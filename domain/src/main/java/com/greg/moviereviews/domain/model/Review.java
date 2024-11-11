package com.greg.moviereviews.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Review {
  Long id;
  String author;
  String reviewBody;
}
