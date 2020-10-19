package com.github.vladimirantin.core.softDelete.event;

import com.github.vladimirantin.core.softDelete.event.collection.PostSoftDeleteCollectionEvent;
import com.github.vladimirantin.core.softDelete.event.collection.PreSoftDeleteCollectionEvent;
import com.github.vladimirantin.core.softDelete.event.one.PostSoftDeleteEvent;
import com.github.vladimirantin.core.softDelete.event.one.PreSoftDeleteEvent;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 17.10.2020
 * Time: 11:58
 */
public class SoftDeletePublisher {

    private static ApplicationEventPublisher applicationEventPublisher;

    public static boolean isAvailable() {
        return applicationEventPublisher != null;
    }

    public SoftDeletePublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public static void preSoftDelete(Object entityClass) {
        if (isAvailable()) {
            PreSoftDeleteEvent preSoftDeleteEvent = new PreSoftDeleteEvent(SoftDeletePublisher.class, entityClass);
            applicationEventPublisher.publishEvent(preSoftDeleteEvent);
        }
    }

    public static void postSoftDelete(Object entityClass) {
        if (isAvailable()) {
            PostSoftDeleteEvent postSoftDeleteEvent = new PostSoftDeleteEvent(SoftDeletePublisher.class, entityClass);
            applicationEventPublisher.publishEvent(postSoftDeleteEvent);
        }
    }
    public static void preSoftDeleteCollection(Iterable<? extends Object> entityClasses) {
        if (isAvailable()) {
            PreSoftDeleteCollectionEvent preSoftDeleteEvent = new PreSoftDeleteCollectionEvent(SoftDeletePublisher.class, entityClasses);
            applicationEventPublisher.publishEvent(preSoftDeleteEvent);
        }
    }

    public static void postSoftDeleteCollection(Iterable<? extends Object> entityClasses) {
        if (isAvailable()) {
            PostSoftDeleteCollectionEvent postSoftDeleteEvent = new PostSoftDeleteCollectionEvent(SoftDeletePublisher.class, entityClasses);
            applicationEventPublisher.publishEvent(postSoftDeleteEvent);
        }
    }
}