package com.cur.furniture.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cur.furniture.database.entity.User;
import com.cur.furniture.database.entity.User.Role;
import com.cur.furniture.database.repository.UserRepository;

@Component
public class AdminInitializer implements ApplicationRunner {

    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.password}")
    private String adminPassword;
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public AdminInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.findByRole(Role.ADMIN).isEmpty()) {
            User admin = new User();
            admin.setUsername(adminName);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(Role.ADMIN);
            
            userRepository.saveAndFlush(admin);
            System.out.println("Admin user created successfully!");
        }
    }
}
