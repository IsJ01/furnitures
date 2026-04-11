package com.cur.furniture.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cur.furniture.database.entity.User;
import com.cur.furniture.database.entity.User.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByRole(Role role);

}
