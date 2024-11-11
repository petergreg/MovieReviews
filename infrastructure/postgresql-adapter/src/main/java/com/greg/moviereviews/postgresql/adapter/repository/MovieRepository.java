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

  List<MovieEntity> findByTitle(final String title);

  Optional<MovieEntity> findByTitleAndAuthor(final String title, final String author);

  int deleteByTitle(final String title);
}
