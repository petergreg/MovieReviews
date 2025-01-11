package com.greg.moviereviews.kafka.mapper;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.kafka.model.KafkaMovie;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaMovieMapper {

  private final KafkaReviewMapper reviewMapper;

  public Movie toDomainMovie(final KafkaMovie kafkaMovie) {
    return com.greg.moviereviews.domain.model.Movie.builder()
        .title(kafkaMovie.title())
        .author(kafkaMovie.author())
        .reviews(kafkaMovie.reviews().stream().map(reviewMapper::toDomainReview).toList())
        .build();
  }
}
