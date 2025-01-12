package com.greg.moviereviews.rest.controller;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

import com.greg.moviereviews.domain.exception.FunctionalException;
import com.greg.moviereviews.domain.exception.FunctionalException.MovieDoesNotExistException;
import com.greg.moviereviews.domain.exception.TechnicalException.DatabaseException;
import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.port.request.IRequestMovie;
import com.greg.moviereviews.rest.mapper.ApiMovieMapper;
import com.greg.moviereviews.rest.model.ApiMovie;
import java.net.URI;
import java.util.List;
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
  void shouldReturnMovieResponse() throws DatabaseException {
    // Given
    val title = "title";
    val domainMovie = mock(com.greg.moviereviews.domain.model.Movie.class);
    val apiMovie = mock(ApiMovie.class);

    when(iRequestMovie.getMovie(title)).thenReturn(List.of(domainMovie));
    when(movieMapper.toApiMovie(domainMovie)).thenReturn(apiMovie);

    // When
    val result = moviesController.getMovie(title);

    // Then
    assertThat(result).isEqualTo(ResponseEntity.ok(List.of(apiMovie)));
  }

  @Test
  void shouldReturnEmpty_whenNoMovie() throws DatabaseException {
    // Given
    val title = "title";
    when(iRequestMovie.getMovie(title)).thenReturn(emptyList());

    // When
    val result = moviesController.getMovie(title);

    // Then
    assertThat(result).isEqualTo(ResponseEntity.ok(emptyList()));
  }

  @Test
  void shouldReturnMovieResponse_whenCreateMovieOk() throws FunctionalException, DatabaseException {
    // Given
    val domainMovie = mock(com.greg.moviereviews.domain.model.Movie.class);
    val apiMovie = mock(ApiMovie.class);

    when(apiMovie.getTitle()).thenReturn("title");
    when(iRequestMovie.createMovie(domainMovie)).thenReturn(domainMovie);
    when(movieMapper.toApiMovie(domainMovie)).thenReturn(apiMovie);
    when(movieMapper.toDomainMovie(apiMovie)).thenReturn(domainMovie);

    // When
    val result = moviesController.createMovie(apiMovie);

    // Then
    assertThat(result)
        .isEqualTo(ResponseEntity.created(URI.create("/movies/title")).body(apiMovie));
  }

  @Test
  void shouldReturn204_whenDeleteMovieOk() throws DatabaseException {
    // Given
    val title = "title";
    when(iRequestMovie.deleteMovie(title)).thenReturn(true);

    // When
    val result = moviesController.deleteMovie(title);

    // Then
    assertThat(result).isEqualTo(noContent().build());
  }

  @Test
  void shouldReturn404_whenDeleteMovieNotOk() throws DatabaseException {
    // Given
    val title = "title";
    when(iRequestMovie.deleteMovie(title)).thenReturn(false);

    // When
    val result = moviesController.deleteMovie(title);

    // Then
    assertThat(result).isEqualTo(notFound().build());
  }

  @Test
  void shouldReturnMovieResponse_whenUpdateMovieOk()
      throws DatabaseException, MovieDoesNotExistException {
    // Given
    val domainMovie = Movie.builder().title("title").author("author").build();
    val apiMovie = ApiMovie.builder().title("title").author("author").build();

    when(movieMapper.toDomainMovie(apiMovie)).thenReturn(domainMovie);
    when(iRequestMovie.updateMovie(domainMovie)).thenReturn(domainMovie);
    when(movieMapper.toApiMovie(domainMovie)).thenReturn(apiMovie);

    // When
    val result = moviesController.updateMovie(apiMovie);

    // Then
    assertThat(result).isEqualTo(ResponseEntity.ok(apiMovie));
  }
}
