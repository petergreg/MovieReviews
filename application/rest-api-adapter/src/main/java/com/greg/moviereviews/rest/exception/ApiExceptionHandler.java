package com.greg.moviereviews.rest.exception;

import com.greg.moviereviews.domain.exception.FunctionalException.MovieAlreadyExistsException;
import com.greg.moviereviews.domain.exception.TechnicalException.DatabaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = {MovieAlreadyExistsException.class})
  public ResponseEntity<ApiException> handleException(MovieAlreadyExistsException e) {
    return ResponseEntity.badRequest()
        .body(ApiException.builder().message(e.getMessage()).throwable(e).build());
  }

  @ExceptionHandler(value = {DatabaseException.class})
  public ResponseEntity<ApiException> handleException(DatabaseException e) {
    return ResponseEntity.internalServerError()
        .body(ApiException.builder().message(e.getMessage()).throwable(e).build());
  }
}
