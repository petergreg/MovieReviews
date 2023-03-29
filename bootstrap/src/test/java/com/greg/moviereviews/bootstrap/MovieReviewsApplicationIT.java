package com.greg.moviereviews.bootstrap;

import com.greg.moviereviews.bootstrap.config.MovieReviewApplicationTestConfiguration;
import com.greg.moviereviews.rest.model.Movie;
import com.greg.moviereviews.rest.model.Review;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Import(MovieReviewApplicationTestConfiguration.class)
class MovieReviewsApplicationIT {

    @Autowired
    protected WebTestClient webTestClient;

    @Test
    void should_get_review() throws Exception {

        val expectedResult = Movie.builder()
                .title("movieTitle")
                .reviews(List.of(
                        Review.builder()
                                .reviewBody("reviewBody")
                                .build()))
                .build();

        webTestClient
                .get()
                .uri("/movies/randomTitle")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Movie.class).isEqualTo(expectedResult)
        ;
    }

}
