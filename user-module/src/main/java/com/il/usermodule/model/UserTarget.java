package com.il.usermodule.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "student_target")
public class UserTarget {
    @Id
    private UUID id;
    
//    @Min(0)
//    private int duration;

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

//    @OneToOne
//    @JoinColumn(name = "id", nullable = false)
//    private User user;

    public UUID getId() {
        return id;
    }

//    public int getDuration() {
//        return duration;
//    }

    public float getReading() {
        return reading;
    }

    public float getListening() {
        return listening;
    }

    public float getWriting() {
        return writing;
    }

    public float getSpeaking() {
        return speaking;
    }

    public Timestamp getNextExamDate() {
        return nextExamDate;
    }

}