package com.il.usermodule.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "student_target")
public class UserTarget {
    @Getter
    @Id
    private UUID id;

    @Column(name = "target_reading")
    @Min(0)
    @Max(9)
    private float reading;

    @Column(name = "target_listening")
    @Min(0)
    @Max(9)
    private float listening;

    @Column(name = "target_writing")
    @Min(0)
    @Max(9)
    private float writing;

    @Column(name = "target_speaking")
    @Min(0)
    @Max(9)
    private float speaking;

    @Column(name = "next_exam_date")
    private Timestamp nextExamDate;

    public UserTarget() {}

    private UserTarget(Builder builder) {
        this.id = builder.id;
        this.reading = builder.reading;
        this.listening = builder.listening;
        this.writing = builder.writing;
        this.speaking = builder.speaking;
        this.nextExamDate = builder.nextExamDate;
    }

    public static class Builder {
        private UUID id;
        private float reading;
        private float listening;
        private float writing;
        private float speaking;
        private Timestamp nextExamDate;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder reading(float reading) {
            this.reading = reading;
            return this;
        }

        public Builder listening(float listening) {
            this.listening = listening;
            return this;
        }

        public Builder writing(float writing) {
            this.writing = writing;
            return this;
        }

        public Builder speaking(float speaking) {
            this.speaking = speaking;
            return this;
        }

        public Builder nextExamDate(Timestamp nextExamDate) {
            this.nextExamDate = nextExamDate;
            return this;
        }

        public UserTarget build() {
            return new UserTarget(this);
        }
    }

    public void logger() {
        System.out.println("User Target ID: " + id);
        System.out.println("Reading Target: " + reading);
        System.out.println("Listening Target: " + listening);
        System.out.println("Writing Target: " + writing);
        System.out.println("Speaking Target: " + speaking);
        System.out.println("Next Exam Date: " + nextExamDate);
    }
}