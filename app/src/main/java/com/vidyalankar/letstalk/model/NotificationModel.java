package com.vidyalankar.letstalk.model;

public class NotificationModel {

    int profilePic;
    String notificationMessage, time;

    public NotificationModel(int profilePic, String notificationMessage, String time) {

        this.profilePic = profilePic;
        this.notificationMessage = notificationMessage;
        this.time = time;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
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
