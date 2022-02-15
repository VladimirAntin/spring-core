package com.github.vladimirantin.core.security.web.DTO;

import com.github.vladimirantin.core.web.DTO.CoreDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 13.02.2022
 * Time: 22:22
 */
@Getter
@Setter
public class LoginUserDTO extends CoreDTO {

    private String username;
    private String password;
}
