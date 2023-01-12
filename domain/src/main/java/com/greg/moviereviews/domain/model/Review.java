package com.greg.moviereviews.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Review {
  String author;
  String reviewBody;
}
