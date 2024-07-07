package com.greg.moviereviews.domain.service;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.port.obtain.IObtainMovie;
import com.greg.moviereviews.domain.port.request.IRequestMovie;
import java.util.Optional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MovieService implements IRequestMovie {

  private IObtainMovie iObtainMovie;

  @Override
  public Optional<Movie> getMovie(final String title) {
    return iObtainMovie.getMovie(title);
  }

  @Override
  public Movie createMovie(final Movie movie) {
    return iObtainMovie.createMovie(movie);
  }

  @Override
  public boolean deleteMovie(final String title) {
    return iObtainMovie.deleteMovie(title) > 0;
  }
}
