package com.greg.moviereviews.domain.port.request;

import com.greg.moviereviews.domain.exception.FunctionalException;
import com.greg.moviereviews.domain.model.Movie;

import java.util.List;
import java.util.Optional;

public interface IRequestMovie {
  public List<Movie> getMovie(final String title);

  public Movie createMovie(final Movie movie) throws FunctionalException;

  public boolean updateMovie(final Movie movie);

  public boolean deleteMovie(final String title);
}
