package com.greg.moviereviews.infrastructure.adapter;

import com.greg.moviereviews.domain.model.Movie;
import com.greg.moviereviews.domain.port.driven.IObtainMovie;

public class PostgresMovieReviewAdapter implements IObtainMovie {
    @Override
    public Movie findMovie() {
        return null;
    }
}
