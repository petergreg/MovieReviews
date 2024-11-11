package com.greg.moviereviews.rest.model;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {

  UUID id;
  String title;
  String author;
  String review;
}
