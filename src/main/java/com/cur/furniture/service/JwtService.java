package com.cur.furniture.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cur.furniture.database.entity.User;
import com.cur.furniture.database.entity.User.Role;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateKey(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 1000 * 60 * 6);
        return Jwts.builder()
            .subject(user.getUsername())
            .claim("role", user.getRole())
            .expiration(expiryDate)
            .signWith(getKey())
            .compact();
    }

    public Role extractRole(String token) {
        return Role.valueOf(
            Jwts.parser()
            .verifyWith(getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .get("role", String.class)
        );
    }

    public String extractUsername(String token) {
        return Jwts.parser()
            .verifyWith(getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

}
