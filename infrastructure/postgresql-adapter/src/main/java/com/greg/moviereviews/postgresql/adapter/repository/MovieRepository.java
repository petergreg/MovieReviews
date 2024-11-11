package com.greg.moviereviews.postgresql.adapter.repository;

import com.greg.moviereviews.postgresql.adapter.model.MovieEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepositoryImplementation<MovieEntity, Long> {

  public List<MovieEntity> findByTitle(final String title);

  public Optional<MovieEntity> findByTitleAndAuthor(final String title, final String author);

  public int deleteByTitle(final String title);

  @Modifying
  @Query("UPDATE MovieEntity m SET m.review = :#{#movie.review} WHERE m.title = :#{#movie.title} AND m.author = :#{#movie.author}")
  int updateByTitleAndAuthor(MovieEntity movie);

}
