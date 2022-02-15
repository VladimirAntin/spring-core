package com.github.vladimirantin.core.security;

import com.github.vladimirantin.core.security.config.BearerProperties;
import com.github.vladimirantin.core.security.config.WebSecurity;
import com.github.vladimirantin.core.security.config.TokenUtils;
import com.github.vladimirantin.core.security.model.CoreUser;
import com.github.vladimirantin.core.security.model.Role;
import com.github.vladimirantin.core.security.service.AuthService;
import com.github.vladimirantin.core.security.service.UserDetailsServiceImpl;
import com.github.vladimirantin.core.security.web.DTO.LoginUserDTO;
import com.github.vladimirantin.core.security.web.DTO.UserDTO;
import com.github.vladimirantin.core.security.web.mapper.UserMapper;
import com.github.vladimirantin.core.security.web.rest.AuthController;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 13.02.2022
 * Time: 22:22
 */
@Configuration
@Import({BearerProperties.class, WebSecurity.class, TokenUtils.class,
        CoreUser.class, Role.class, UserDTO.class, UserDetailsServiceImpl.class, AuthController.class,
        AuthService.class, LoginUserDTO.class, UserMapper.class
})
@AutoConfigurationPackage
public class SecurityConfiguration {
}
