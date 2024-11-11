package com.greg.moviereviews.postgresql.adapter.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MovieEntityTest {

  @BeforeEach
  public void setUp() {}

  @Test
  public void testAddReviewEntity() {

    // Given
    val title = "title";
    val author = "author";
    val movieId = UUID.randomUUID();
    val movieEntity = MovieEntity.builder().id(movieId).title(title).author(author).build();
    val reviewId = UUID.randomUUID();
    val reviewEntity =
        ReviewEntity.builder().id(reviewId).author("reviewAuthor").reviewBody("reviewBody").build();

    // When
    movieEntity.addReviewEntity(reviewEntity);

    // Then
    assertThat(movieEntity)
        .isEqualTo(
            MovieEntity.builder()
                .id(movieId)
                .title(title)
                .author(author)
                .reviews(List.of(reviewEntity))
                .build());
  }
}
