package com.greg.moviereviews.rest.mapper;

import com.greg.moviereviews.rest.model.Movie;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class MovieMapper {

  ReviewMapper reviewMapper;

  public Movie toApiMovie(final com.greg.moviereviews.domain.model.Movie domainMovie) {
    return Movie.builder()
        .title(domainMovie.getTitle())
        .reviews(domainMovie.getReviews().stream().map(reviewMapper::toApiReview).toList())
        .build();
  }
}
