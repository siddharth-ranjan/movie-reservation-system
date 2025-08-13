package com.hackathon.backend.movies.internal;

import com.hackathon.backend.movies.Movie;
import com.hackathon.backend.movies.MovieRepository;
import com.hackathon.backend.movies.dto.MovieRegister;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/admin")
class MovieAdminController {

    private final MovieRepository movieRepository;

    public MovieAdminController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostMapping("/movie/register")
    Movie saveMovie(@RequestBody MovieRegister movieRegister) {
        return movieRepository
                .save(
                        new Movie(
                                movieRegister.title(),
                                movieRegister.description(),
                                Set.copyOf(movieRegister.genreList()),
                                movieRegister.runtimeInMinutes()
                        )
                );
    }
    
}
