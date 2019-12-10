package com.ravn.review.storage;

import com.ravn.review.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewsRepository extends MongoRepository<Review, String> {

    List<Review> findByIsbn(String isbn);
}
