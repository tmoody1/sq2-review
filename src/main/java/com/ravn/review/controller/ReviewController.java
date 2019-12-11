package com.ravn.review.controller;

import com.ravn.review.Review;
import com.ravn.review.storage.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration

public class ReviewController {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @GetMapping("/reviews")
    public List<Review> getBookReviews() {
        return reviewsRepository.findAll();
    }

    @PostMapping("/reviews")
    public Review addReview(Review review) {
        return reviewsRepository.insert(review);
    }

    @GetMapping("/reviews/{isbn}")
    public List<Review> getBookReviews(@PathVariable("isbn") String isbn) {
        return reviewsRepository.findByIsbn(isbn);
    }
}
