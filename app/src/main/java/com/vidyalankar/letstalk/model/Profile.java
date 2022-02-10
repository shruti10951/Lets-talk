package com.vidyalankar.letstalk.model;

public class Profile {

    String userID, profilePic;

    public Profile(String userID, String profilePic) {
        this.userID = userID;
        this.profilePic = profilePic;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserID() {
        return userID;
    }

    public String getProfilePic() {
        return profilePic;
    }
}
