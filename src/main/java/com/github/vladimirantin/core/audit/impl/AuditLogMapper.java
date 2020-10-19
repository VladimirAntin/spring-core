package com.github.vladimirantin.core.audit.impl;

import com.github.vladimirantin.core.audit.AuditLog;
import com.github.vladimirantin.core.audit.AuditLogDTO;
import com.github.vladimirantin.core.web.mapper.CoreMapperImpl;
import org.springframework.stereotype.Component;

@Component
public class AuditLogMapper extends CoreMapperImpl<AuditLogDTO, AuditLog> {}
