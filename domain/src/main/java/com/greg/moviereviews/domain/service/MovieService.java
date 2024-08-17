package com.greg.moviereviews.domain.service;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.port.obtain.IObtainMovie;
import com.greg.moviereviews.domain.port.request.IRequestMovie;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MovieService implements IRequestMovie {

  private IObtainMovie iObtainMovie;

  @Override
  public List<Movie> getMovie(final String title) {
    return iObtainMovie.getMovie(title);
  }

  @Override
  public Movie createMovie(final Movie movie) {
    return iObtainMovie.createMovie(movie);
  }

  @Override
  public boolean updateMovie(final Movie movie) {
    return iObtainMovie.updateMovie(movie) > 0;
  }

  @Override
  public boolean deleteMovie(final String title) {
    return iObtainMovie.deleteMovie(title) > 0;
  }
}
