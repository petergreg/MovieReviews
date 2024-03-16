package com.greg.moviereviews.rest.controller;

import com.greg.moviereviews.domain.port.request.IRequestMovie;
import com.greg.moviereviews.rest.mappers.MovieMapper;
import com.greg.moviereviews.rest.model.Movie;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "MovieReviews", description = "Movie reviews API")
@RequestMapping("/movies")
@Builder
public class MoviesController {

  private IRequestMovie iRequestMovie;

  private MovieMapper movieMapper;

  @GetMapping("/{title}")
  public ResponseEntity<Movie> getReview(@PathVariable String title) {
    return ResponseEntity.ok(movieMapper.toApiMovie(iRequestMovie.getMovie(title)));
  }
}
