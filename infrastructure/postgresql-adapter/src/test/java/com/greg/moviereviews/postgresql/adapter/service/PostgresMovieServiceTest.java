package com.greg.moviereviews.postgresql.adapter.service;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.postgresql.adapter.mapper.MovieMapper;
import com.greg.moviereviews.postgresql.adapter.model.MovieEntity;
import com.greg.moviereviews.postgresql.adapter.repository.MovieRepository;
import java.util.List;
import java.util.Optional;
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
  void shouldReturnMovie() {
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
  void shouldBeEmpty_whenMovieDoesNotExist() {
    // Given
    val title = "title";
    when(movieRepository.findByTitle(title)).thenReturn(emptyList());

    // When
    val result = postgresMovieService.getMovie(title);

    // Then
    assertThat(result).isEqualTo(emptyList());
  }

  @Test
  void shouldCreateMovie() {
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
  void shouldDeleteMovie() {
    // Given
    val title = "title";
    when(movieRepository.deleteByTitle(title)).thenReturn(1);

    // When
    val result = postgresMovieService.deleteMovie(title);

    // Then
    assertThat(result).isEqualTo(1);
  }

  @Test
  void shouldReturnMinusOneWhenUpdateMovieReturnsMinusOne() {
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
}
