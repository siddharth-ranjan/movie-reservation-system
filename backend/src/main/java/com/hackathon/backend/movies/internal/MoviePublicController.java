package com.hackathon.backend.movies.internal;

import com.hackathon.backend.movies.Genre;
import com.hackathon.backend.movies.Movie;
import com.hackathon.backend.movies.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MoviePublicController {

    private final MovieService movieService;

    public MoviePublicController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/movies", params = "title")
    List<Movie> getMoviesByTitle(@RequestParam String title) {
        return movieService
                .findMovieByTitle(title);
    }

    @GetMapping(value = "/movies", params = "genre")
    List<Movie> getMoviesByGenre(@RequestParam Genre genre) {
        return movieService
                .findMovieByGenre(genre);
    }

    @GetMapping("/movies")
    List<Movie> getAllMovies() {
        return movieService.findAllMovies();
    }
}
