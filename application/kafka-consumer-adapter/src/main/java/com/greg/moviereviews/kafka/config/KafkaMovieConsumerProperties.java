package com.greg.moviereviews.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.kafka.consumer")
@Data
public class KafkaMovieConsumerProperties {
    private String bootstrapServers;
    private String keyDeserializer;
    private String valueDeserializer;
    private String groupId;
}
