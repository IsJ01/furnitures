package com.cur.furniture.database.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "furnitures")
@Entity
public class User extends BaseEntity implements UserDetails {

    private String username;

    private String password;

    private Role role;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role.toAuthority());
    }

    public static enum Role {
        ADMIN,
        CONSULTANT;

        public GrantedAuthority toAuthority() {
            return new SimpleGrantedAuthority(name());
        }

    }

}
