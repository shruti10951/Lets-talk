package com.vidyalankar.letstalk.model;

public class FollowingModel {

    Long followedAt;

    public FollowingModel(Long followedAt, String followedTo) {
        this.followedAt = followedAt;
        this.followedTo = followedTo;
    }

    String followedTo;

    public Long getFollowedAt() { return followedAt; }

    public void setFollowedAt(Long followedAt) { this.followedAt = followedAt; }

    public String getFollowedTo() { return followedTo; }

    public void setFollowedTo(String followedTo) { this.followedTo = followedTo; }

    public FollowingModel() {

    }
}
