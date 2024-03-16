package com.greg.moviereviews.domain.port.request;

import com.greg.moviereviews.domain.model.Movie;

public interface IRequestMovie {
  public Movie getMovie(final String title);
}
