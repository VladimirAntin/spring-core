package com.github.vladimirantin.core.security.service;


import com.github.vladimirantin.core.security.config.BearerProperties;
import com.github.vladimirantin.core.security.config.TokenUtils;
import com.github.vladimirantin.core.security.model.CoreUser;
import com.github.vladimirantin.core.security.web.DTO.LoginUserDTO;
import com.github.vladimirantin.core.utils.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 13.02.2022
 * Time: 22:22
 */
@Service
public class AuthService {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CoreUserService userService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private BearerProperties properties;

    public CoreUser login(LoginUserDTO userLogin) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword());
        authenticationManager.authenticate(token);
        return userService.getUser(userLogin.getUsername());

    }

    private boolean isValidLong(String code) {
        return Try.then(() -> {
            Long.parseLong(code);
            return true;
        }, false);
    }

    public HttpHeaders setHeader(CoreUser user) {
        HttpHeaders headers = new HttpHeaders();
        UserDetails details = userDetailsService.loadUserByUsername(user.getUsername());
        headers.set(properties.header, properties.prefix.concat(" ").concat(tokenUtils.generateToken(details)));
        return headers;
    }

}
