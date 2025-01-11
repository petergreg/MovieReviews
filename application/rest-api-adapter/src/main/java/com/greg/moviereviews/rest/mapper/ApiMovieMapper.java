package com.greg.moviereviews.rest.mapper;

import com.greg.moviereviews.rest.model.ApiMovie;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class ApiMovieMapper {

  private final ApiReviewMapper reviewMapper;

  public ApiMovie toApiMovie(final com.greg.moviereviews.domain.model.Movie domainMovie) {
    return ApiMovie.builder()
        .id(domainMovie.getId())
        .author(domainMovie.getAuthor())
        .title(domainMovie.getTitle())
        .reviews(domainMovie.getReviews().stream().map(reviewMapper::toApiReview).toList())
        .build();
  }

  public com.greg.moviereviews.domain.model.Movie toDomainMovie(final ApiMovie apiMovie) {
    return com.greg.moviereviews.domain.model.Movie.builder()
        .title(apiMovie.getTitle())
        .author(apiMovie.getAuthor())
        .reviews(apiMovie.getReviews().stream().map(reviewMapper::toDomainReview).toList())
        .build();
  }
}
