package com.ravn.review.controller;

import com.ravn.review.Review;
import com.ravn.review.storage.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration

public class ReviewController {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @RequestMapping("/reviews")
    public List<Review> getBookReviews() {
        return reviewsRepository.findAll();
    }

//    @RequestMapping("/reviews")
//    @PostMapping
//    public Review addReview(Review review) {
//        return reviewRepository.insert(review);
//    }

    @RequestMapping("/reviews/{isbn}")
    public List<Review> getBookReviews(@PathVariable("isbn") String isbn) {
        return reviewsRepository.findByIsbn(isbn);
    }
}
