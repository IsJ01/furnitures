package com.cur.furniture.integrational.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cur.furniture.database.entity.User;
import com.cur.furniture.database.entity.User.Role;
import com.cur.furniture.database.repository.UserRepository;
import com.cur.furniture.dto.SignInDto;
import com.cur.furniture.dto.SignUpDto;
import com.cur.furniture.integrational.IntegrationalTestBase;
import com.cur.furniture.service.AuthService;

public class AuthServiceTest extends IntegrationalTestBase {

    @Autowired private AuthService authService;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        User user = new User("Test user", passwordEncoder.encode("pass"), Role.CONSULTANT);
        userRepository.saveAndFlush(user);
    }

    @Test
    void testSignIn() {
        authService.signIn(new SignInDto("Test user", "pass"));
    }

    @Test
    void testSignUp() {
        authService.signUp(new SignUpDto("Test user 2", "pass"));

        User user = userRepository.findByUsername("Test user 2").orElseThrow();

        assertThat(user.getUsername()).isEqualTo("Test user 2");
    }

}
