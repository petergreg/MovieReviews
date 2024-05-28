package com.greg.moviereviews.rest.mapper;

import com.greg.moviereviews.domain.model.Review;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class ApiReviewMapper {
  public com.greg.moviereviews.rest.model.Review toApiReview(final Review domainReview) {
    return com.greg.moviereviews.rest.model.Review.builder()
        .id(domainReview.getId())
        .author(domainReview.getAuthor())
        .reviewBody(domainReview.getReviewBody())
        .build();
  }

  public Review toDomainReview(final com.greg.moviereviews.rest.model.Review apiReview) {
    return Review.builder()
        .id(apiReview.getId())
        .author(apiReview.getAuthor())
        .reviewBody(apiReview.getReviewBody())
        .build();
  }
}
