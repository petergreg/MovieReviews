package com.greg.moviereviews.domain.model;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {

  private final UUID id;
  private final String author;
  private final String title;
  private final String review;
}
