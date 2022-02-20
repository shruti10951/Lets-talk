package com.vidyalankar.letstalk.model;

public class ChatModel {

    String userId, message;
    Long time;

    public ChatModel() {
    }

    public ChatModel(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public ChatModel(String userId, String message, Long time) {
        this.userId = userId;
        this.message = message;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
