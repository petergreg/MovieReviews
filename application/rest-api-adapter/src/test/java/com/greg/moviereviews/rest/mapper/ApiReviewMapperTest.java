package com.greg.moviereviews.rest.mapper;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.*;

import com.greg.moviereviews.domain.model.Review;
import com.greg.moviereviews.rest.model.ApiReview;
import java.util.UUID;
import lombok.val;
import org.junit.jupiter.api.Test;

class ApiReviewMapperTest {

  private final ApiReviewMapper reviewMapper = new ApiReviewMapper();

  @Test
  void shouldMapToApiReview() {
    // Given
    val id = randomUUID();
    val author = "author";
    val reviewBody = "reviewBody";
    val domainReview = Review.builder().id(id).author(author).reviewBody(reviewBody).build();

    // When
    val result = reviewMapper.toApiReview(domainReview);

    // Then
    assertThat(result)
        .isEqualTo(ApiReview.builder().id(id).author(author).reviewBody(reviewBody).build());
  }
}
