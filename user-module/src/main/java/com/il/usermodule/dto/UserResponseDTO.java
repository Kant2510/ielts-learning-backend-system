package com.il.usermodule.dto;

public class UserResponseDTO {
    private String id;
    private String first_name;
    private String last_name;
    private String avatar;
    private Boolean is_email_notification;
    private int vocab_usage_count;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return first_name; }
    public void setFirstName(String first_name) { this.first_name = first_name; }

    public String getLastName() { return last_name; }
    public void setLastName(String last_name) { this.last_name = last_name; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public Boolean getEmailNotifications() { return is_email_notification; }
    public void setEmailNotifications(Boolean email_notifications) { this.is_email_notification = email_notifications; }

    public int getVocabUsageCount() { return vocab_usage_count; }
    public void setVocabUsageCount(int vocab_usage_count) { this.vocab_usage_count = vocab_usage_count; }
}