package com.github.vladimirantin.core.softDelete.event;

import com.github.vladimirantin.core.softDelete.event.collection.PostSoftDeleteCollectionEvent;
import com.github.vladimirantin.core.softDelete.event.collection.PreSoftDeleteCollectionEvent;
import com.github.vladimirantin.core.softDelete.event.one.PostSoftDeleteEvent;
import com.github.vladimirantin.core.softDelete.event.one.PreSoftDeleteEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 17.10.2020
 * Time: 11:59
 */
public interface SoftDeleteListener extends ApplicationListener<SoftDeleteEvent>{

    @Override
    default void onApplicationEvent(SoftDeleteEvent event) {
        if (event instanceof PreSoftDeleteEvent) {
            onPreSoftDelete((PreSoftDeleteEvent) event);
        } else if (event instanceof PostSoftDeleteEvent) {
            onPostSoftDelete((PostSoftDeleteEvent) event);
        } else if (event instanceof PreSoftDeleteCollectionEvent) {
            onPreSoftDeleteCollectionEvent((PreSoftDeleteCollectionEvent) event);
        } else if (event instanceof PostSoftDeleteCollectionEvent) {
            onPostSoftDeleteCollectionEvent((PostSoftDeleteCollectionEvent) event);
        }
    }


    default void onPreSoftDelete(PreSoftDeleteEvent event){}
    default void onPostSoftDelete(PostSoftDeleteEvent event){}
    default void onPreSoftDeleteCollectionEvent(PreSoftDeleteCollectionEvent event){}
    default void onPostSoftDeleteCollectionEvent(PostSoftDeleteCollectionEvent event){}

}