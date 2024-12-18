package com.greg.moviereviews.bootstrap;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greg.moviereviews.rest.model.ApiMovie;
import com.greg.moviereviews.rest.model.ApiReview;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("it")
@Testcontainers
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = MovieReviewsApplication.class)
@AutoConfigureMockMvc
public class MovieReviewsApplicationIT {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Container @ServiceConnection
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

  @Test
  public void shouldCreateAndGetMovie() throws Exception {

    // Given

    val title = "title";
    val author = "author";
    val reviewAuthor = "reviewAuthor";
    val reviewBody = "reviewBody";
    val apiReview = ApiReview.builder().author(reviewAuthor).reviewBody(reviewBody).build();
    val movie = ApiMovie.builder().title(title).author(author).reviews(List.of(apiReview)).build();
    val movieJson = objectMapper.writeValueAsString(movie);

    // When & Then
    mockMvc
        .perform(post("/movies/").contentType(APPLICATION_JSON).content(movieJson))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "/movies/title"))
        .andExpect(jsonPath("$.title").value(title))
        .andExpect(jsonPath("$.reviews[0].author").value(reviewAuthor))
        .andExpect(jsonPath("$.reviews[0].reviewBody").value(reviewBody));

    mockMvc
        .perform(get("/movies/title"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].title").value(title))
        .andExpect(jsonPath("$[0].author").value(author))
        .andExpect(jsonPath("$[0].reviews[0].author").value(reviewAuthor))
        .andExpect(jsonPath("$[0].reviews[0].reviewBody").value(reviewBody));
  }

  @Test
  public void shouldCreateAndUpdateMovie() throws Exception {

    // Given
    val title = "title";
    val author = "author";
    val apiReview = ApiReview.builder().author("reviewAuthor").reviewBody("reviewBody").build();
    val newReviewAuthor = "newReviewAuthor";
    val newReviewBody = "newReviewBody";
    val newApiReview =
        ApiReview.builder().author(newReviewAuthor).reviewBody(newReviewBody).build();
    val movie = ApiMovie.builder().title(title).author(author).reviews(List.of(apiReview)).build();
    val newMovie =
        ApiMovie.builder().title(title).author(author).reviews(List.of(newApiReview)).build();
    val movieJson = objectMapper.writeValueAsString(movie);
    val newMovieJson = objectMapper.writeValueAsString(newMovie);

    // When & Then
    mockMvc.perform(post("/movies/").contentType(APPLICATION_JSON).content(movieJson));

    mockMvc
        .perform(put("/movies/").contentType(APPLICATION_JSON).content(newMovieJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value(title))
        .andExpect(jsonPath("$.author").value(author))
        .andExpect(jsonPath("$.reviews[0].author").value(newReviewAuthor))
        .andExpect(jsonPath("$.reviews[0].reviewBody").value(newReviewBody));
  }

  @Test
  public void shouldBe404_whenUpdatintUnknownMovie() throws Exception {

    // Given
    val title = "unknownTitle";
    val author = "unknownAuthor";
    val newReviewAuthor = "newReviewAuthor";
    val newReviewBody = "newReviewBody";
    val newApiReview =
        ApiReview.builder().author(newReviewAuthor).reviewBody(newReviewBody).build();
    val newMovie =
        ApiMovie.builder().title(title).author(author).reviews(List.of(newApiReview)).build();
    val newMovieJson = objectMapper.writeValueAsString(newMovie);

    // When & Then
    mockMvc
        .perform(put("/movies/").contentType(APPLICATION_JSON).content(newMovieJson))
        .andExpect(status().isNotFound());
  }

  @Test
  public void shouldCreateAndDeleteMovie() throws Exception {

    // Given
    val title = "title";
    val author = "author";
    val apiReview = ApiReview.builder().author("reviewAuthor").reviewBody("reviewBody").build();
    val movie = ApiMovie.builder().title(title).author(author).reviews(List.of(apiReview)).build();
    val movieJson = objectMapper.writeValueAsString(movie);

    // When & Then
    mockMvc.perform(post("/movies/").contentType(APPLICATION_JSON).content(movieJson));

    mockMvc.perform(delete("/movies/title")).andExpect(status().isNoContent());

    mockMvc
        .perform(get("/movies/title"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(0));
    ;
  }
}
