package com.greg.moviereviews.bootstrap.config;

import com.greg.moviereviews.domain.port.request.IRequestMovie;
import com.greg.moviereviews.domain.service.MovieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieReviewsConfiguration {

    @Bean
    public IRequestMovie IRequestMovie() {
        return new MovieService();
    }

}
