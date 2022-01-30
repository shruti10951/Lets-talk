package com.vidyalankar.letstalk;

public class User {

    public String username, email, image;

    public User() {

    }

    public User(String username, String email)
    {
        this.username= username;
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
