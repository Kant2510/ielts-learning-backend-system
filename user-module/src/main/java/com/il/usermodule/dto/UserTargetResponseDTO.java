package com.il.usermodule.dto;

public class UserTargetResponseDTO {
    private String id;
//    private int duration;
    private float reading;
    private float listening;
    private float writing;
    private float speaking;
    private String nextExamDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public int getDuration() {
//        return duration;
//    }
//
//    public void setDuration(int duration) {
//        this.duration = duration;
//    }

    public float getReading() {
        return reading;
    }

    public void setReading(float reading) {
        this.reading = reading;
    }

    public float getListening() {
        return listening;
    }

    public void setListening(float listening) {
        this.listening = listening;
    }

    public float getWriting() {
        return writing;
    }

    public void setWriting(float writing) {
        this.writing = writing;
    }

    public float getSpeaking() {
        return speaking;
    }

    public void setSpeaking(float speaking) {
        this.speaking = speaking;
    }

    public String getNextExamDate() {
        return nextExamDate;
    }

    public void setNextExamDate(String nextExamDate) {
        this.nextExamDate = nextExamDate;
    }
}
