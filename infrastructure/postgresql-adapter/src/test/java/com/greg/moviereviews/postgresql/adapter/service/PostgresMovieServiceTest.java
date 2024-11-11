package com.greg.moviereviews.postgresql.adapter.service;

import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.greg.moviereviews.domain.exception.FunctionalException.MovieDoesNotExistException;
import com.greg.moviereviews.domain.exception.TechnicalException.DatabaseException;
import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.model.Review;
import com.greg.moviereviews.postgresql.adapter.mapper.MovieMapper;
import com.greg.moviereviews.postgresql.adapter.model.MovieEntity;
import com.greg.moviereviews.postgresql.adapter.model.ReviewEntity;
import com.greg.moviereviews.postgresql.adapter.repository.MovieRepository;
import java.util.ArrayList;
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
  void shouldReturnMovie() throws DatabaseException {
    // Given
    val title = "title";
    val movieEntity = mock(MovieEntity.class);
    val domainMovie = mock(Movie.class);

    when(movieMapper.entityToDomain(movieEntity)).thenReturn(domainMovie);
    when(movieRepository.findByTitle(title)).thenReturn(List.of(movieEntity));

    // When
    val result = postgresMovieService.getMoviesByTitle(title);

    // Then
    assertThat(result).isEqualTo(List.of(domainMovie));
  }

  @Test
  void shouldThrowDatabaseException_whenGetMoviesByTitleFails() {
    // Given
    val title = "title";
    when(movieRepository.findByTitle(title)).thenThrow(new RuntimeException("DB error"));

    // When & Then
    assertThatThrownBy(() -> postgresMovieService.getMoviesByTitle(title))
        .isInstanceOf(DatabaseException.class)
        .hasMessageContaining("Error when getting movie " + title);
  }

  @Test
  void shouldReturnMovieByTitleAndAuthor() throws DatabaseException {
    // Given
    val title = "title";
    val author = "author";
    val movieEntity = mock(MovieEntity.class);
    val domainMovie = mock(Movie.class);

    when(movieMapper.entityToDomain(movieEntity)).thenReturn(domainMovie);
    when(movieRepository.findByTitleAndAuthor(title, author)).thenReturn(Optional.of(movieEntity));

    // When
    val result = postgresMovieService.getMovie(title, author);

    // Then
    assertThat(result).isEqualTo(Optional.of(domainMovie));
  }

  @Test
  void shouldThrowDatabaseException_whenGetMoviesByTitleAndAuthorFails() {
    // Given
    val title = "title";
    val author = "author";
    when(movieRepository.findByTitleAndAuthor(title, author))
        .thenThrow(new RuntimeException("DB error"));

    // When & Then
    assertThatThrownBy(() -> postgresMovieService.getMovie(title, author))
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
    val result = postgresMovieService.getMoviesByTitle(title);

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
  void shouldReturnMovie_whenUpdateMovieIsOk() throws DatabaseException {
    // Given
    val title = "title";
    val author = "author";

    val existingReviewEntity = mock(ReviewEntity.class);
    val existingReviewEntities = new ArrayList<ReviewEntity>();
    existingReviewEntities.add(existingReviewEntity);
    val existingMovieEntity =
        MovieEntity.builder().title(title).author(author).reviews(existingReviewEntities).build();

    val newDomainReview = mock(Review.class);
    val newReviewEntity = mock(ReviewEntity.class);
    val entryDomainMovie =
        Movie.builder().title(title).author(author).reviews(List.of(newDomainReview)).build();
    val entryMovieEntity =
        MovieEntity.builder().title(title).author(author).reviews(List.of(newReviewEntity)).build();

    val domainMovieToReturn = mock(Movie.class);

    when(movieRepository.findByTitleAndAuthor(title, author))
        .thenReturn(Optional.of(existingMovieEntity));
    when(movieMapper.domainToEntity(entryDomainMovie)).thenReturn(entryMovieEntity);
    when(movieRepository.save(existingMovieEntity)).thenReturn(existingMovieEntity);
    when(movieMapper.entityToDomain(existingMovieEntity)).thenReturn(domainMovieToReturn);

    // When
    val result = postgresMovieService.updateMovie(entryDomainMovie);

    // Then
    assertThat(result).isEqualTo(domainMovieToReturn);
  }

  @Test
  void shouldThrowException_whenUpdateMovieFails() throws DatabaseException {
    // Given
    val title = "title";
    val author = "author";

    val existingReviewEntity = mock(ReviewEntity.class);
    val existingReviewEntities = new ArrayList<ReviewEntity>();
    existingReviewEntities.add(existingReviewEntity);
    val existingMovieEntity =
        MovieEntity.builder().title(title).author(author).reviews(existingReviewEntities).build();

    val newDomainReview = mock(Review.class);
    val newReviewEntity = mock(ReviewEntity.class);
    val entryDomainMovie =
        Movie.builder().title(title).author(author).reviews(List.of(newDomainReview)).build();
    val entryMovieEntity =
        MovieEntity.builder().title(title).author(author).reviews(List.of(newReviewEntity)).build();

    when(movieRepository.findByTitleAndAuthor(title, author))
        .thenReturn(Optional.of(existingMovieEntity));
    when(movieMapper.domainToEntity(entryDomainMovie)).thenReturn(entryMovieEntity);
    val rootCause = new RuntimeException("DB error");
    when(movieRepository.save(existingMovieEntity)).thenThrow(rootCause);

    // When & Then
    assertThatThrownBy(() -> postgresMovieService.updateMovie(entryDomainMovie))
        .isInstanceOf(DatabaseException.class)
        .hasMessageContaining(String.format("Error when updating movie %s", title))
        .hasCause(rootCause);
  }
}
