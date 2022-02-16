package com.vidyalankar.letstalk.model;

public class PostModel {

    private String postId;
    private String post;
    private String postedBy;
    private Long postedAt;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public Long getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Long postedAt) {
        this.postedAt = postedAt;
    }

    public PostModel() {
    }

    public PostModel(String postId, String post, String postedBy, Long postedAt) {
        this.postId = postId;
        this.post = post;
        this.postedBy = postedBy;
        this.postedAt = postedAt;
    }
}