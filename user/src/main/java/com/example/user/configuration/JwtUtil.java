package com.example.user.configuration;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@Component
public class JwtUtil {
    //todo replace with value in properties file
    private static final String SECRET_KEY = "MySuperSecretKeyForJwtSigningMustBeAtLeast32Bytes";
    private static final SecretKey key = hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(String username) {
        LocalDateTime now = LocalDateTime.now();
        Date issuedAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        Date expirationAt = Date.from(now.plusHours(1).atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .subject(username)
                .issuedAt(issuedAt)
                .expiration(expirationAt)
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        Jws<Claims> claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseClaimsJws(token);
        return claims.getBody().getSubject();
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token));
    }
}
