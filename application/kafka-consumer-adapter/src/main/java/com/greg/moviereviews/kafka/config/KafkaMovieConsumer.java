package com.greg.moviereviews.kafka.config;

import com.greg.moviereviews.kafka.model.KafkaMovie;
import com.greg.moviereviews.kafka.service.KafkaMovieService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KafkaMovieConsumer {

    private final KafkaMovieService kafkaMovieService;

    @KafkaListener(
            topics = "#{kafkaMovieConsumerProperties.movieTopic}",
            groupId = "#{kafkaMovieConsumerProperties.groupId}",
            containerFactory = "kafkaListenerContainerFactory")
    public void kafkaMovieListener(KafkaMovie kafkaMovie) {
        kafkaMovieService.consumeMovie(kafkaMovie);
    }
}
