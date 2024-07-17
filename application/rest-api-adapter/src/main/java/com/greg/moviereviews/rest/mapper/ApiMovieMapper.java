package com.greg.moviereviews.rest.mapper;

import com.greg.moviereviews.rest.model.Movie;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class ApiMovieMapper {

  ApiReviewMapper reviewMapper;

  public Movie toApiMovie(final com.greg.moviereviews.domain.model.Movie domainMovie) {
    return Movie.builder()
        .id(domainMovie.getId())
        .title(domainMovie.getTitle())
        .review(domainMovie.getReview())
        .build();
  }

  public com.greg.moviereviews.domain.model.Movie toDomainMovie(final Movie apiMovie) {
    return com.greg.moviereviews.domain.model.Movie.builder()
        .title(apiMovie.getTitle())
        .review(apiMovie.getReview())
        .build();
  }
}
