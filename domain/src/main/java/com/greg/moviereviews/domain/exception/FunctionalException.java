package com.greg.moviereviews.domain.exception;

abstract class FunctionalException extends Exception {

    public FunctionalException(String message, Throwable cause) {
        super(message, cause);
    }


    public static class MovieAlreadyExists extends FunctionalException {
        public MovieAlreadyExists(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
