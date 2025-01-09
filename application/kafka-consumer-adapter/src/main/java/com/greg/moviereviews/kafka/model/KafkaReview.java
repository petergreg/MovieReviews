package com.greg.moviereviews.kafka.model;

import java.util.UUID;

public record KafkaReview(UUID id, String author, String reviewBody) {}
