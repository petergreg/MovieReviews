package com.greg.moviereviews.domain.service;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.port.obtain.IObtainMovie;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

  @Mock private IObtainMovie iObtainMovie;

  @InjectMocks private MovieService movieService;

  @Test
  void shouldGetMovieByTitle() {
    // Given
    val title = "title";
    val movie = Movie.builder().title(title).build();
    when(iObtainMovie.getMovie(title)).thenReturn(List.of(movie));

    // When
    val result = movieService.getMovie(title);

    // Then
    assertThat(result).isEqualTo(List.of(movie));
  }

  @Test
  void shouldNotGetMovieByTitle_whenDoesNotExist() {
    // Given
    val title = "unknownTitle";
    val movie = Movie.builder().title(title).build();
    when(iObtainMovie.getMovie(title)).thenReturn(emptyList());

    // When
    val result = movieService.getMovie(title);

    // Then
    assertThat(result).isEmpty();
  }

  @Test
  void shouldReturnTrueWhenDeleteMovieByTitleIsOK() {
    // Given
    val title = "title";
    when(iObtainMovie.deleteMovie(title)).thenReturn(1);

    // When
    val result = movieService.deleteMovie(title);

    // Then
    assertThat(result).isTrue();
  }

  @Test
  void shouldReturnFalseWhenDeleteMovieByTitleNotOk() {
    // Given
    val title = "title";
    when(iObtainMovie.deleteMovie(title)).thenReturn(0);

    // When
    val result = movieService.deleteMovie(title);

    // Then
    assertThat(result).isFalse();
  }

  @Test
  void shouldReturnTrueWhenUpdateMovieIsOk() {
    // Given
    val movie = Movie.builder().title("title").build();
    when(iObtainMovie.updateMovie(movie)).thenReturn(1);

    // When
    val result = movieService.updateMovie(movie);

    // Then
    assertThat(result).isTrue();
  }

  @Test
  void shouldReturnFalseWhenUpdateMovieNotOk() {
    // Given
    val movie = Movie.builder().title("title").build();
    when(iObtainMovie.updateMovie(movie)).thenReturn(0);

    // When
    val result = movieService.updateMovie(movie);

    // Then
    assertThat(result).isFalse();
  }

}
