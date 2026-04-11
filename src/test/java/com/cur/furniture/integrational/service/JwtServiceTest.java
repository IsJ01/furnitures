package com.cur.furniture.integrational.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.cur.furniture.database.entity.User;
import com.cur.furniture.database.entity.User.Role;
import com.cur.furniture.integrational.IntegrationalTestBase;
import com.cur.furniture.service.JwtService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtServiceTest extends IntegrationalTestBase {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Autowired private JwtService jwtService;

    @Test
    void testCreate() {
        String token = jwtService.generateToken(new User("Test user", null, Role.CONSULTANT));
        assertThat(jwtService.extractUsername(token)).isEqualTo("Test user");
        assertThat(jwtService.extractRole(token)).isEqualTo(Role.CONSULTANT);
        assertThat(jwtService.validateToken(token)).isTrue();
    }

    @Test
    void testValidate() {
        Date expiryDate = new Date(new Date().getTime() - 1000 * 60 * 6);
        String token = Jwts.builder()
            .subject("Test user")
            .claim("role", Role.CONSULTANT)
            .expiration(expiryDate)
            .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
            .compact();
        assertThat(jwtService.validateToken(token)).isFalse();
    }

}
