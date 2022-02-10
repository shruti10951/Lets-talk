package com.vidyalankar.letstalk.model;

public class HomeModel {

    int profile, save;
    String name, like, comment, post;

    public HomeModel(int profile, String post, int save, String name, String like, String comment) {
        this.profile = profile;
        this.post = post;
        this.save = save;
        this.name = name;
        this.like = like;
        this.comment = comment;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getPostImage() {
        return post;
    }

    public void setPostImage(String post) {
        this.post = post;
    }

    public int getSave() {
        return save;
    }

    public void setSave(int save) {
        this.save = save;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
