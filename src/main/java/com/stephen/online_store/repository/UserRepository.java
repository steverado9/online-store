package com.stephen.online_store.repository;

import com.stephen.online_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM Users WHERE email = :email", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);
}
