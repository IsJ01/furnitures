package com.cur.furniture.controller;

import org.springframework.web.bind.annotation.RestController;

import com.cur.furniture.dto.SignInDto;
import com.cur.furniture.dto.SignUpDto;
import com.cur.furniture.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto signUpDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(authService.signUp(signUpDto));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInDto signInDto) {
        return ResponseEntity.status(HttpStatus.OK)
            .body(authService.signIn(signInDto));
    }
    
}
