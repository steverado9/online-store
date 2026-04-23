package com.stephen.online_store.dto;

import jakarta.validation.constraints.NotEmpty;

public class CreateUserDto {

    private Long id;

    @NotEmpty(message = "email should not be empty")
    private String email;

    @NotEmpty(message = "password should not be empty")
    private String password;

    @NotEmpty(message = "firstName should not be empty")
    private String firstName;

    @NotEmpty(message = "lastName should not be empty")
    private String lastName;

    @NotEmpty(message = "phoneNumber should not be empty")
    private String phoneNumber;

    public CreateUserDto() {}

    public CreateUserDto(String email, String password, String firstName, String lastName, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
