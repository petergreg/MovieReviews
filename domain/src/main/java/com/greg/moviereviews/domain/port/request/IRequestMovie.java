package com.greg.moviereviews.domain.port.request;

import com.greg.moviereviews.domain.exception.FunctionalException;
import com.greg.moviereviews.domain.exception.TechnicalException;
import com.greg.moviereviews.domain.model.Movie;
import java.util.List;

public interface IRequestMovie {
  public List<Movie> getMovie(final String title) throws TechnicalException.DatabaseException;

  public Movie createMovie(final Movie movie)
      throws FunctionalException, TechnicalException.DatabaseException;

  public Movie updateMovie(final Movie movie)
      throws TechnicalException.DatabaseException, FunctionalException.MovieDoesNotExistException;

  public boolean deleteMovie(final String title) throws TechnicalException.DatabaseException;
}
