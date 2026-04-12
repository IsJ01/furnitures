package com.cur.furniture.integrational.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cur.furniture.dto.SignInDto;
import com.cur.furniture.dto.SignUpDto;
import com.cur.furniture.integrational.IntegrationalTestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
public class AuthControllerValidationTest extends IntegrationalTestBase {

    @Autowired private FilterChainProxy springSecurityFilterChain;
    @Autowired protected WebApplicationContext context;

    @Value("${admin.name}") private String adminName;
    @Value("${admin.password}") private String adminPassword;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .addFilter(springSecurityFilterChain) 
            .build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullSource
    void testSignUpShouldFailed(String field) throws JsonProcessingException, Exception {
        SignUpDto signUpDto = new SignUpDto(field, field);
        mockMvc.perform(post("/auth/sign-up")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(signUpDto))
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
