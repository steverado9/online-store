package com.stephen.online_store.service;

import com.stephen.online_store.entity.User;
import com.stephen.online_store.enums.Role;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    void saveUser(String email, String password, String firstname, String lastname, String phoneNumber, Role role);

    Optional<User> findById(Long userId);
}
