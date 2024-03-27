package com.greg.moviereviews.postgresql.adapter.repository;

import com.greg.moviereviews.postgresql.adapter.model.MovieEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepositoryImplementation<MovieEntity, String> {
  public Optional<MovieEntity> findByTitle(final String title);
}
