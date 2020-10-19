package com.github.vladimirantin.core.audit.impl;

import com.github.vladimirantin.core.audit.AuditLog;
import com.github.vladimirantin.core.service.CoreModelService;
import org.springframework.stereotype.Service;

@Service
public class AuditLogService extends CoreModelService<AuditLogRepo, AuditLog> {
}
