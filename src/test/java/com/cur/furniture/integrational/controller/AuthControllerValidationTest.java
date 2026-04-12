package com.cur.furniture.integrational.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cur.furniture.database.entity.User;
import com.cur.furniture.dto.SignInDto;
import com.cur.furniture.dto.SignUpDto;
import com.cur.furniture.integrational.IntegrationalTestBase;
import com.cur.furniture.service.JwtService;
import com.cur.furniture.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
public class AuthControllerValidationTest extends IntegrationalTestBase {

    @Value("${admin.name}") private String adminName;
    @Value("${admin.password}") private String adminPassword;

    @Autowired private JwtService jwtService;
    @Autowired private MockMvc mockMvc;
    @Autowired private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void testSignUpShouldFailed(String field) throws JsonProcessingException, Exception {
        SignUpDto signUpDto = new SignUpDto(field, field);
        mockMvc.perform(post("/auth/sign-up")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(signUpDto))
            .header("Authorization", "Bearer " + jwtService.generateToken(
                (User) userService.loadUserByUsername(adminName)
            ))
        ).andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void testSignIn(String field) throws JsonProcessingException, Exception {
        SignInDto signInDto = new SignInDto(field, field);
        mockMvc.perform(post("/auth/sign-in")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(signInDto))
        ).andExpect(status().isBadRequest());
    }

}
