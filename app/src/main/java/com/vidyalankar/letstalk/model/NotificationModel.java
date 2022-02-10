package com.vidyalankar.letstalk.model;

public class NotificationModel {

    int profile;
    String notificationMessage, time;

    public NotificationModel(int profile, String notificationMessage, String time) {
        this.profile = profile;
        this.notificationMessage = notificationMessage;
        this.time = time;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        this.notificationMessage = notificationMessage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
