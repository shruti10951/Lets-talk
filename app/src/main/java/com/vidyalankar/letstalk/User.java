package com.vidyalankar.letstalk;

import android.net.Uri;

public class User {

    public String username, email, image;
    public Uri profileImage;

    public User() {

    }
    public User(Uri profileImage)
    {
        this.profileImage= profileImage;
    }
    public User(String username, String email)
    {
        this.username= username;
        this.email= email;
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

    public String getImage() {
        return image;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
