package com.github.vladimirantin.core.reflection;

import com.github.vladimirantin.core.web.DTO.CoreDTO;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Date: 27.02.2020
 * Time: 20:42
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CoreImpl {
    enum ImplType {

        STANDARD, EXTENDED, SERVICE, REPO, CONTROLLER, EXTENDED_CONTROLLER, MAPPER
    }
    ImplType[] type() default {ImplType.STANDARD};
    Class<?> DTO() default CoreDTO.class;

}
