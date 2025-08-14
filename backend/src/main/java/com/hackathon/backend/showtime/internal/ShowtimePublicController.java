package com.hackathon.backend.showtime.internal;

import com.hackathon.backend.showtime.ShowtimeService;
import com.hackathon.backend.showtime.internal.dto.ShowtimeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/public")
@RestController
public class ShowtimePublicController {

    private final ShowtimeService showtimeService;

    public ShowtimePublicController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping("/shows/movie")
    public List<ShowtimeDTO> showMovies(@RequestParam Long movieId) {
        return showtimeService.findByMovie(movieId);
    }

    @GetMapping("/shows")
    public List<ShowtimeDTO> showShows() {
        return showtimeService.findAll();
    }
}
