package org.example.demo.repository;


import org.example.demo.model.ReviewsModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ReviewsRepository {

    private final List<ReviewsModel> reviews = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<ReviewsModel> findAll() {
        return new ArrayList<>(reviews);
    }

    public ReviewsModel add(ReviewsModel review) {
        review.setIdReview(idCounter.getAndIncrement());
        reviews.add(review);
        return review;
    }

    public void delete(int id) {
        reviews.removeIf(r -> r.getIdReview() == id);
    }
}
