package com.vidyalankar.letstalk.model;

public class ChatModel {

    int profile;
    String name, lastMessage, time;

    public ChatModel(int profile, String name, String lastMessage, String time) {
        this.profile = profile;
        this.name = name;
        this.lastMessage = lastMessage;
        this.time = time;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
