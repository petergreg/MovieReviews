package com.greg.moviereviews.rest.mapper;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.model.Review;
import com.greg.moviereviews.rest.model.ApiMovie;
import com.greg.moviereviews.rest.model.ApiReview;
import java.util.List;
import java.util.UUID;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ApiMovieMapperTest {

  @Mock private ApiReviewMapper reviewMapper;
  @InjectMocks private ApiMovieMapper movieMapper;

  @Test
  void shouldMapToApiMovie() {
    // Given
    val apiReview = mock(ApiReview.class);
    val domainReview = mock(Review.class);
    val id = UUID.randomUUID();
    val apiMovie =
        com.greg.moviereviews.rest.model.ApiMovie.builder()
            .id(id)
            .author("author")
            .title("title")
            .reviews(List.of(apiReview))
            .build();
    val domainMovie =
        Movie.builder().id(id).author("author").title("title").reviews(List.of(domainReview)).build();

    when(reviewMapper.toApiReview(domainReview)).thenReturn(apiReview);

    // When
    val result = movieMapper.toApiMovie(domainMovie);

    // Then
    assertThat(result).isEqualTo(apiMovie);
  }

  @Test
  void shouldMapToDomainMovie() {
    // Given
    val apiReview = mock(ApiReview.class);
    val domainReview = mock(Review.class);
    val title = "title";
    val author = "author";
    val apiMovie =
        ApiMovie.builder().title(title).author(author).reviews(List.of(apiReview)).build();
    val domainMovie =
        Movie.builder().title(title).author(author).reviews(List.of(domainReview)).build();

    when(reviewMapper.toDomainReview(apiReview)).thenReturn(domainReview);

    // When
    val result = movieMapper.toDomainMovie(apiMovie);

    // Then
    assertThat(result).isEqualTo(domainMovie);
  }
}
