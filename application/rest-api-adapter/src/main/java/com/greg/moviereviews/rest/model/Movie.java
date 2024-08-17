package com.greg.moviereviews.rest.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Movie {

  String title;
  String author;
//  Long id;
  String review;
}
