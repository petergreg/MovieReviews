package com.greg.moviereviews.postgresql.adapter.service;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.greg.moviereviews.domain.exception.TechnicalException.DatabaseException;
import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.postgresql.adapter.mapper.MovieMapper;
import com.greg.moviereviews.postgresql.adapter.model.MovieEntity;
import com.greg.moviereviews.postgresql.adapter.repository.MovieRepository;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostgresMovieServiceTest {

  @Mock private MovieRepository movieRepository;
  @Mock private MovieMapper movieMapper;
  @InjectMocks private PostgresMovieService postgresMovieService;

  @Test
  void shouldReturnMovie() throws DatabaseException {
    // Given
    val title = "title";
    val movieEntity = mock(MovieEntity.class);
    val domainMovie = mock(Movie.class);

    when(movieMapper.entityToDomain(movieEntity)).thenReturn(domainMovie);
    when(movieRepository.findByTitle(title)).thenReturn(List.of(movieEntity));

    // When
    val result = postgresMovieService.getMovie(title);

    // Then
    assertThat(result).isEqualTo(List.of(domainMovie));
  }

  @Test
  void shouldThrowDatabaseException_whenGetMovieFails() {
    // Given
    val title = "title";
    when(movieRepository.findByTitle(title)).thenThrow(new RuntimeException("DB error"));

    // When & Then
    assertThatThrownBy(() -> postgresMovieService.getMovie(title))
        .isInstanceOf(DatabaseException.class)
        .hasMessageContaining("Error when getting movie " + title);
  }

  @Test
  void shouldThrowDatabaseException_whenExistsByTitleAndAuthorFails() {
    // Given
    val title = "title";
    val author = "author";
    when(movieRepository.findByTitleAndAuthor(title, author))
        .thenThrow(new RuntimeException("DB error"));

    // When & Then
    assertThatThrownBy(() -> postgresMovieService.existsByTitleAndAuthor(title, author))
        .isInstanceOf(DatabaseException.class)
        .hasMessageContaining(
            String.format("Error when checking if movie %s by %s exists", title, author));
  }

  @Test
  void shouldBeEmpty_whenMovieDoesNotExist() throws DatabaseException {
    // Given
    val title = "title";
    when(movieRepository.findByTitle(title)).thenReturn(emptyList());

    // When
    val result = postgresMovieService.getMovie(title);

    // Then
    assertThat(result).isEqualTo(emptyList());
  }

  @Test
  void shouldCreateMovie() throws DatabaseException {
    // Given
    val movieEntity = mock(MovieEntity.class);
    val title = "title";
    val author = "author";
    val domainMovie = Movie.builder().title(title).author(author).build();

    when(movieMapper.domainToEntity(domainMovie)).thenReturn(movieEntity);
    when(movieMapper.entityToDomain(movieEntity)).thenReturn(domainMovie);
    when(movieRepository.save(movieEntity)).thenReturn(movieEntity);

    // When
    val result = postgresMovieService.createMovie(domainMovie);

    // Then
    assertThat(result).isEqualTo(domainMovie);
  }

  @Test
  void shouldThrowDatabaseException_whenCreateMovieFails() {
    // Given
    val title = "title";
    val author = "author";
    val movie = Movie.builder().title(title).author(author).build();
    when(movieRepository.save(any())).thenThrow(new RuntimeException("DB error"));

    // When & Then
    assertThatThrownBy(() -> postgresMovieService.createMovie(movie))
        .isInstanceOf(DatabaseException.class)
        .hasMessageContaining(String.format("Error when saving movie %s by %s", title, author));
  }

  @Test
  void shouldDeleteMovie() throws DatabaseException {
    // Given
    val title = "title";
    when(movieRepository.deleteByTitle(title)).thenReturn(1);

    // When
    val result = postgresMovieService.deleteMovie(title);

    // Then
    assertThat(result).isEqualTo(1);
  }

  @Test
  void shouldThrowDatabaseException_whenDeleteMovieFails() {
    // Given
    val title = "title";
    when(movieRepository.deleteByTitle(title)).thenThrow(new RuntimeException("DB error"));

    // When & Then
    assertThatThrownBy(() -> postgresMovieService.deleteMovie(title))
        .isInstanceOf(DatabaseException.class)
        .hasMessageContaining("Error when deleting movie " + title);
  }

  @Test
  void shouldReturnMinusOneWhenUpdateMovieReturnsMinusOne() throws DatabaseException {
    // Given
    val movie = Movie.builder().title("title").build();
    val movieEntity = movieMapper.domainToEntity(movie);
    when(movieMapper.domainToEntity(movie)).thenReturn(movieEntity);
    when(movieRepository.updateByTitle(movieEntity)).thenReturn(1);

    // When
    val result = postgresMovieService.updateMovie(movie);

    // Then
    assertThat(result).isEqualTo(1);
  }

  @Test
  void shouldThrowDatabaseException_whenUpdateMovieFails() {
    // Given
    val title = "title";
    val author = "author";
    val movie = Movie.builder().title(title).author(author).build();
    when(movieRepository.updateByTitle(any())).thenThrow(new RuntimeException("DB error"));

    // When & Then
    assertThatThrownBy(() -> postgresMovieService.updateMovie(movie))
        .isInstanceOf(DatabaseException.class)
        .hasMessageContaining(String.format("Error when updating movie %s by %s", title, author));
  }
}
