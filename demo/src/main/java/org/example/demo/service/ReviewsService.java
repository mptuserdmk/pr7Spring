package org.example.demo.service;

import org.example.demo.model.ReviewsModel;

import java.util.List;

public interface ReviewsService {
    List<ReviewsModel> findAll();
    ReviewsModel addReview(int userId, String comment, int rating);
    void deleteReview(int id);
}