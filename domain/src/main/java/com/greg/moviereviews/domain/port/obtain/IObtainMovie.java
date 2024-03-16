package com.greg.moviereviews.domain.port.obtain;

import com.greg.moviereviews.domain.model.Movie;

public interface IObtainMovie {

    public Movie getMovie(final String title);
}
