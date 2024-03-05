package com.greg.moviereviews.infrastructure.repository;

import com.greg.moviereviews.domain.model.Movie;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresMovieRepository extends JpaRepositoryImplementation<Movie, String> {

  public Movie findByTitle(final String title);
}
