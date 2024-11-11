package com.greg.moviereviews.domain.model;

import java.util.UUID;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Movie {
  UUID id;
  String author;
  String title;
  String review;
}
