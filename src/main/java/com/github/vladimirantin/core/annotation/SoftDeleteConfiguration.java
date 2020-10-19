package com.github.vladimirantin.core.annotation;

import com.github.vladimirantin.core.softDelete.SoftDeleteConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 17.10.2020
 * Time: 18:41
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(SoftDeleteConfig.class)
public @interface SoftDeleteConfiguration {
}
