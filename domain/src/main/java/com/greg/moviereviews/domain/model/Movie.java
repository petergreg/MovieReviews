package com.greg.moviereviews.domain.model;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Value
@Builder
public class Movie {
  UUID id;
  String author;
  String title;
  List<Review> reviews;
}
