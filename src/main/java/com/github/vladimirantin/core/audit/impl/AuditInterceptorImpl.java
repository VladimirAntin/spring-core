package com.github.vladimirantin.core.audit.impl;

import com.github.vladimirantin.core.audit.AuditInterceptor;
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
    protected String loggedInUsername() {
        String defaultUsername = "Application";
        try {
            defaultUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e){}
        return defaultUsername;
    }
}
