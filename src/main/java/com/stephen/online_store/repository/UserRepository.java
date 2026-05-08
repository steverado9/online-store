package com.stephen.online_store.repository;

import com.stephen.online_store.entity.User;
import com.stephen.online_store.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Transactional
    @Query(value = """
    INSERT INTO users (email, password, first_name, last_name, phone_number, role)
    VALUES (:email, :password, :firstName, :lastName, :phoneNumber, :role)
    """, nativeQuery = true)
    void saveUser(
            @Param("email") String email,
            @Param("password") String password,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("phoneNumber") String phoneNumber,
            @Param("role") Role role
    );

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);


}
