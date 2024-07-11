package com.greg.moviereviews.postgresql.adapter.repository;

import com.greg.moviereviews.postgresql.adapter.model.MovieEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepositoryImplementation<MovieEntity, Long> {

  public Optional<MovieEntity> findByTitle(final String title);

  public int deleteByTitle(final String title);
}
