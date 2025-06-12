package com.il.usermodule.dto;

import jakarta.validation.constraints.NotBlank;

public class UserRequestDTO {
    @NotBlank(message = "First name is required")
    private String first_name;

    @NotBlank(message = "Last name is required")
    private String last_name;

    private String avatar;

    @NotBlank(message = "Email notification is required")
    private Boolean is_email_notification;

    @NotBlank(message = "Vocab usage is required")
    private int vocab_usage_count;

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

    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public @NotBlank(message = "Email notification is required") Boolean getIsEmailNotification() {
        return is_email_notification;
    }
    public void setIsEmailNotification(@NotBlank(message = "Email notification is required") Boolean is_email_notification) {
        this.is_email_notification = is_email_notification;
    }

    public @NotBlank(message = "Vocab usage is required") int getVocabUsageCount() {
        return vocab_usage_count;
    }
    public void setVocabUsageCount(@NotBlank(message = "Vocab usage is required") int vocab_usage_count) {
        this.vocab_usage_count = vocab_usage_count;
    }
}
