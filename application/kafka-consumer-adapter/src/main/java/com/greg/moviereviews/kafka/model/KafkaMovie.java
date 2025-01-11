package com.greg.moviereviews.kafka.model;

import java.util.List;
import lombok.Builder;

@Builder
public record KafkaMovie(String title, String author, List<KafkaReview> reviews) {}
