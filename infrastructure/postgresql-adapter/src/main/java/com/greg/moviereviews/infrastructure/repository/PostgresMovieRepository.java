package com.greg.moviereviews.infrastructure.repository;

import com.greg.moviereviews.infrastructure.model.MovieEntity;

public interface PostgresMovieRepository {

  public MovieEntity find();
}
