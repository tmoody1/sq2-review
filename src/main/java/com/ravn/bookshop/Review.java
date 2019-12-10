package com.ravn.bookshop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Review implements Serializable {
private String id;
private String author;
private String text;
private int likes;
}
