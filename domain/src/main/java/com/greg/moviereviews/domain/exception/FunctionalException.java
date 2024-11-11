package com.greg.moviereviews.domain.exception;

import lombok.EqualsAndHashCode;

public abstract class FunctionalException extends Exception {

  public FunctionalException(final String message) {
    super(message);
  }

  @EqualsAndHashCode(callSuper = false)
  public static class MovieAlreadyExistsException extends FunctionalException {

    public MovieAlreadyExistsException(final String movieTitle, final String movieAuthor) {
      super(String.format("Movie %s by %s already exists", movieTitle, movieAuthor));
    }
  }

  @EqualsAndHashCode(callSuper = false)
  public static class MovieDoesNotExistException extends FunctionalException {

    public MovieDoesNotExistException(final String movieTitle, final String movieAuthor) {
      super(String.format("Movie %s by %s does not exist", movieTitle, movieAuthor));
    }
  }
}
