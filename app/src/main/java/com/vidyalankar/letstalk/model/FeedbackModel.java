package com.vidyalankar.letstalk.model;

public class FeedbackModel {
    String feedback, feedbackBy;
    Long feedbackAt;

    public FeedbackModel(String feedback, String feedbackBy, Long feedbackAt) {
        this.feedback = feedback;
        this.feedbackBy = feedbackBy;
        this.feedbackAt = feedbackAt;
    }

    public FeedbackModel() {
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getFeedbackBy() {
        return feedbackBy;
    }

    public void setFeedbackBy(String feedbackBy) {
        this.feedbackBy = feedbackBy;
    }

    public Long getFeedbackAt() {
        return feedbackAt;
    }

    public void setFeedbackAt(Long feedbackAt) {
        this.feedbackAt = feedbackAt;
    }
}
