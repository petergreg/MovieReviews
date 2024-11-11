package com.greg.moviereviews.rest.mapper;

import com.greg.moviereviews.domain.model.Review;
import com.greg.moviereviews.rest.model.ApiReview;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class ApiReviewMapper {
  public ApiReview toApiReview(final Review domainReview) {
    return ApiReview.builder()
        .id(domainReview.getId())
        .author(domainReview.getAuthor())
        .reviewBody(domainReview.getReviewBody())
        .build();
  }

  public Review toDomainReview(final ApiReview apiReview) {
    return Review.builder()
        .id(apiReview.getId())
        .author(apiReview.getAuthor())
        .reviewBody(apiReview.getReviewBody())
        .build();
  }
}
