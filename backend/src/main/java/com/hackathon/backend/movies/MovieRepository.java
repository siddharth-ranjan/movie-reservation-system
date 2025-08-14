package com.hackathon.backend.movies;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    Movie save(Movie movie);
    List<Movie> findByTitle(String name);
    List<Movie> findByGenresContaining(Genre genre);
    List<Movie> findAll();
    Optional<Movie> findById(Long id);
}