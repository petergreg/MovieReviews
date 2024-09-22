package com.greg.moviereviews.domain.port.obtain;

import com.greg.moviereviews.domain.exception.TechnicalException;
import com.greg.moviereviews.domain.model.Movie;
import java.util.List;

public interface IObtainMovie {

  public List<Movie> getMovie(final String title) throws TechnicalException.DatabaseException;

  public boolean existsByTitleAndAuthor(final String title, final String author) throws TechnicalException.DatabaseException;

  public Movie createMovie(final Movie movie) throws TechnicalException.DatabaseException;

  public int updateMovie(final Movie movie) throws TechnicalException.DatabaseException;

  public int deleteMovie(final String title) throws TechnicalException.DatabaseException;
}
