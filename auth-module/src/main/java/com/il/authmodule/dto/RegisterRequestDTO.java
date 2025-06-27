package com.il.authmodule.dto;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@AllArgsConstructor
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

    @NotBlank(message = "Role is required")
    private String role; // Default role set to 'user'

    public @NotBlank(message = "First name is required") String getFirstName() {
        return first_name;
    }

    public @NotBlank(message = "Last name is required") String getLastName() {
        return last_name;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be a valid email address") String getEmail() {
        return email;
    }

    public @NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") String getPassword() {
        return password;
    }

    public @NotBlank(message = "Role is required") String getRole() {
        return role;
    }
}
