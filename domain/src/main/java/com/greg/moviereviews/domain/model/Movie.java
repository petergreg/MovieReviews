package com.greg.moviereviews.domain.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {

  private final Long id;
  private final String title;
  private final String review;
}
