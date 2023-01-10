package com.greg.moviereviews.bootstrap;

import com.greg.moviereviews.bootstrap.config.MovieReviewsConfiguration;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication(scanBasePackages = "com.greg.moviereviews")
@Import({MovieReviewsConfiguration.class})
public class MovieReviewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieReviewsApplication.class, args);
	}
}
