package com.greg.moviereviews.kafka.model;

import lombok.Builder;

import java.util.UUID;

@Builder
public record KafkaReview(String author, String reviewBody) {}
