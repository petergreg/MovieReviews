package com.greg.moviereviews.postgresql.adapter.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.postgresql.adapter.model.MovieEntity;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MovieMapperTest {

  @Mock private ReviewMapper reviewMapper;
  @InjectMocks private MovieMapper movieMapper;

  @Test
  void shouldMapReviewFromEntityToDomain() {
    // Given
    val review = "review";
//    val domainReview = mock(Review.class);
    val title = "title";
    long id = 1L;
    val movieEntity =
        MovieEntity.builder().id(id).title(title).review(review).build();

//    when(reviewMapper.entityToDomain(review)).thenReturn(domainReview);

    // When
    val result = movieMapper.entityToDomain(movieEntity);

    // Then
    assertThat(result)
        .isEqualTo(Movie.builder().id(id).title(title).review(review).build());
  }
}
