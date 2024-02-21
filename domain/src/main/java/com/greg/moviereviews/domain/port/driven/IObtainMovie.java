package com.greg.moviereviews.domain.port.driven;

import com.greg.moviereviews.domain.model.Movie;

public interface IObtainMovie {

    public Movie findMovie();
}
