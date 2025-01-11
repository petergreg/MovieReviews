package com.greg.moviereviews.kafka.service;

import com.greg.moviereviews.domain.exception.FunctionalException;
import com.greg.moviereviews.domain.exception.TechnicalException.DatabaseException;
import com.greg.moviereviews.domain.port.request.IRequestMovie;
import com.greg.moviereviews.kafka.mapper.KafkaMovieMapper;
import com.greg.moviereviews.kafka.model.KafkaMovie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaMovieService {

  private final IRequestMovie iRequestMovie;
  private final KafkaMovieMapper kafkaMovieMapper;

  public void consumeMovie(final KafkaMovie kafkaMovie) throws FunctionalException, DatabaseException {
    log.info("\n -- Received Message from kafka: {}", kafkaMovie);
    iRequestMovie.createMovie(kafkaMovieMapper.toDomainMovie(kafkaMovie));
  }
}
