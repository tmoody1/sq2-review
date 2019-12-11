package com.ravn.review;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
public class Review implements Serializable {

    @Id
    private String id;
    private String isbn;
    private String author;
    private String text;
    private int likes;
    private List<Comment> comments;
}
