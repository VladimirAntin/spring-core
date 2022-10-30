package com.github.vladimirantin.core.audit.impl;

import com.github.vladimirantin.core.audit.AuditInterceptor;
import com.github.vladimirantin.core.audit.AuditLog;
import com.github.vladimirantin.core.softDelete.event.one.PostSoftDeleteEvent;
import com.github.vladimirantin.core.utils.Try;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 19.10.2020
 * Time: 02:02
 */
@Component
public class AuditInterceptorImpl extends AuditInterceptor {

    @Override
    public void onPostInsert(PostInsertEvent event) {
        AuditLog ce = AuditLog.INSERT(event, loggedInUsername());
        if (ce!=null) {
            auditLogService.save(ce);
        }
    }

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        AuditLog ce = AuditLog.UPDATE(event, loggedInUsername());
        if (ce!=null) {
            auditLogService.save(ce);
        }
    }

    @Override
    public void onPostSoftDelete(PostSoftDeleteEvent event) {
        AuditLog ce = AuditLog.DELETE(event, loggedInUsername());
        if (ce!=null) {
            auditLogService.save(ce);
        }
    }

    protected String loggedInUsername() {
        return Try.then(() -> SecurityContextHolder.getContext().getAuthentication().getName(), "Application");
    }
}
