package com.github.vladimirantin.core.audit;

import com.github.vladimirantin.core.audit.impl.AuditLogService;
import com.github.vladimirantin.core.softDelete.event.SoftDeleteEvent;
import com.github.vladimirantin.core.softDelete.event.SoftDeleteListener;
import com.github.vladimirantin.core.softDelete.event.collection.PostSoftDeleteCollectionEvent;
import com.github.vladimirantin.core.softDelete.event.collection.PreSoftDeleteCollectionEvent;
import com.github.vladimirantin.core.softDelete.event.one.PreSoftDeleteEvent;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 16.10.2020
 * Time: 23:37
 */
public abstract class AuditInterceptor implements PostInsertEventListener, PostUpdateEventListener, SoftDeleteListener {

    @PersistenceUnit
    private EntityManagerFactory emf;  // NOTE Can't autowire SessionFactory.

    @Autowired
    protected AuditLogService auditLogService;

    @Override
    public final void onApplicationEvent(SoftDeleteEvent event) {
        if (event instanceof PreSoftDeleteEvent) {
            onPreSoftDelete((PreSoftDeleteEvent) event);
        }
    }

    @PostConstruct
    public final void registerListeners() {
        SessionFactoryImpl sessionFactory = emf.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory)
                .getServiceRegistry().getService(EventListenerRegistry.class);
        registry.appendListeners(EventType.POST_INSERT, this);
        registry.appendListeners(EventType.POST_UPDATE, this);
    }

    @Override
    public final void onPreSoftDelete(PreSoftDeleteEvent event) { }

    @Override
    public final void onPreSoftDeleteCollectionEvent(PreSoftDeleteCollectionEvent event) { }

    @Override
    public final void onPostSoftDeleteCollectionEvent(PostSoftDeleteCollectionEvent event) { }

    @Override
    public final boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }

    @Override
    public final boolean requiresPostCommitHanding(EntityPersister persister) {
        return false;
    }
}
