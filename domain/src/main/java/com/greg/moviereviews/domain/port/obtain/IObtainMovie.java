package com.greg.moviereviews.domain.port.obtain;

import com.greg.moviereviews.domain.model.Movie;
import java.util.List;

public interface IObtainMovie {

  public List<Movie> getMovie(final String title);

  public Movie createMovie(final Movie movie);

  public int updateMovie(final Movie movie);

  public int deleteMovie(final String title);
}
