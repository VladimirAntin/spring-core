package com.github.vladimirantin.core.audit;

import com.github.vladimirantin.core.web.DTO.CoreDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 16.10.2020
 * Time: 21:57
 */
@Getter
@Setter
public class AuditLogDTO extends CoreDTO {

    private String action;
    private String currentState;
    private String previousState;
    private Long entityId;
    private String entityName;
    private String username;
}
