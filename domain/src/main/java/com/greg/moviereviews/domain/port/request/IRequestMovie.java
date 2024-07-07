package com.greg.moviereviews.domain.port.request;

import com.greg.moviereviews.domain.model.Movie;

import java.util.Optional;

public interface IRequestMovie {
  public Optional<Movie> getMovie(final String title);
  public Movie createMovie(final Movie movie);
  public boolean deleteMovie(final String title);
}
