package com.hackathon.backend.showtime.internal;

import com.hackathon.backend.showtime.Showtime;
import com.hackathon.backend.showtime.ShowtimeService;
import com.hackathon.backend.showtime.dto.ShowtimeDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class ShowtimeAdminController {

    private final ShowtimeService showtimeService;

    public ShowtimeAdminController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @PostMapping("/create/show")
    ShowtimeDTO createShowTimes(@RequestBody Showtime showtime) {
        return showtimeService
                .save(showtime);
    }

    @PostMapping("/delete/show")
    boolean deleteShowTimes(@RequestParam Long showId) {
        showtimeService.delete(showId);
        return true;
    }
}
