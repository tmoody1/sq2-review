package com.ravn.review;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Comment {
    private String id;
    private String author;
    private String text;
}
