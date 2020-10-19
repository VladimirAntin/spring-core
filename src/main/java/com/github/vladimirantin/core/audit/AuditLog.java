package com.github.vladimirantin.core.audit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.github.vladimirantin.core.model.CoreModel;
import com.github.vladimirantin.core.softDelete.event.one.PostSoftDeleteEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 16.10.2020
 * Time: 21:57
 * https://github.com/FasterXML/jackson-datatype-hibernate/issues/87
 */
@Entity(name = "audit_log")
@Getter
@Setter
@ToString
public class AuditLog extends CoreModel {

    private String entityType;
    private String entityName;
    @Enumerated(EnumType.STRING)
    private AuditLogAction action;

    @Column(length = 16777215, columnDefinition = "mediumtext")
    private String currentState;
    @Column(length = 16777215, columnDefinition = "mediumtext")
    private String previousState;

    private Long entityId;
    private String username;


    public static AuditLog INSERT(PostInsertEvent event, String username) {
        if (event.getEntity().getClass().getCanonicalName().equals(AuditLog.class.getCanonicalName())) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Hibernate5Module());
        CoreModel model = (CoreModel) event.getEntity();
        AuditLog auditLog = createChangeEntityFromCoreModel(model, username);
        auditLog.setAction(AuditLogAction.INSERT);
        auditLog.setPreviousState("{}");
        try {
            auditLog.setCurrentState(objectMapper.writeValueAsString(model));
        } catch (Exception e) {
            auditLog.setCurrentState("{}");
        }
        return auditLog;
    }

    public static AuditLog UPDATE(PostUpdateEvent event, String username) {
        if (event.getEntity().getClass().getCanonicalName().equals(AuditLog.class.getCanonicalName())) {
            return null;
        }
        AuditLog auditLog = createChangeEntityFromCoreModel((CoreModel) event.getEntity(), username);
        auditLog.setAction(AuditLogAction.UPDATE);
        List<String> previous = new ArrayList<>();
        List<String> current = new ArrayList<>();

        for (int i : event.getDirtyProperties()) {
            String propertyName = event.getPersister().getPropertyNames()[i];
            Object oldValue = event.getOldState()[i];
            Object currentValue = event.getState()[i];
            previous.add(String.format("%s: %s", propertyName, oldValue));
            current.add(String.format("%s: %s", propertyName, currentValue));
        }
        auditLog.setPreviousState(String.format("{%s}", String.join(", ", previous)));
        auditLog.setCurrentState(String.format("{%s}", String.join(", ", current)));
        return auditLog;
    }

    public static AuditLog DELETE(PostSoftDeleteEvent event, String username) {
        if (event.getEntity().getClass().getCanonicalName().equals(AuditLog.class.getCanonicalName())) {
            return null;
        }
        AuditLog auditLog = createChangeEntityFromCoreModel((CoreModel) event.getEntity(), username);
        auditLog.setAction(AuditLogAction.DELETE);
        auditLog.setPreviousState("{}");
        auditLog.setCurrentState("{}");
        return auditLog;
    }

    protected static AuditLog createChangeEntityFromCoreModel(CoreModel model, String username) {
        AuditLog auditLog = new AuditLog();
        auditLog.setUsername(username);
        auditLog.setEntityName(model.getClass().getName());
        auditLog.setEntityType(model.getClass().getCanonicalName());
        auditLog.setEntityId(model.getId());
        return auditLog;
    }
}
