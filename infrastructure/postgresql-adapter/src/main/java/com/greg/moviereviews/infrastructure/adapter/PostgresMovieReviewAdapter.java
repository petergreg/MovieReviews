package com.greg.moviereviews.infrastructure.adapter;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.port.driven.IObtainMovie;
import com.greg.moviereviews.infrastructure.repository.PostgresMovieRepository;
import org.springframework.stereotype.Service;

@Service
public class PostgresMovieReviewAdapter implements IObtainMovie {

  private PostgresMovieRepository postgresMovieRepository;

  @Override
  public Movie findMovie(final String title) {
    return postgresMovieRepository.findByTitle(title);
  }
}
