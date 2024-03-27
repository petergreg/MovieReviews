package com.greg.moviereviews.postgresql.adapter.mapper;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.postgresql.adapter.model.MovieEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MovieMapper {

  private final ReviewMapper reviewMapper;

  public Movie entityToDomain(final MovieEntity movieEntity) {
    return Movie.builder()
        .title(movieEntity.getTitle())
        .reviews(movieEntity.getReviews().stream().map(reviewMapper::entityToDomain).toList())
        .build();
  }
}
