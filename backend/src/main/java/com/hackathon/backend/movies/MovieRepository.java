package com.hackathon.backend.movies;

import java.util.List;

public interface MovieRepository {
    Movie save(Movie movie);
    List<Movie> findByTitle(String name);
    List<Movie> findByGenresContaining(Genre genre);
    List<Movie> findAll();
}