package com.cur.furniture.integrational.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cur.furniture.dto.SignInDto;
import com.cur.furniture.dto.SignUpDto;
import com.cur.furniture.integrational.IntegrationalTestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
public class AuthControllerTest extends IntegrationalTestBase {

    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.password}")
    private String adminPassword;

    @Autowired private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSignUp() throws JsonProcessingException, Exception {
        SignUpDto signUpDto = new SignUpDto("Test user", "pass");
        mockMvc.perform(post("/auth/sign-up")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(signUpDto))
        ).andExpect(status().isCreated());
    }

    @Test
    void testSignIn() throws JsonProcessingException, Exception {
        SignInDto signInDto = new SignInDto(adminName, adminPassword);
        mockMvc.perform(post("/auth/sign-in")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(signInDto))
        ).andExpect(status().isOk());
    }

}
