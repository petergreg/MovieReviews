package com.greg.moviereviews.rest.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.greg.moviereviews.domain.port.request.IRequestMovie;
import com.greg.moviereviews.rest.mapper.ApiMovieMapper;
import com.greg.moviereviews.rest.model.Movie;
import java.util.Optional;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class MoviesControllerTest {

  @Mock private ApiMovieMapper movieMapper;
  @Mock private IRequestMovie iRequestMovie;
  @InjectMocks private MoviesController moviesController;

  @Test
  void shouldReturnMovieResponse() {
    // Given
    val title = "title";
    val domainMovie = mock(com.greg.moviereviews.domain.model.Movie.class);
    val apiMovie = mock(Movie.class);

    when(iRequestMovie.getMovie(title)).thenReturn(Optional.of(domainMovie));
    when(movieMapper.toApiMovie(domainMovie)).thenReturn(apiMovie);

    // When
    val result = moviesController.getMovie(title);

    // Then
    assertThat(result).isEqualTo(ResponseEntity.ok(Optional.of(apiMovie)));
  }

  @Test
  void shouldNotReturnMovieResponse_whenNoMovie() {
    // Given
    val title = "title";
    when(iRequestMovie.getMovie(title)).thenReturn(Optional.empty());

    // When
    val result = moviesController.getMovie(title);

    // Then
    assertThat(result).isEqualTo(ResponseEntity.ok(Optional.empty()));
  }
}
