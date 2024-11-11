package com.greg.moviereviews.postgresql.adapter.service;

import com.greg.moviereviews.domain.exception.FunctionalException.MovieDoesNotExistException;
import com.greg.moviereviews.domain.exception.TechnicalException.DatabaseException;
import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.port.obtain.IObtainMovie;
import com.greg.moviereviews.postgresql.adapter.mapper.MovieMapper;
import com.greg.moviereviews.postgresql.adapter.repository.MovieRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class PostgresMovieService implements IObtainMovie {

  private final MovieRepository movieRepository;

  private final MovieMapper movieMapper;

  @Override
  public List<Movie> getMoviesByTitle(final String title) throws DatabaseException {
    try {
      return movieRepository.findByTitle(title).stream().map(movieMapper::entityToDomain).toList();
    } catch (Exception e) {
      throw new DatabaseException("Error when getting movie " + title, e);
    }
  }

  // TODO : exposer cette methode ?
  @Override
  public Optional<Movie> getMovie(final String title, final String author)
      throws DatabaseException {
    try {
      return movieRepository.findByTitleAndAuthor(title, author).map(movieMapper::entityToDomain);
    } catch (Exception e) {
      throw new DatabaseException("Error when getting movie " + title, e);
    }
  }

  @Override
  public boolean existsByTitleAndAuthor(final String title, final String author)
      throws DatabaseException {
    try {
      return movieRepository.findByTitleAndAuthor(title, author).isPresent();
    } catch (Exception e) {
      throw new DatabaseException(
          String.format("Error when checking if movie %s by %s exists", title, author), e);
    }
  }

  public Movie createMovie(final Movie movie) throws DatabaseException {
    try {
      return Optional.of(movieRepository.save(movieMapper.domainToEntity(movie)))
          .map(movieMapper::entityToDomain)
          .orElse(null);
    } catch (Exception e) {
      throw new DatabaseException(
          String.format("Error when saving movie %s by %s", movie.getTitle(), movie.getAuthor()),
          e);
    }
  }

  // TODO : a refacto
  public Movie updateMovie(final Movie movie) throws DatabaseException {
    try {
      var optionalExistingMovieEntity =
          movieRepository.findByTitleAndAuthor(movie.getTitle(), movie.getAuthor());
        var movieEntity = optionalExistingMovieEntity.get();
        val newReviews = movieMapper.domainToEntity(movie).getReviews();
        movieEntity.getReviews().clear();
        newReviews.forEach(movieEntity::addReviewEntity);
        return movieMapper.entityToDomain(movieRepository.save(movieEntity));
    } catch (Exception e) {
      throw new DatabaseException(
          String.format("Error when updating movie %s", movie.getTitle()), e);
    }
  }

  public int deleteMovie(final String title) throws DatabaseException {
    try {
      return movieRepository.deleteByTitle(title);
    } catch (Exception e) {
      throw new DatabaseException("Error when deleting movie " + title, e);
    }
  }
}
