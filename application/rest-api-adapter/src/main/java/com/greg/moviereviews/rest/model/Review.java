package com.greg.moviereviews.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
  final String author;
  final String reviewBody;
}
