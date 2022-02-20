package com.vidyalankar.letstalk.model;

public class ChatModel {

    String userId, message;
    String time;

    public ChatModel() {
    }

    public ChatModel(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public ChatModel(String userId, String message, String time) {
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
