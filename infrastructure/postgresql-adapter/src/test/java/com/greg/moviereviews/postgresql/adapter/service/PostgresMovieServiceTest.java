package com.greg.moviereviews.postgresql.adapter.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.postgresql.adapter.mapper.MovieMapper;
import com.greg.moviereviews.postgresql.adapter.model.MovieEntity;
import com.greg.moviereviews.postgresql.adapter.repository.MovieRepository;
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
    when(movieRepository.findByTitle(title)).thenReturn(Optional.of(movieEntity));

    // When
    val result = postgresMovieService.getMovie(title);

    // Then
    assertThat(result).isEqualTo(Optional.of(domainMovie));
  }

  @Test
  void shouldBeEmpty_whenMovieDoesNotExist() {
    // Given
    val title = "title";
    when(movieRepository.findByTitle(title)).thenReturn(Optional.empty());

    // When
    val result = postgresMovieService.getMovie(title);

    // Then
    assertThat(result).isEqualTo(Optional.empty());
  }

  @Test
  void shouldCreateMovie() {
    // Given
    val movieEntity = mock(MovieEntity.class);
    val domainMovie = mock(Movie.class);

    when(movieMapper.domainToEntity(domainMovie)).thenReturn(movieEntity);
    when(movieMapper.entityToDomain(movieEntity)).thenReturn(domainMovie);
    when(movieRepository.save(movieEntity)).thenReturn(movieEntity);

    // When
    val result = postgresMovieService.createMovie(domainMovie);

    // Then
    assertThat(result).isEqualTo(domainMovie);
  }
}
