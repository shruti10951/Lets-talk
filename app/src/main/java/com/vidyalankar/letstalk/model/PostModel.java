package com.vidyalankar.letstalk.model;

public class PostModel {

    private String postId;
    private String post;
    private String postedBy;
   // private String postedAt;
    private int postLikes;
    private int commentCount;
    private Long postedAt;
    private String type;

    public PostModel(String postId, String post, String postedBy, int postLikes, int commentCount, Long postedAt, String type) {
        this.postId = postId;
        this.post = post;
        this.postedBy = postedBy;
        this.postLikes = postLikes;
        this.commentCount = commentCount;
        this.postedAt = postedAt;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Long postedAt) {
        this.postedAt = postedAt;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(int postLikes) {
        this.postLikes = postLikes;
    }

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

    public PostModel() {
    }
}