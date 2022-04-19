package com.vidyalankar.letstalk.model;

public class FriendsModel {

    private String followedBy;
    private Long followedAt;

    public FriendsModel() {
    }

    public String getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(String followedBy) {
        this.followedBy = followedBy;
    }

    public Long getFollowedAt() {
        return followedAt;
    }

    public void setFollowedAt(Long followedAt) {
        this.followedAt = followedAt;
    }
}
