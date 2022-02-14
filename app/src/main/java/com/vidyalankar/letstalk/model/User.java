package com.vidyalankar.letstalk.model;

import android.net.Uri;

public class User {

    public String username, email, userID, profilePic;
    int followerCount;

    public User() {
    }
    public User(String username, String email)
    {
        this.username= username;
        this.email= email;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public User(String email)
    {
        this.email= email;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
