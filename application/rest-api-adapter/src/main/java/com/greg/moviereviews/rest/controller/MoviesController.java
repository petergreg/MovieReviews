package com.greg.moviereviews.rest.controller;

import com.greg.moviereviews.domain.exception.FunctionalException;
import com.greg.moviereviews.domain.exception.FunctionalException.MovieDoesNotExistException;
import com.greg.moviereviews.domain.exception.TechnicalException.DatabaseException;
import com.greg.moviereviews.domain.port.request.IRequestMovie;
import com.greg.moviereviews.rest.mapper.ApiMovieMapper;
import com.greg.moviereviews.rest.model.ApiMovie;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "MovieReviews", description = "Movie reviews API")
@RequestMapping("/movies")
@Builder
public class MoviesController {

  private IRequestMovie iRequestMovie;

  private ApiMovieMapper movieMapper;

  @GetMapping("/{title}")
  public ResponseEntity<List<ApiMovie>> getMovie(@PathVariable String title)
      throws DatabaseException {
    return ResponseEntity.ok(
        iRequestMovie.getMovie(title).stream().map(movieMapper::toApiMovie).toList());
  }

  @PostMapping("/")
  public ResponseEntity<ApiMovie> createMovie(@RequestBody ApiMovie movie)
      throws FunctionalException, DatabaseException {
    return Optional.ofNullable(iRequestMovie.createMovie(movieMapper.toDomainMovie(movie)))
        .map(domainMovie -> movieMapper.toApiMovie(domainMovie))
        .map(
            apiMovie ->
                ResponseEntity.created(URI.create("/movies/" + apiMovie.getTitle())).body(apiMovie))
        .orElseThrow(RuntimeException::new);
  }

  @DeleteMapping("/{title}")
  public ResponseEntity<Void> deleteMovie(@PathVariable String title) throws DatabaseException {
    return iRequestMovie.deleteMovie(title)
        ? ResponseEntity.noContent().build()
        : ResponseEntity.notFound().build();
  }

  @PutMapping("/")
  public ResponseEntity<ApiMovie> updateMovie(@RequestBody ApiMovie apiMovie)
      throws DatabaseException, MovieDoesNotExistException {
    return Optional.of(iRequestMovie.updateMovie(movieMapper.toDomainMovie(apiMovie)))
        .map(domainMovie -> movieMapper.toApiMovie(domainMovie))
        .map(ResponseEntity::ok)
        .orElse(null);
  }
}
