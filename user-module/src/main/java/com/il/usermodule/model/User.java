package com.il.usermodule.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String first_name;

    @NotNull
    private String last_name;

    @NotNull
    private String email;

    private String password;

    @NotNull
    private String avatar;

    private String provider;

    @NotNull
    private Boolean email_notifications;

    @NotNull
    @Column(columnDefinition = "int default 0")
    private int vocab_usage_count;

    @NotNull
    private Boolean is_banned;

    @NotNull
    private UUID role;

    public UUID getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getProvider() {
        return provider;
    }

    public Boolean getEmail_notifications() {
        return email_notifications;
    }

    public int getVocab_usage_count() {
        return vocab_usage_count;
    }

    public Boolean getIs_banned() {
        return is_banned;
    }

    public UUID getRole() {
        return role;
    }
}