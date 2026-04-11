package com.cur.furniture.integrational.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.cur.furniture.database.entity.User;
import com.cur.furniture.database.entity.User.Role;
import com.cur.furniture.dto.SignUpDto;
import com.cur.furniture.integrational.IntegrationalTestBase;
import com.cur.furniture.service.UserService;

public class UserServiceTest extends IntegrationalTestBase {

    @Value("${admin.name}")
    private String adminName;

    @Autowired private UserService userService;

    @Test
    void testCreate() {
        SignUpDto signUpDto = new SignUpDto("test user", "pass");
        User user = userService.create(signUpDto);
        assertThat(user.getUsername()).isEqualTo("test user");
        assertThat(user.getRole()).isEqualTo(Role.CONSULTANT);
    }

    @Test
    void testLoad() {
        User user = (User) userService.loadUserByUsername(adminName);
        assertThat(user.getUsername()).isEqualTo(adminName);
        assertThat(user.getRole()).isEqualTo(Role.ADMIN);
    }

}
