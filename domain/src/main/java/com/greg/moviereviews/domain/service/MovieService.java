package com.greg.moviereviews.domain.service;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.model.Review;
import com.greg.moviereviews.domain.port.request.IRequestMovie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieService implements IRequestMovie {

  @Override
  public Movie getMovie() {
    return Movie.builder()
        .title("titre")
        .reviews(List.of(
            Review.builder()
                .reviewBody("ertyfgfgghhhhghh")
                .build()))
        .build();
  }
}
