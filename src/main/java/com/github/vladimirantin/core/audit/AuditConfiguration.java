package com.github.vladimirantin.core.audit;

import com.github.vladimirantin.core.audit.impl.*;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 19.10.2020
 * Time: 12:35
 */
@Configuration
@Import({AuditInterceptorImpl.class, AuditLog.class, AuditLogRepo.class,
         AuditLogService.class, AuditLogMapper.class, AuditLogController.class})
@AutoConfigurationPackage
public class AuditConfiguration { }
