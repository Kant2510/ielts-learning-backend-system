package com.il.usermodule.dto;

public class UserResponseDTO {
    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String avatar;
    private String provider;
    private Boolean email_notifications;
    private int vocab_usage_count;
    private Boolean is_banned;
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Boolean getEmail_notifications() {
        return email_notifications;
    }

    public void setEmail_notifications(Boolean email_notifications) {
        this.email_notifications = email_notifications;
    }

    public int getVocab_usage_count() {
        return vocab_usage_count;
    }

    public void setVocab_usage_count(int vocab_usage_count) {
        this.vocab_usage_count = vocab_usage_count;
    }

    public Boolean getIs_banned() {
        return is_banned;
    }

    public void setIs_banned(Boolean is_banned) {
        this.is_banned = is_banned;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}