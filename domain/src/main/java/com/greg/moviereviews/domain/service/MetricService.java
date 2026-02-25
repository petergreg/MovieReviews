package com.greg.moviereviews.domain.service;

import com.greg.moviereviews.domain.model.ActionOnMovieEnum;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Builder
@Slf4j
public class MetricService {

  private static final String MOVIE_COUNT = "action_on_movie";
  public static final String ACTION_TAG = "Action";
  private final MeterRegistry registry;
  public static final String DIRECTION_TAG_KEY = "direction";
  public static final String STATUS_TAG_KEY = "status";

  public void incrementCreateUpdateMovieCounter(final ActionOnMovieEnum actionOnMovieEnum) {
    log.info("Incrementing movie counter for {}", actionOnMovieEnum.name());
    registry
        .counter(
                MOVIE_COUNT,
            List.of(Tag.of(ACTION_TAG, actionOnMovieEnum.toString())))
        .increment();
  }
}
