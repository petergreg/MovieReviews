package com.greg.moviereviews.domain.exception;

public abstract class TechnicalException extends Exception {

  public TechnicalException(final String message, final Throwable throwable) {
    super(message, throwable);
  }

  public static class DatabaseException extends TechnicalException {

    public DatabaseException(final String message, final Throwable throwable) {
      super(String.format("Database Exception: %s", message), throwable);
    }
  }
}
