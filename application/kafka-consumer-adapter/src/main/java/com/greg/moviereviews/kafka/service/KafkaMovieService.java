package com.greg.moviereviews.kafka.service;

import com.greg.moviereviews.kafka.model.KafkaMovie;
import org.springframework.stereotype.Service;

@Service
public class KafkaMovieService {

  // TODO : Consume movie
  public void consumeMovie(final KafkaMovie kafkaMovie) {
    System.out.println("\n\n -- Received Message in group AZD: " + kafkaMovie);
  }
}
