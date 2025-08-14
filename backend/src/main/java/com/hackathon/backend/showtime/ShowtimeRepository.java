package com.hackathon.backend.showtime;

import java.util.List;
import java.util.Optional;

public interface ShowtimeRepository {
    Showtime save(Showtime showtime);
    List<Showtime> findByMovieId(Long movieId); // for listing shows
    Optional<Showtime> findById(Long showId); // for booking

    List<Showtime> findAll();

    void deleteById(Long showId);

    boolean existsById(Long showId);
}
