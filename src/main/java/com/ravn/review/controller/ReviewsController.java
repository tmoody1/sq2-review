package com.ravn.review.controller;

import com.ravn.review.Comment;
import com.ravn.review.Review;
import com.ravn.review.storage.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableAutoConfiguration

public class ReviewsController {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @GetMapping(value = "/reviews", produces = { MediaType.APPLICATION_JSON_VALUE})
    public List<Review> getReviews() {
        return reviewsRepository.findAll();
    }

    @PostMapping(value = "/reviews", produces = { MediaType.APPLICATION_JSON_VALUE}, consumes = { MediaType.APPLICATION_JSON_VALUE})
    public Review addReview(@RequestBody Review review) {
        return reviewsRepository.insert(review);
    }

    @GetMapping(value = "/reviews/{reviewId}", produces = { MediaType.APPLICATION_JSON_VALUE})
    public Review getReview(@PathVariable String reviewId) {
        return reviewsRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
    }

    @PostMapping(value = "/reviews/{reviewId}/comment", produces = { MediaType.APPLICATION_JSON_VALUE}, consumes = { MediaType.APPLICATION_JSON_VALUE})
    public Review addReviewComment(@PathVariable String reviewId, @RequestBody Comment comment) {
        Review review = reviewsRepository.findById(reviewId)
                .orElseThrow(ReviewNotFoundException::new);
        if(review.getComments() == null)
            review.setComments(new ArrayList<>());
        review.getComments().add(comment);
        return reviewsRepository.save(review);
    }

    @PutMapping(value = "/reviews/{reviewId}/like", produces = { MediaType.APPLICATION_JSON_VALUE})
    public Review likeReview(@PathVariable String reviewId) {
        Review review = reviewsRepository.findById(reviewId)
                .orElseThrow(ReviewNotFoundException::new);
        review.setLikes(review.getLikes() + 1);
        return reviewsRepository.save(review);
    }

    @GetMapping(value = "/reviewsForIsbn/{isbn}", produces = { MediaType.APPLICATION_JSON_VALUE})
    public List<Review> getBookReviews(@PathVariable String isbn) {
        return reviewsRepository.findByIsbn(isbn);
    }
}
