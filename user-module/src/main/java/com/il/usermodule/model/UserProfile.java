package com.il.usermodule.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String first_name;

    @NotNull
    private String last_name;

    private String avatar;

    @NotNull
    private Boolean is_email_notification;

    @NotNull
    private int vocab_usage_count;

    public UserProfile() {}

    private UserProfile(Builder builder) {
        this.first_name = builder.first_name;
        this.last_name = builder.last_name;
        this.avatar = builder.avatar;
        this.is_email_notification = builder.is_email_notification;
        this.vocab_usage_count = builder.vocab_usage_count;
    }

    public static class Builder {
        private String first_name;
        private String last_name;
        private String avatar = null;
        private Boolean is_email_notification = false;
        private int vocab_usage_count = 0;

        public Builder firstName(@NotNull String first_name) {
            this.first_name = first_name;
            return this;
        }

        public Builder lastName(@NotNull String last_name) {
            this.last_name = last_name;
            return this;
        }

        public Builder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder isEmailNotification(@NotNull Boolean is_email_notification) {
            this.is_email_notification = is_email_notification;
            return this;
        }

        public Builder vocabUsageCount(@NotNull int vocab_usage_count) {
            this.vocab_usage_count = vocab_usage_count;
            return this;
        }

        public UserProfile build() {
            return new UserProfile(this);
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public @NotNull String getFirstName() { return first_name; }
    public void setFirstName(@NotNull String first_name) { this.first_name = first_name; }

    public @NotNull String getLastName() { return last_name; }
    public void setLastName(@NotNull String last_name) { this.last_name = last_name; }

    public @NotNull String getAvatar() { return avatar; }
    public void setAvatar(@NotNull String avatar) { this.avatar = avatar; }

    public @NotNull Boolean getIsEmailNotification() { return is_email_notification; }
    public void setIsEmailNotification(@NotNull Boolean is_email_notification) {
        this.is_email_notification = is_email_notification;
    }

    public @NotNull int getVocabUsageCount() { return vocab_usage_count; }
    public void setVocabUsageCount(@NotNull int vocab_usage_count) {
        this.vocab_usage_count = vocab_usage_count;
    }
}