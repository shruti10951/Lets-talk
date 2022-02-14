package com.vidyalankar.letstalk.model;

public class FriendsModel {

    private String followedBy;
    private long followedAt;
    private String username;
    int profilepic;

    public String getUsername() {
        return username;
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }
//
    public int getProfilepic() {
        return profilepic;
    }
//
//    public void setProfilepic(int profilepic) {
//        this.profilepic = profilepic;
//    }

    public FriendsModel() {
    }

    public String getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(String followedBy) {
        this.followedBy = followedBy;
    }

    public long getFollowedAt() {
        return followedAt;
    }

    public void setFollowedAt(long followedAt) {
        this.followedAt = followedAt;
    }
}
