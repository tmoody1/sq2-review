package com.ravn.bookshop.controller;

import com.ravn.bookshop.Review;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@EnableAutoConfiguration

public class ReviewController {

    @RequestMapping("/reviews")
    public List<Review> getAll(){
        return Arrays.asList(Review.builder().text("Nothing here yet...").build());
    }
}
