package com.greg.moviereviews.postgresql.adapter.mapper;

import com.greg.moviereviews.domain.model.Review;
import com.greg.moviereviews.postgresql.adapter.model.ReviewEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewMapper {

  public Review entityToDomain(final ReviewEntity reviewEntity) {
    return Review.builder()
        .id(reviewEntity.getId())
        .author(reviewEntity.getAuthor())
        .reviewBody(reviewEntity.getReviewBody())
        .build();
  }

  public ReviewEntity domainToEntity(final Review domainReview) {
    return ReviewEntity.builder()
        .id(domainReview.getId())
        .author(domainReview.getAuthor())
        .reviewBody(domainReview.getReviewBody())
        .build();
  }
}
