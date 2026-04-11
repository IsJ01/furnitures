package com.cur.furniture.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.stereotype.Service;

import com.cur.furniture.database.entity.User;
import com.cur.furniture.dto.JwtToken;
import com.cur.furniture.dto.SignInDto;
import com.cur.furniture.dto.SignUpDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public JwtToken signUp(SignUpDto signUpDto) {
        return Optional.of(signUpDto)
            .map(userService::create)
            .map(jwtService::generateToken)
            .map(JwtToken::new)
            .get();
    }

    public JwtToken signIn(SignInDto signInDto) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword())
        );
        User user = (User) userService.loadUserByUsername(signInDto.getUsername());
        return new JwtToken(jwtService.generateToken(user));
    }

}
