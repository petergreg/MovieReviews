package com.greg.moviereviews.postgresql.adapter.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.model.Review;
import com.greg.moviereviews.postgresql.adapter.model.MovieEntity;
import com.greg.moviereviews.postgresql.adapter.model.ReviewEntity;
import java.util.List;
import java.util.UUID;
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
  void shouldMapMovieFromEntityToDomain() {
    // Given
    val reviewEntity = mock(ReviewEntity.class);
    val domainReview = mock(Review.class);
    val id = UUID.randomUUID();
    val title = "title";
    val movieEntity =
        MovieEntity.builder()
            .id(id)
            .author("author")
            .title(title)
            .reviews(List.of(reviewEntity))
            .build();

    when(reviewMapper.entityToDomain(reviewEntity)).thenReturn(domainReview);

    // When
    val result = movieMapper.entityToDomain(movieEntity);

    // Then
    assertThat(result)
        .isEqualTo(
            Movie.builder()
                .id(id)
                .author("author")
                .title(title)
                .reviews(List.of(domainReview))
                .build());
  }

  @Test
  void shouldMapMovieFromDomainToEntity() {
    // Given
    val reviewEntity = mock(ReviewEntity.class);
    val domainReview = mock(Review.class);
    val title = "title";
    val author = "author";
    val domainEntity =
        Movie.builder().author(author).title(title).reviews(List.of(domainReview)).build();

    when(reviewMapper.domainToEntity(domainReview)).thenReturn(reviewEntity);

    // When
    val result = movieMapper.domainToEntity(domainEntity);

    // Then
    assertThat(result)
        .isEqualTo(
            MovieEntity.builder()
                .author(author)
                .title(title)
                .reviews(List.of(reviewEntity))
                .build());
  }
}
