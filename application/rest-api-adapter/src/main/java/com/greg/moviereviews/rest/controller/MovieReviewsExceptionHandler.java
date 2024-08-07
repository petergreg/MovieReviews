package com.greg.moviereviews.rest.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieReviewsExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handleMovieReviewsException(Exception e) {
        System.out.println("An exception occurred: " + e.getMessage());
    }

}
