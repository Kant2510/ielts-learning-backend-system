package com.il.authmodule.dto;

import java.util.UUID;

public class RegisterResponseDTO {
    private String id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
    private String provider;
    private Boolean is_email_notification;
    private int vocab_usage_count;
    private Boolean is_banned;
    private String role;

    public void setId(UUID id) { this.id = id.toString(); }
    public String getId() { return id; }

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

    public void setFirstName(String first_name) { this.first_name = first_name; }
    public String getFirstName() { return first_name; }

    public void setLastName(String last_name) { this.last_name = last_name; }
    public String getLastName() { return last_name; }

    public void setAvatar(String avatar) { this.avatar = avatar; }
    public String getAvatar() { return avatar; }

    public void setProvider(String provider) { this.provider = provider; }
    public String getProvider() { return provider; }

    public void setIsEmailNotification(Boolean email_notifications) { this.is_email_notification = email_notifications; }
    public Boolean getIsEmailNotification() { return is_email_notification; }

    public void setVocabUsageCount(int vocab_usage_count) { this.vocab_usage_count = vocab_usage_count; }
    public int getVocabUsageCount() { return vocab_usage_count; }

    public void setIsBanned(Boolean is_banned) { this.is_banned = is_banned; }
    public Boolean getIsBanned() { return is_banned; }

    public void setRole(String role) { this.role = role; }
    public String getRole() { return role; }
}
