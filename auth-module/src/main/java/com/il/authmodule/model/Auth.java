package com.il.authmodule.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.il.authmodule.constant.AuthConstant;

import java.util.UUID;

@Entity
@Table(name = "auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Column(unique = true)
    @Email
    private String email;

    @NotNull
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull
    private String provider;

    @NotNull
    private Boolean is_banned;

    @NotNull
    private UUID role;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private UserProfile userProfile;

    public Auth() {}

    private Auth(Builder builder) {
        this.email = builder.email;
        this.password = builder.password;
        this.provider = builder.provider;
        this.is_banned = builder.is_banned;
        this.role = builder.role;
    }

    public static class Builder {
        private String email;
        private String password;
        private String provider = AuthConstant.PROVIDER_TYPE.local.name(); // Default provider
        private boolean is_banned = false;
        private UUID role = AuthConstant.END_USER_ROLE_UUID; // Default role

        // Setter methods for optional parameters
        public Builder email(@NotNull @Email String email) {
            this.email = email;
            return this;
        }
        public Builder password(@NotNull @Size(min = 8, message = "Password must be at least 8 characters long") String password) {
            this.password = password;
            return this;
        }
        public Builder provider(@NotNull String provider) {
            this.provider = provider;
            return this;
        }
        public Builder isBanned(@NotNull Boolean is_banned) {
            this.is_banned = is_banned;
            return this;
        }
        public Builder role(@NotNull UUID role) {
            this.role = role;
            return this;
        }

        // Build method to create the Car object
        public Auth build() {
            return new Auth(this);
        }
    }

    public static class BuilderChain {
        private final Auth auth;

        public BuilderChain(Auth auth) {
            this.auth = auth;
        }

        public void userProfile(UserProfile userProfile) {
            auth.setUserProfile(userProfile);
        }
    }

    // ✅ Custom chain class
    public BuilderChain BuildMore() {
        return new BuilderChain(this);
    }

    public UUID getId() { return id; }

    public @NotNull @Email String getEmail() { return email; }

    public @NotNull @Size(min = 8, message = "Password must be at least 8 characters long") String getPassword() { return password; }

    public @NotNull String getProvider() { return provider; }

    public @NotNull Boolean getIsBanned() { return is_banned; }

    public @NotNull UUID getRole() { return role; }

    public @NotNull UserProfile getUserProfile() { return userProfile; }
    public void setUserProfile(@NotNull UserProfile userProfile) { this.userProfile = userProfile; }

}
