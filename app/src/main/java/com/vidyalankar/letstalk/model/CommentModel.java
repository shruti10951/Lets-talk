package com.vidyalankar.letstalk.model;

public class CommentModel {
    
    private String comment;
    private Long commentedAt;
    private String commentedBy;

    public CommentModel() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCommentedAt() {
        return commentedAt;
    }

    public void setCommentedAt(Long commentedAt) {
        this.commentedAt = commentedAt;
    }

    public String getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(String commentedBy) {
        this.commentedBy = commentedBy;
    }
}
