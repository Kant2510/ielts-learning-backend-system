package com.il.usermodule.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "user_profile")
@NoArgsConstructor
@AllArgsConstructor
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

//    public UserProfile() {}
//
//    private UserProfile(Builder builder) {
//        this.first_name = builder.first_name;
//        this.last_name = builder.last_name;
//        this.avatar = builder.avatar;
//        this.is_email_notification = builder.is_email_notification;
//        this.vocab_usage_count = builder.vocab_usage_count;
//    }
//
//    public static class Builder {
//        private String first_name;
//        private String last_name;
//        private String avatar = null;
//        private Boolean is_email_notification = false;
//        private int vocab_usage_count = 0;
//
//        public Builder firstName(@NotNull String first_name) {
//            this.first_name = first_name;
//            return this;
//        }
//
//        public Builder lastName(@NotNull String last_name) {
//            this.last_name = last_name;
//            return this;
//        }
//
//        public Builder avatar(String avatar_url) {
//            this.avatar = avatar_url;
//            return this;
//        }
//
//        public Builder isEmailNotification(@NotNull Boolean is_email_notification) {
//            this.is_email_notification = is_email_notification;
//            return this;
//        }
//
//        public Builder vocabUsageCount(@NotNull int vocab_usage_count) {
//            this.vocab_usage_count = vocab_usage_count;
//            return this;
//        }
//
//        public UserProfile build() {
//            return new UserProfile(this);
//        }
//    }

    public void logger() {
        System.out.println("User Profile ID: " + id);
        System.out.println("First Name: " + first_name);
        System.out.println("Last Name: " + last_name);
        System.out.println("Avatar: " + avatar);
        System.out.println("Is Email Notification: " + is_email_notification);
        System.out.println("Vocab Usage Count: " + vocab_usage_count);
    }
}