package com.greg.moviereviews.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
  final String author;
  final String reviewBody;
}
