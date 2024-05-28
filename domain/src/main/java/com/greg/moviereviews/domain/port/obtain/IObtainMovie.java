package com.greg.moviereviews.domain.port.obtain;

import com.greg.moviereviews.domain.model.Movie;

import java.util.Optional;

public interface IObtainMovie {
    public Optional<Movie> getMovie(final String title);
    public Movie createMovie(final Movie movie);
}
