package com.greg.moviereviews.rest.mapper;

import static org.assertj.core.api.Assertions.*;

import com.greg.moviereviews.domain.model.Review;
import lombok.val;
import org.junit.jupiter.api.Test;

class ApiReviewMapperTest {

  private final ApiReviewMapper reviewMapper = new ApiReviewMapper();

  @Test
  void shouldMapToApiReview() {
    // Given
    val author = "author";
    val reviewBody = "reviewBody";
    val domainReview = Review.builder().author(author).reviewBody(reviewBody).build();

    // When
    val result = reviewMapper.toApiReview(domainReview);

    // Then
    assertThat(result)
        .isEqualTo(
            com.greg.moviereviews.rest.model.Review.builder()
                .author(author)
                .reviewBody(reviewBody)
                .build());
  }
}
