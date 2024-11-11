package com.greg.moviereviews.domain.service;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import com.greg.moviereviews.domain.exception.FunctionalException;
import com.greg.moviereviews.domain.exception.FunctionalException.MovieAlreadyExistsException;
import com.greg.moviereviews.domain.exception.FunctionalException.MovieDoesNotExistException;
import com.greg.moviereviews.domain.exception.TechnicalException.DatabaseException;
import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.port.obtain.IObtainMovie;
import java.util.List;
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
  void shouldGetMovieByTitle() throws DatabaseException {
    // Given
    val title = "title";
    val movie = Movie.builder().title(title).build();
    when(iObtainMovie.getMoviesByTitle(title)).thenReturn(List.of(movie));

    // When
    val result = movieService.getMovie(title);

    // Then
    assertThat(result).isEqualTo(List.of(movie));
  }

  @Test
  void shouldNotGetMovieByTitle_whenDoesNotExist() throws DatabaseException {
    // Given
    val title = "unknownTitle";
    val movie = Movie.builder().title(title).build();
    when(iObtainMovie.getMoviesByTitle(title)).thenReturn(emptyList());

    // When
    val result = movieService.getMovie(title);

    // Then
    assertThat(result).isEmpty();
  }

  @Test
  void shouldReturnTrueWhenDeleteMovieByTitleIsOK() throws DatabaseException {
    // Given
    val title = "title";
    when(iObtainMovie.deleteMovie(title)).thenReturn(1);

    // When
    val result = movieService.deleteMovie(title);

    // Then
    assertThat(result).isTrue();
  }

  @Test
  void shouldReturnFalseWhenDeleteMovieByTitleNotOk() throws DatabaseException {
    // Given
    val title = "title";
    when(iObtainMovie.deleteMovie(title)).thenReturn(0);

    // When
    val result = movieService.deleteMovie(title);

    // Then
    assertThat(result).isFalse();
  }

  @Test
  void shouldReturnTrueWhenUpdateMovieIsOk() throws DatabaseException, MovieDoesNotExistException {
    // Given
    val title = "title";
    val author = "author";
    val movie = Movie.builder().title(title).author(author).build();
    when(iObtainMovie.existsByTitleAndAuthor(title, author)).thenReturn(true);
    when(iObtainMovie.updateMovie(movie)).thenReturn(movie);

    // When
    val result = movieService.updateMovie(movie);

    // Then
    assertThat(result).isEqualTo(movie);
  }

  @Test
  void shouldThrowException_WhenMovieDoesNotExist()
      throws DatabaseException {
    // Given
    val title = "title";
    val author = "author";
    val movie = Movie.builder().title(title).author(author).build();
    when(iObtainMovie.existsByTitleAndAuthor(title, author)).thenReturn(false);

    // Then
    assertThatThrownBy(() -> movieService.updateMovie(movie))
        .isEqualTo(new MovieDoesNotExistException(title, author));
  }

  @Test
  void shouldCreateMovieWhenMovieDoesNotExist() throws FunctionalException, DatabaseException {
    // Given
    val title = "title";
    val author = "author";
    val movie = Movie.builder().title(title).author(author).build();
    when(iObtainMovie.existsByTitleAndAuthor(title, author)).thenReturn(false);
    when(iObtainMovie.createMovie(movie)).thenReturn(movie);

    // When
    val result = movieService.createMovie(movie);

    // Then
    assertThat(result).isEqualTo(movie);
  }

  @Test
  void shouldThrowMovieAlreadyExistsException_whenMovieAlreadyExists() throws DatabaseException {
    // Given
    val title = "title";
    val author = "author";
    val movie = Movie.builder().title(title).author(author).build();
    when(iObtainMovie.existsByTitleAndAuthor(title, author)).thenReturn(true);

    // Then
    assertThatThrownBy(() -> movieService.createMovie(movie))
        .isInstanceOf(MovieAlreadyExistsException.class)
        .hasMessageContaining(String.format("Movie %s by %s already exists", title, author));
  }
}
