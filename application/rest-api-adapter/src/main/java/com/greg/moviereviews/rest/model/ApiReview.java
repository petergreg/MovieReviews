package com.greg.moviereviews.rest.model;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class ApiReview {
  UUID id;
  String author;
  String reviewBody;
}
