package com.greg.moviereviews.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
  private final String author;
  private final String reviewBody;
}
