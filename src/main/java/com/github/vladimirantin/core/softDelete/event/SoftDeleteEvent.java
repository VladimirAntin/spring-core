package com.github.vladimirantin.core.softDelete.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 17.10.2020
 * Time: 11:57
 */
@Getter
@Setter
public class SoftDeleteEvent extends ApplicationEvent {
    Object entity;
    Iterable<? extends Object> entities;

    public SoftDeleteEvent(Object source, Object entity) {
        super(source);
        this.entity = entity;
    }

    public SoftDeleteEvent(Object source, Iterable<? extends Object> entities) {
        super(source);
        this.entities = entities;
    }
}
