package com.greg.moviereviews.rest.mapper;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.model.Review;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MovieMapperTest {

  @Mock private ReviewMapper reviewMapper;
  @InjectMocks private MovieMapper movieMapper;

  @Test
  void shouldMapToApiMovie() {
    // Given
    val apiReview = mock(com.greg.moviereviews.rest.model.Review.class);
    val domainReview = mock(Review.class);
    val apiMovie =
        com.greg.moviereviews.rest.model.Movie.builder()
            .title("title")
            .reviews(List.of(apiReview))
            .build();
    val domainMovie = Movie.builder().title("title").reviews(List.of(domainReview)).build();

    when(reviewMapper.toApiReview(domainReview)).thenReturn(apiReview);

    // When
    val result = movieMapper.toApiMovie(domainMovie);

    // Then
    assertThat(result).isEqualTo(apiMovie);
  }
}
