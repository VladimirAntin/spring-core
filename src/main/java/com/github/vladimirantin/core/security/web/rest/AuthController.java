package com.github.vladimirantin.core.security.web.rest;

import com.github.vladimirantin.core.security.config.BearerProperties;
import com.github.vladimirantin.core.security.config.TokenUtils;
import com.github.vladimirantin.core.security.model.CoreUser;
import com.github.vladimirantin.core.security.service.AuthService;
import com.github.vladimirantin.core.security.service.CoreUserService;
import com.github.vladimirantin.core.security.web.DTO.LoginUserDTO;
import com.github.vladimirantin.core.security.web.DTO.UserDTO;
import com.github.vladimirantin.core.security.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 14.02.2022
 * Time: 22:22
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BearerProperties bearerProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private CoreUserService coreUserService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody final LoginUserDTO user) {
        try {
            CoreUser coreUser = authService.login(user);
            HttpHeaders headers = authService.setHeader(coreUser);
            return ResponseEntity.ok().headers(headers).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Invalid login");
        }
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(Principal principal) {
        UserDetails details = userDetailsService.loadUserByUsername(principal.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.set(bearerProperties.header, tokenUtils.generateToken(details));
        return ResponseEntity.ok()
                .headers(headers)
                .build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> findOne(Principal principal) {
        return ResponseEntity.ok(userMapper.toDto(coreUserService.getUser(principal.getName())));
    }

}
