package com.greg.moviereviews.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.kafka.consumer")
@Component // Mettre le contexte spring pour être utilisé en SPEL (Spring Expression Language)
@Data
public class KafkaMovieConsumerProperties {
    private String movieTopic;
    private String bootstrapServers;
    private String groupId;
}
