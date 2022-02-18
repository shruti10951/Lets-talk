package com.vidyalankar.letstalk.model;

public class NotificationModel {

    private String notificationBy;
    private Long notificationAt;
    private String type;
    private String postId;
    private String notificationId;
    private String postedBy;
    private boolean checkOpen;

    public String getNotificationBy() {
        return notificationBy;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public void setNotificationBy(String notificationBy) {
        this.notificationBy = notificationBy;
    }

    public Long getNotificationAt() {
        return notificationAt;
    }

    public void setNotificationAt(Long notificationAt) {
        this.notificationAt = notificationAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public boolean isCheckOpen() {
        return checkOpen;
    }

    public void setCheckOpen(boolean checkOpen) {
        this.checkOpen = checkOpen;
    }
}
