package com.github.vladimirantin.core.security.web.rest;

import com.github.vladimirantin.core.security.config.TokenUtils;
import com.github.vladimirantin.core.security.model.CoreUser;
import com.github.vladimirantin.core.security.service.AuthService;
import com.github.vladimirantin.core.security.web.DTO.LoginUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 13.02.2022
 * Time: 22:22
 */
@RestController
@RequestMapping("")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private AuthService authService;

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

/*

    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(Principal principal) {
        UserDetails details = userDetailsService.loadUserByUsername(principal.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.set(properties.header, tokenUtils.generateToken(details));
        return ResponseEntity.ok()
                .headers(headers)
                .build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> findOne(Principal principal) {
        return ResponseEntity.ok(userMapper.toDto(userService.findByUsername(principal.getName())));
    }
*/

}
