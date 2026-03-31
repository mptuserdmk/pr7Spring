package org.example.demo.model;

public class ReviewsModel {
    private int idReview;
    private int userId;
    private String comment;
    private int rating;

    public ReviewsModel(int idReview, int userId, String comment, int rating) {
        this.idReview = idReview;
        this.userId = userId;
        this.comment = comment;
        this.rating = rating;
    }

    public int getIdReview() {
        return idReview;
    }

    public int getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }

    public int getRating() {
        return rating;
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
