package com.greg.moviereviews.rest.controller;

import com.greg.moviereviews.domain.port.request.IRequestMovie;
import com.greg.moviereviews.rest.mapper.ApiMovieMapper;
import com.greg.moviereviews.rest.model.Movie;
import io.swagger.v3.oas.annotations.tags.Tag;
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
  public ResponseEntity<List<Movie>> getMovie(@PathVariable String title) {
    return ResponseEntity.ok(iRequestMovie
        .getMovie(title)
            .stream().map(movieMapper::toApiMovie)
            .toList());
//        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping("/")
  public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
    return Optional.ofNullable(iRequestMovie.createMovie(movieMapper.toDomainMovie(movie)))
        .map(domainMovie -> movieMapper.toApiMovie(domainMovie))
        .map(ResponseEntity::ok)
        .orElseThrow(RuntimeException::new);
  }

  @DeleteMapping("/{title}")
  public ResponseEntity<Void> deleteMovie(@PathVariable String title) {
    return iRequestMovie.deleteMovie(title)
        ? ResponseEntity.noContent().build()
        : ResponseEntity.notFound().build();
  }

  @PutMapping("/{title}")
  public ResponseEntity<Void> updateMovie(@RequestBody Movie movie) {
    return iRequestMovie.updateMovie(movieMapper.toDomainMovie(movie))
        ? ResponseEntity.noContent().build()
        : ResponseEntity.notFound().build();
  }
}
