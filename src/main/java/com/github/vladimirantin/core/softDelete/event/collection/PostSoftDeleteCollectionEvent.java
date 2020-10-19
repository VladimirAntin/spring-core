package com.github.vladimirantin.core.softDelete.event.collection;

import com.github.vladimirantin.core.softDelete.event.SoftDeleteEvent;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 17.10.2020
 * Time: 14:55
 */
@Getter
@Setter
public class PostSoftDeleteCollectionEvent extends SoftDeleteEvent {

    public PostSoftDeleteCollectionEvent(Object source, Iterable<? extends Object> entityClasses) {
        super(source, entityClasses);
    }
}
