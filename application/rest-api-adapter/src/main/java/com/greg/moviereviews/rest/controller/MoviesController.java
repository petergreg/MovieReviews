package com.greg.moviereviews.rest.controller;

import com.greg.moviereviews.rest.model.Movie;
import com.greg.moviereviews.rest.model.Review;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(
    name = "MovieReviews",
    description = "Movie reviews API"
)
@RequestMapping("/movies")
public class MoviesController {

  @Operation(
      operationId = "merchProductsList",
      summary = "Merchandising products using query filter",
      tags = {"MerchProducts"},
      responses = {@ApiResponse(
          responseCode = "200",
          description = "Successfully movie reviews",
          content = {@Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = Movie.class
              )
          )}
      ), @ApiResponse(
          responseCode = "400",
          description = "Functional movie reviews error",
          content = {@Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = Movie.class
              )
          )}
      ), @ApiResponse(
          responseCode = "500",
          description = "Unexpected error",
          content = {@Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = Movie.class
              )
          )}
      )},
      security = {@SecurityRequirement(
          name = "bearerAuth"
      )}
  )
@GetMapping("/{title}")
  public ResponseEntity<Movie> getMovie(@PathVariable String title){
    return ResponseEntity.ok(

        Movie.builder()
            .title(title)
            .reviews(List.of(
                Review.builder()
                    .author("author")
                    .reviewBody("review").build()
            )).build()
    );
  }
}
