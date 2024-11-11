package com.greg.moviereviews.postgresql.adapter.mapper;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.postgresql.adapter.model.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MovieMapper {

  private final ReviewMapper reviewMapper;

  public Movie entityToDomain(final MovieEntity movieEntity) {
    return Movie.builder()
        .id(movieEntity.getId())
        .author(movieEntity.getAuthor())
        .title(movieEntity.getTitle())
        .reviews(movieEntity.getReviews().stream().map(reviewMapper::entityToDomain).toList())
        .build();
  }

  public MovieEntity domainToEntity(final Movie movie) {
    val movieEntity =
        MovieEntity.builder().author(movie.getAuthor()).title(movie.getTitle()).build();

    movie
        .getReviews()
        .forEach(review -> movieEntity.addReviewEntity(reviewMapper.domainToEntity(review)));

    return movieEntity;
  }
}
