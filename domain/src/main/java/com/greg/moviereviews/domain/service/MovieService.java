package com.greg.moviereviews.domain.service;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.model.Review;
import com.greg.moviereviews.domain.port.obtain.IObtainMovie;
import com.greg.moviereviews.domain.port.request.IRequestMovie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
public class MovieService implements IRequestMovie {

  private IObtainMovie iObtainMovie;

  @Override
  public Movie getMovie(final String title) {
    return iObtainMovie.getMovie(title);
  }
}
