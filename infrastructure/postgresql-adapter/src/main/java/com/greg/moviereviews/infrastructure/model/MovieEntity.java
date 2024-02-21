package com.greg.moviereviews.infrastructure.model;

import com.greg.moviereviews.domain.model.Review;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MovieEntity {
  String title;
  List<Review> reviews;
}
