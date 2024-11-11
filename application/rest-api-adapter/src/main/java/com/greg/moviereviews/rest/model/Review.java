package com.greg.moviereviews.rest.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class Review {
  Long id;
  String author;
  String reviewBody;
}
