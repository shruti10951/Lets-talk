package com.vidyalankar.letstalk.model;

public class FollowingModel {

    String followedAt;

    public FollowingModel(String followedAt, String followedTo) {
        this.followedAt = followedAt;
        this.followedTo = followedTo;
    }

    String followedTo;

    public String getFollowedAt() { return followedAt; }

    public void setFollowedAt(String followedAt) { this.followedAt = followedAt; }

    public String getFollowedTo() { return followedTo; }

    public void setFollowedTo(String followedTo) { this.followedTo = followedTo; }

    public FollowingModel() {

    }
}
