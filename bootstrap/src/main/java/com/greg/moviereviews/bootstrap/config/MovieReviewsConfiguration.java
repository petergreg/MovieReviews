package com.greg.moviereviews.bootstrap.config;

import com.greg.moviereviews.domain.port.obtain.IObtainMovie;
import com.greg.moviereviews.domain.port.request.IRequestMovie;
import com.greg.moviereviews.domain.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieReviewsConfiguration {

  @Bean
  public IRequestMovie IRequestMovie(final IObtainMovie iObtainMovie) {
    return new MovieService(iObtainMovie);
  }
}
