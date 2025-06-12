package com.il.authmodule.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.il.authmodule.constant.AuthConstant;

public class RegisterRequestDTO {
    @JsonProperty("first_name")
    @NotBlank(message = "First name is required")
    private String first_name;

    @JsonProperty("last_name")
    @NotBlank(message = "Last name is required")
    private String last_name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be a valid email address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    private String role = AuthConstant.END_USER_ROLE; // Default role set to 'user'

    public @NotBlank(message = "First name is required") String getFirstName() {
        return first_name;
    }
    public void setFirstName(@NotBlank(message = "First name is required") String first_name) {
        this.first_name = first_name;
    }

    public @NotBlank(message = "Last name is required") String getLastName() {
        return last_name;
    }
    public void setLastName(@NotBlank(message = "Last name is required") String last_name) {
        this.last_name = last_name;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be a valid email address") String getEmail() {
        return email;
    }
    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be a valid email address") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") String getPassword() {
        return password;
    }
    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
