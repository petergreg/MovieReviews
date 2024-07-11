package com.greg.moviereviews.postgresql.adapter.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.greg.moviereviews.domain.model.Review;
import com.greg.moviereviews.postgresql.adapter.model.ReviewEntity;
import lombok.val;
import org.junit.jupiter.api.Test;

public class ReviewMapperTest {

  private final ReviewMapper reviewMapper = new ReviewMapper();

  @Test
  void shouldMapReviewFromEntityToDomain() {
    // Given
    val author = "author";
    val reviewBody = "reviewBody";
    val id = 1L;
    val reviewEntity = ReviewEntity.builder().id(id).author(author).reviewBody(reviewBody).build();

    // When
    val result = reviewMapper.entityToDomain(reviewEntity);

    // Then
    assertThat(result)
        .isEqualTo(Review.builder().id(id).author(author).reviewBody(reviewBody).build());
  }

  @Test
  void shouldMapReviewFromDomainToEntity() {
    // Given
    val author = "author";
    val reviewBody = "reviewBody";
    val id = 1L;
    val domainMovie = Review.builder().id(id).author(author).reviewBody(reviewBody).build();

    // When
    val result = reviewMapper.domainToEntity(domainMovie);

    // Then
    assertThat(result)
        .isEqualTo(ReviewEntity.builder().id(id).author(author).reviewBody(reviewBody).build());
  }
}
