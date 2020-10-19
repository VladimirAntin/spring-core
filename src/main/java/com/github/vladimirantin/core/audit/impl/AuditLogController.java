package com.github.vladimirantin.core.audit.impl;

import com.github.vladimirantin.core.audit.AuditLog;
import com.github.vladimirantin.core.audit.AuditLogDTO;
import com.github.vladimirantin.core.web.rest.CoreRestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/audit_logs")
public class AuditLogController extends CoreRestController<AuditLogService, AuditLogMapper, AuditLogDTO, AuditLog> {
}
