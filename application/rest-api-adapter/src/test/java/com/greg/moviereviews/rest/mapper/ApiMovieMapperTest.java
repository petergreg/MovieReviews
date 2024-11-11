package com.greg.moviereviews.rest.mapper;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.model.Review;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ApiMovieMapperTest {

  @Mock private ApiReviewMapper reviewMapper;
  @InjectMocks private ApiMovieMapper movieMapper;

  @Test
  void shouldMapToApiMovie() {
    // Given
    val review = "apiReview";
    val domainReview = mock(Review.class);
    val id = UUID.randomUUID();
    val apiMovie =
        com.greg.moviereviews.rest.model.Movie.builder()
            .id(id)
            .author("author")
            .title("title")
            .review(review)
            .build();
    val domainMovie = Movie.builder().id(id).author("author").title("title").review(review).build();

    //    when(reviewMapper.toApiReview(domainReview)).thenReturn(apiReview);

    // When
    val result = movieMapper.toApiMovie(domainMovie);

    // Then
    assertThat(result).isEqualTo(apiMovie);
  }

  @Test
  void shouldMapToDomainMovie() {
    // Given
    val review = "review";
    val apiMovie =
        com.greg.moviereviews.rest.model.Movie.builder()
            .title("title")
            .author("author")
            .review(review)
            .build();
    val domainMovie = Movie.builder().title("title").author("author").review(review).build();

    //    when(reviewMapper.toDomainReview(apiReview)).thenReturn(domainReview);

    // When
    val result = movieMapper.toDomainMovie(apiMovie);

    // Then
    assertThat(result).isEqualTo(domainMovie);
  }
}
