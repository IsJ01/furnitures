package com.cur.furniture.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cur.furniture.database.entity.User;
import com.cur.furniture.database.entity.User.Role;
import com.cur.furniture.database.repository.UserRepository;
import com.cur.furniture.dto.SignUpDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Transactional
    public User create(SignUpDto signUpDto) {
        User user = new User(
            signUpDto.getUsername(), 
            passwordEncoder.encode(signUpDto.getPassword()), 
            Role.CONSULTANT
        );
        userRepository.save(user);
        return user;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
