package com.greg.moviereviews.kafka.model;

import java.util.List;

public record KafkaMovie(String title, String author, List<KafkaReview> reviews) {}
