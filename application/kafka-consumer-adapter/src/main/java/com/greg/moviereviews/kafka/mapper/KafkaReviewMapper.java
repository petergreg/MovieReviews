package com.greg.moviereviews.kafka.mapper;

import com.greg.moviereviews.domain.model.Review;
import com.greg.moviereviews.kafka.model.KafkaReview;
import org.springframework.stereotype.Component;

@Component
public class KafkaReviewMapper {
    public Review toDomainReview(final KafkaReview kafkaReview) {
        return Review.builder()
                .author(kafkaReview.author())
                .reviewBody(kafkaReview.reviewBody())
                .build();
    }
}
