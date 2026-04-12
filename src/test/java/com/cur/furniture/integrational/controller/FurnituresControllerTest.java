package com.cur.furniture.integrational.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cur.furniture.database.entity.User;
import com.cur.furniture.dto.FurnitureCreateDto;
import com.cur.furniture.integrational.IntegrationalTestBase;
import com.cur.furniture.service.JwtService;
import com.cur.furniture.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FurnituresControllerTest extends IntegrationalTestBase {

    @Value("${admin.name}") private String adminName;

    @Autowired private FilterChainProxy springSecurityFilterChain;
    @Autowired private JwtService jwtService;
    @Autowired private UserService userService;
    @Autowired protected WebApplicationContext context;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .addFilter(springSecurityFilterChain) 
            .build();
    }

    @Test
    void testCreate() throws JsonProcessingException, Exception {
        FurnitureCreateDto createDto = new FurnitureCreateDto(
            13l, "a", 1, 
            1, 1, 1, 
            "b", null
        );
        mockMvc.perform(post("/furnitures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createDto))
            .header("Authorization", "Bearer " + jwtService.generateToken(
                (User) userService.loadUserByUsername(adminName)
            ))
        ).andExpect(status().isCreated());
    }

    @Test
    void testCreateShouldForbidden() throws JsonProcessingException, Exception {
        mockMvc.perform(post("/furnitures")
            .contentType(MediaType.APPLICATION_JSON)
            .content("")
        ).andExpect(status().isForbidden());
    }


    @Test
    void testCreateShouldFailed() throws JsonProcessingException, Exception {
        FurnitureCreateDto createDto = new FurnitureCreateDto(
            null, null, null, 
            null, null, null, 
            null, null
        );
        mockMvc.perform(post("/furnitures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createDto))
            .header("Authorization", "Bearer " + jwtService.generateToken(
                (User) userService.loadUserByUsername(adminName)
            ))
        ).andExpect(status().isBadRequest());
    }

}
