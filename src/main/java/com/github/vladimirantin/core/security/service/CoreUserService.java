package com.github.vladimirantin.core.security.service;

import com.github.vladimirantin.core.security.model.CoreUser;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 13.02.2022
 * Time: 22:22
 */
public interface CoreUserService {

    CoreUser getUser(String username);
/*
    {
        return userRepo.findByUsernameAndActive(username, true)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("No user found with username '%s'.", username)));
    }
*/
}
