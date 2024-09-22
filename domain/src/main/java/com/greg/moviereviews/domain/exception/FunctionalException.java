package com.greg.moviereviews.domain.exception;

public abstract class FunctionalException extends Exception {

  public FunctionalException(final String message) {
    super(message);
  }

  public static class MovieAlreadyExistsException extends FunctionalException {

    public MovieAlreadyExistsException(final String movieTitle, final String movieAuthor) {
      super(String.format("Movie %s by %s already exists", movieTitle, movieAuthor));
    }
  }
}
