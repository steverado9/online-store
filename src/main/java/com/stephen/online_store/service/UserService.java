package com.stephen.online_store.service;

import com.stephen.online_store.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    void saveUser(User user);
}
