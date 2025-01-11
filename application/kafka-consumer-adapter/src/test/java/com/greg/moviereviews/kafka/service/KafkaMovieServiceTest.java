package com.greg.moviereviews.kafka.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.greg.moviereviews.domain.exception.FunctionalException;
import com.greg.moviereviews.domain.exception.TechnicalException;
import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.port.request.IRequestMovie;
import com.greg.moviereviews.kafka.mapper.KafkaMovieMapper;
import com.greg.moviereviews.kafka.model.KafkaMovie;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class KafkaMovieServiceTest {

  @Mock private IRequestMovie iRequestMovie;

  @Mock private KafkaMovieMapper kafkaMovieMapper;

  @InjectMocks private KafkaMovieService kafkaMovieService;

  @Test
  void shouldConsumeKafkaMovie() throws FunctionalException, TechnicalException.DatabaseException {
    // Given
    val kafkaMovie = mock(KafkaMovie.class);
    val domainMovie = mock(Movie.class);
    when(kafkaMovieMapper.toDomainMovie(kafkaMovie)).thenReturn(domainMovie);
    when(iRequestMovie.createMovie(domainMovie)).thenReturn(domainMovie);

    // When
    kafkaMovieService.consumeMovie(kafkaMovie);

    // Then
    verify(iRequestMovie, times(1)).createMovie(domainMovie);
  }
}
