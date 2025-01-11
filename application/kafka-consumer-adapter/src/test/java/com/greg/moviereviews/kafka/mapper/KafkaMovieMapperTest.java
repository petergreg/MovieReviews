package com.greg.moviereviews.kafka.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.model.Review;
import com.greg.moviereviews.kafka.model.KafkaMovie;
import com.greg.moviereviews.kafka.model.KafkaReview;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class KafkaMovieMapperTest {

  @Mock private KafkaReviewMapper reviewMapper;

  @InjectMocks private KafkaMovieMapper movieMapper;

  @Test
  void shouldMapToDomainMovie() {
    // Given
    val title = "title";
    val author = "author";
    val kafkaReview = mock(KafkaReview.class);
    val domainReview = mock(Review.class);
    val kafkaMovie =
        KafkaMovie.builder().title(title).author(author).reviews(List.of(kafkaReview)).build();

    when(reviewMapper.toDomainReview(kafkaReview)).thenReturn(domainReview);

    // When
    val result = movieMapper.toDomainMovie(kafkaMovie);

    // Then
    assertThat(result)
        .isEqualTo(
            Movie.builder().title(title).author(author).reviews(List.of(domainReview)).build());
  }
}
