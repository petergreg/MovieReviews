package com.greg.moviereviews.postgresql.adapter.service;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.model.Review;
import com.greg.moviereviews.domain.port.obtain.IObtainMovie;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieService implements IObtainMovie {

  @Override
  public Optional<Movie> getMovie(final String title) {
    return Optional.of(
        Movie.builder()
            .title("movieTitle")
            .reviews(List.of(Review.builder().reviewBody("reviewBody").build()))
            .build());
  }
}
