package com.hackathon.backend.movies;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findMovieByTitle(String title) {
        return movieRepository
                .findByTitle(title);
    }

    public List<Movie> findAllMovies() {
        return movieRepository
                .findAll();
    }

    public List<Movie> findMovieByGenre(Genre genre) {
        return movieRepository
                .findByGenresContaining(genre);
    }
    
    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Movie with id " + id + " is not present!"));
    }
}
