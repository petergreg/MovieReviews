package com.greg.moviereviews.rest.mappers;

import com.greg.moviereviews.rest.model.Movie;
import lombok.Builder;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Value
@Builder
@Component
public class MovieMapper {

  ReviewMapper reviewMapper;

  public Movie toApiMovie(final com.greg.moviereviews.domain.model.Movie domainMovie){
    return Movie.builder()
        .title(domainMovie.getTitle())
        .reviews(domainMovie.getReviews()
            .stream()
            .map(reviewMapper::toApiReview)
            .toList())
        .build();
  }
}
