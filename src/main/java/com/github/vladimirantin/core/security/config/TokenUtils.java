package com.github.vladimirantin.core.security.config;

import com.github.vladimirantin.core.utils.Try;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: vladimir_antin
 * Email: antin502@gmail.com
 * Date: 13.02.2022
 * Time: 22:22
 */
@Component
public class TokenUtils {

    @Autowired
    BearerProperties bearerProperties;

    public String getUsernameFromToken(String token) {
        return Try.then(() -> {
            Claims claims = this.getClaimsFromToken(token);
            return claims.getSubject();
        },null);
    }

    private Claims getClaimsFromToken(String token) {
        return Try.then(() -> Jwts.parser().setSigningKey(bearerProperties.secret).parseClaimsJws(token).getBody(), null);
    }

    private Date getExpirationDateFromToken(String token) {
        return Try.then(() -> {
            final Claims claims = this.getClaimsFromToken(token);
            return claims.getExpiration();
        }, null);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(new Date(System.currentTimeMillis()));
    }

    public boolean validateToken(String token, UserDetails userDetails, String path) {
        final String username = getUsernameFromToken(token);
        final Claims claims = getClaimsFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token) || path.equals("/api/refresh");
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails, generateExpirationDate());
    }

    private String generateToken(UserDetails userDetails, Date expiredOn) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", userDetails.getUsername());
        claims.put("created", new Date(System.currentTimeMillis()));
        return Jwts.builder().setClaims(claims)
                .setExpiration(expiredOn)
                .signWith(SignatureAlgorithm.HS512, bearerProperties.secret).compact();
    }

    private Date generateExpirationDate() {
        return new Date(getCurrentTimeMillis() + bearerProperties.expiration * 60 * 60 * 1000);
    }

    private long getCurrentTimeMillis() {
        return new Date().getTime();
    }

}
