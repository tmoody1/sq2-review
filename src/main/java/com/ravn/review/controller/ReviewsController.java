package com.ravn.review.controller;

import com.ravn.review.Comment;
import com.ravn.review.Review;
import com.ravn.review.storage.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableAutoConfiguration

public class ReviewsController {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @GetMapping("/reviews")
    public List<Review> getReviews() {
        return reviewsRepository.findAll();
    }

    @PostMapping("/reviews")
    public Review addReview(Review review) {
        return reviewsRepository.insert(review);
    }

    @GetMapping("/reviews/{reviewId}")
    public Review getReview(@PathVariable String reviewId) {
        return reviewsRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
    }

    @PostMapping("/reviews/{reviewId}/comment")
    public Review addReviewComment(@PathVariable String reviewId, Comment comment) {
        Review review = reviewsRepository.findById(reviewId)
                .orElseThrow(ReviewNotFoundException::new);
        if(review.getComments() == null)
            review.setComments(new ArrayList<>());
        review.getComments().add(comment);
        return reviewsRepository.save(review);
    }

    @PutMapping("/reviews/{reviewId}/like")
    public Review likeReview(@PathVariable String reviewId) {
        Review review = reviewsRepository.findById(reviewId)
                .orElseThrow(ReviewNotFoundException::new);
        review.setLikes(review.getLikes() + 1);
        return reviewsRepository.save(review);
    }

    @GetMapping("/reviewsForIsbn/{isbn}")
    public List<Review> getBookReviews(@PathVariable String isbn) {
        return reviewsRepository.findByIsbn(isbn);
    }
}
