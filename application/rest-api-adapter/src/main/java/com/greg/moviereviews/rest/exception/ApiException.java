package com.greg.moviereviews.rest.exception;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Builder
public record ApiException(String message, Throwable throwable) {}
