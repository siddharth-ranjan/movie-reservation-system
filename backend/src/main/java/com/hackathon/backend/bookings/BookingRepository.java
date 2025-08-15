package com.hackathon.backend.bookings;

import com.hackathon.backend.bookings.dto.BookingResponse;
import com.hackathon.backend.movies.Movie;
import com.hackathon.backend.showtime.Showtime;
import com.hackathon.backend.users.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    Optional<Booking> findById(Long id);
    List<Booking> findAll();
    Booking save(Booking booking);

    List<Booking> findAllByUser(User user);

    List<Booking> findAllByShowtime(Showtime showtime);
}
