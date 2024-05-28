package com.greg.moviereviews.postgresql.adapter.service;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.port.obtain.IObtainMovie;
import com.greg.moviereviews.postgresql.adapter.mapper.MovieMapper;
import com.greg.moviereviews.postgresql.adapter.repository.MovieRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostgresMovieService implements IObtainMovie {

  private final MovieRepository movieRepository;

  private final MovieMapper movieMapper;

  @Override
  public Optional<Movie> getMovie(final String title) {
    return movieRepository.findByTitle(title).map(movieMapper::entityToDomain);
  }

  public Movie createMovie(final Movie movie) {
    return Optional.of(movieRepository.save(movieMapper.domainToEntity(movie)))
        .map(movieMapper::entityToDomain)
        .orElse(null);
  }
}
