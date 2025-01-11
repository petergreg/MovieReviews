package com.greg.moviereviews.kafka.mapper;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

import com.greg.moviereviews.domain.model.Review;
import com.greg.moviereviews.kafka.model.KafkaReview;
import lombok.val;
import org.junit.jupiter.api.Test;

class KafkaReviewMapperTest {

  private final KafkaReviewMapper reviewMapper = new KafkaReviewMapper();

  @Test
  void shouldMapToDomainReview() {
    // Given
    val id = randomUUID();
    val author = "author";
    val reviewBody = "reviewBody";
    val kafkaReview = KafkaReview.builder().author(author).reviewBody(reviewBody).build();

    // When
    val result = reviewMapper.toDomainReview(kafkaReview);

    // Then
    assertThat(result)
        .isEqualTo(Review.builder().author(author).reviewBody(reviewBody).build());
  }
}
