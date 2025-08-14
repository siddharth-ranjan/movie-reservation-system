package com.hackathon.backend.showtime.internal;

import com.hackathon.backend.showtime.Showtime;
import com.hackathon.backend.showtime.ShowtimeRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaShowtimeRepository extends ShowtimeRepository, JpaRepository<Showtime, Integer> {
}
