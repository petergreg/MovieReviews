package com.greg.moviereviews.domain.port.request;

import com.greg.moviereviews.domain.exception.FunctionalException;
import com.greg.moviereviews.domain.exception.TechnicalException;
import com.greg.moviereviews.domain.model.Movie;

import java.util.List;
import java.util.Optional;

public interface IRequestMovie {
  public List<Movie> getMovie(final String title) throws TechnicalException.DatabaseException;

  public Movie createMovie(final Movie movie) throws FunctionalException, TechnicalException.DatabaseException;

  public boolean updateMovie(final Movie movie) throws TechnicalException.DatabaseException;

  public boolean deleteMovie(final String title) throws TechnicalException.DatabaseException;
}
