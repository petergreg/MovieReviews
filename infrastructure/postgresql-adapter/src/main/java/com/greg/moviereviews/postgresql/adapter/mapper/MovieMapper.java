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
        //        .id(movieEntity.getId())
        .author(movieEntity.getAuthor())
        .title(movieEntity.getTitle())
        .review(movieEntity.getReview())
        .build();
  }

  public MovieEntity domainToEntity(final Movie movie) {
    return MovieEntity.builder()
        .author(movie.getAuthor())
        .title(movie.getTitle())
        .review(movie.getReview())
        .build();
  }
}
