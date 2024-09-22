package com.greg.moviereviews.domain.service;

import com.greg.moviereviews.domain.exception.FunctionalException.MovieAlreadyExistsException;
import com.greg.moviereviews.domain.exception.TechnicalException.DatabaseException;
import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.port.obtain.IObtainMovie;
import com.greg.moviereviews.domain.port.request.IRequestMovie;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.val;

@AllArgsConstructor
public class MovieService implements IRequestMovie {

  private IObtainMovie iObtainMovie;

  @Override
  public List<Movie> getMovie(final String title) throws DatabaseException {
    return iObtainMovie.getMovie(title);
  }

  @Override
  public Movie createMovie(final Movie movie) throws MovieAlreadyExistsException, DatabaseException {

    val exists = iObtainMovie.existsByTitleAndAuthor(movie.getTitle(), movie.getAuthor());
    if (exists) {
      throw new MovieAlreadyExistsException(
          movie.getTitle(), movie.getAuthor());
    }
    return iObtainMovie.createMovie(movie);
  }

  @Override
  public boolean updateMovie(final Movie movie) throws DatabaseException {
    return iObtainMovie.updateMovie(movie) > 0;
  }

  @Override
  public boolean deleteMovie(final String title) throws DatabaseException {
    return iObtainMovie.deleteMovie(title) > 0;
  }
}
