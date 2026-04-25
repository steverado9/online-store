package com.stephen.online_store.service.Impl;

import com.stephen.online_store.entity.User;
import com.stephen.online_store.repository.UserRepository;
import com.stephen.online_store.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
