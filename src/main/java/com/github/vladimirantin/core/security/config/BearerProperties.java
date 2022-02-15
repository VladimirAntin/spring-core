package com.github.vladimirantin.core.security.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 13.02.2022
 * Time: 22:22
 */
@Component
public class BearerProperties {

    @Value("${bearer.header:Bearer}")
    public String prefix;

    @Value("${bearer.header:Authorization}")
    public String header;

    @Value("${bearer.secret:spring-core-app}")
    public String secret;

    @Value("${bearer.expiration:8}")
    public Long expiration;

    @Value("${security.links.enable:/api/**}")
    public String[] securityLinksEnable;

    @Value("${security.links.disable:/login}")
    public String[] securityLinksDisable;
}
