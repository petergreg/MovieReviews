package com.greg.moviereviews.domain.port.obtain;

import com.greg.moviereviews.domain.exception.FunctionalException.MovieDoesNotExistException;
import com.greg.moviereviews.domain.exception.TechnicalException.DatabaseException;
import com.greg.moviereviews.domain.model.Movie;
import java.util.List;
import java.util.Optional;

public interface IObtainMovie {

  List<Movie> getMoviesByTitle(final String title) throws DatabaseException;

  Optional<Movie> getMovie(String title, String author) throws DatabaseException;

  boolean existsByTitleAndAuthor(final String title, final String author)
      throws DatabaseException;

  Movie createMovie(final Movie movie) throws DatabaseException;

  Movie updateMovie(final Movie movie) throws DatabaseException, MovieDoesNotExistException;

  int deleteMovie(final String title) throws DatabaseException;
}
