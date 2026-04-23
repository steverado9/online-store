package com.stephen.online_store;

import com.stephen.online_store.entity.User;
import com.stephen.online_store.enums.Role;
import com.stephen.online_store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OnlineStoreApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.findByEmail("isaac.stephen@example.com").isEmpty()) {
			String encodedPassword = passwordEncoder.encode("12345");

			User user1 = new User("isaac.stephen@example.com", encodedPassword, "Isaac", "Stephen", "08096502070", Role.ADMIN);
			userRepository.save(user1);
			System.out.println("Default admin user created successfully.");
		} else {
			System.out.println("Admin user already exists.");
		}
	}
}
