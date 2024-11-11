package com.greg.moviereviews.domain.model;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Review {
  UUID id;
  String author;
  String reviewBody;
}
