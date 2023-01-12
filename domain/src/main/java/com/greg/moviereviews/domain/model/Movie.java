package com.greg.moviereviews.domain.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Movie {

  String title;
  List<Review> reviews;
}
