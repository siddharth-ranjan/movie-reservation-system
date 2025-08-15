package com.hackathon.backend.showtime.internal;

import com.hackathon.backend.bookings.BookingCompletedEvent;
import com.hackathon.backend.showtime.Showtime;
import com.hackathon.backend.showtime.ShowtimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class BookingEventListener {

    private final ShowtimeRepository showtimeRepository;

    public BookingEventListener(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    @Async
    @Transactional
    @EventListener
    void on(BookingCompletedEvent event) {
        System.out.println("Received booking completed event: " + event);
        Showtime showtime = showtimeRepository.findById(event.showtimeId()).orElseThrow(
                () -> new IllegalArgumentException("Showtime not found")
        );

        int updatedAvailableSeats = showtime.getAvailableSeats() - event.bookedSeats();
        showtime.setAvailableSeats(updatedAvailableSeats);

        showtimeRepository.save(showtime);

        System.out.println("Updated available seats for showtime " + showtime.getId() + " to " + updatedAvailableSeats);
    }

}