package com.github.vladimirantin.core.security;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 13.02.2022
 * Time: 22:22
 */
@Configuration
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class DisableSecurityConfiguration {
}
