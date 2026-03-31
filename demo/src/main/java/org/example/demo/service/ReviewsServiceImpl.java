package org.example.demo.service;

import org.example.demo.model.ReviewsModel;
import org.example.demo.repository.ReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository repo;

    public ReviewsServiceImpl(ReviewsRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<ReviewsModel> findAll() {
        return repo.findAll();
    }

    @Override
    public ReviewsModel addReview(int userId, String comment, int rating) {
        return repo.add(new ReviewsModel(0, userId, comment, rating));
    }

    @Override
    public void deleteReview(int id) {
        repo.delete(id);
    }
}