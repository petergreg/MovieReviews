package com.greg.moviereviews.rest.mapper;

import com.greg.moviereviews.domain.model.Review;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
public class ApiReviewMapper {
  public com.greg.moviereviews.rest.model.Review toApiReview(final Review domainReview){
    return com.greg.moviereviews.rest.model.Review.builder()
        .author(domainReview.getAuthor())
        .reviewBody(domainReview.getReviewBody())
        .build();
  }
}
