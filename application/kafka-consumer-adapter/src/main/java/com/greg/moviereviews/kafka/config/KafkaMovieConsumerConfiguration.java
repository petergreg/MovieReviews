package com.greg.moviereviews.kafka.config;

import com.greg.moviereviews.kafka.model.KafkaMovie;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(KafkaMovieConsumerProperties.class)
public class KafkaMovieConsumerConfiguration {

  private final KafkaMovieConsumerProperties kafkaMovieConsumerProperties;

  @Bean
  public ConsumerFactory<String, KafkaMovie> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        kafkaMovieConsumerProperties.getBootstrapServers());
    props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaMovieConsumerProperties.getGroupId());
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    return new DefaultKafkaConsumerFactory<>(
        props, new StringDeserializer(), new JsonDeserializer<>(KafkaMovie.class));
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, KafkaMovie>
      kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, KafkaMovie> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
