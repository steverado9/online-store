package com.stephen.online_store.service.Impl;

import com.stephen.online_store.entity.User;
import com.stephen.online_store.enums.Role;
import com.stephen.online_store.repository.UserRepository;
import com.stephen.online_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(String email, String password, String firstname, String lastname, String phoneNumber, Role role) {
        userRepository.saveUser(email, password, firstname, lastname, phoneNumber, role);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }
}
