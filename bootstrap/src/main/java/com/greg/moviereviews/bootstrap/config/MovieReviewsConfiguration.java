package com.greg.moviereviews.bootstrap.config;

import com.greg.moviereviews.domain.port.obtain.IObtainMovie;
import com.greg.moviereviews.domain.port.request.IRequestMovie;
import com.greg.moviereviews.domain.service.MetricService;
import com.greg.moviereviews.domain.service.MovieService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieReviewsConfiguration {

//  @Bean
//  @ConditionalOnMissingBean(MeterRegistry.class)
//  public MeterRegistry meterRegistry() {
//    return new SimpleMeterRegistry();
//  }

  @Bean
  public MetricService metricService(final MeterRegistry meterRegistry) {
    return new MetricService(meterRegistry);

  }
  @Bean
  public IRequestMovie IRequestMovie(
      final IObtainMovie iObtainMovie, final MetricService metricService) {
    return new MovieService(iObtainMovie, metricService);
  }
}
