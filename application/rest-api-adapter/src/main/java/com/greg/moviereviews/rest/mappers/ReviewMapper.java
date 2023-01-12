package com.greg.moviereviews.rest.mappers;

import com.greg.moviereviews.domain.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
  public com.greg.moviereviews.rest.model.Review toApiReview(final Review domainReview){
    return com.greg.moviereviews.rest.model.Review.builder()
        .author(domainReview.getAuthor())
        .reviewBody(domainReview.getReviewBody())
        .build();
  }
}
