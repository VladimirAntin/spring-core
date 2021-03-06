package com.github.vladimirantin.core.softDelete.event.one;

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
public class PostSoftDeleteEvent extends SoftDeleteEvent {

    public PostSoftDeleteEvent(Object source, Object entityClass) {
        super(source, entityClass);
    }
}
