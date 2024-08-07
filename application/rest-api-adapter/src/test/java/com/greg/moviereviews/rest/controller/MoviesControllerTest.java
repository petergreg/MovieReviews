package com.greg.moviereviews.rest.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

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
    assertThat(result).isEqualTo(ResponseEntity.ok(apiMovie));
  }

  @Test
  void shouldNotReturn404_whenNoMovie() {
    // Given
    val title = "title";
    when(iRequestMovie.getMovie(title)).thenReturn(Optional.empty());

    // When
    val result = moviesController.getMovie(title);

    // Then
    assertThat(result).isEqualTo(ResponseEntity.notFound().build());
  }

  @Test
  void shouldReturnMovieResponse_whenCreateMovieOk() {
    // Given
    val domainMovie = mock(com.greg.moviereviews.domain.model.Movie.class);
    val apiMovie = mock(Movie.class);

    when(iRequestMovie.createMovie(domainMovie)).thenReturn(domainMovie);
    when(movieMapper.toApiMovie(domainMovie)).thenReturn(apiMovie);
    when(movieMapper.toDomainMovie(apiMovie)).thenReturn(domainMovie);

    // When
    val result = moviesController.createMovie(apiMovie);

    // Then
    assertThat(result).isEqualTo(ResponseEntity.ok(apiMovie));
  }

  @Test
  void shouldReturn204_whenDeleteMovieOk() {
    // Given
    val title = "title";
    when(iRequestMovie.deleteMovie(title)).thenReturn(true);

    // When
    val result = moviesController.deleteMovie(title);

    // Then
    assertThat(result).isEqualTo(noContent().build());
  }

  @Test
  void shouldReturn404_whenDeleteMovieNotOk() {
    // Given
    val title = "title";
    when(iRequestMovie.deleteMovie(title)).thenReturn(false);

    // When
    val result = moviesController.deleteMovie(title);

    // Then
    assertThat(result).isEqualTo(notFound().build());
  }

  @Test
  void shouldReturnMovieResponse_whenUpdateMovieOk() {
    // Given
    val domainMovie = mock(com.greg.moviereviews.domain.model.Movie.class);
    val apiMovie = mock(Movie.class);

    when(movieMapper.toDomainMovie(apiMovie)).thenReturn(domainMovie);
    when(iRequestMovie.updateMovie(domainMovie)).thenReturn(true);

    // When
    val result = moviesController.updateMovie(apiMovie);

    // Then
    assertThat(result).isEqualTo(noContent().build());
  }

  @Test
  void shouldReturn404_whenUpdateMovieNotOk() {
    // Given
    val domainMovie = mock(com.greg.moviereviews.domain.model.Movie.class);
    val apiMovie = mock(Movie.class);

    when(movieMapper.toDomainMovie(apiMovie)).thenReturn(domainMovie);
    when(iRequestMovie.updateMovie(domainMovie)).thenReturn(false);

    // When
    val result = moviesController.updateMovie(apiMovie);

    // Then
    assertThat(result).isEqualTo(notFound().build());
  }
}
