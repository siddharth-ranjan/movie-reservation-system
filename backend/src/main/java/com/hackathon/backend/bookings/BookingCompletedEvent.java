package com.hackathon.backend.bookings;

public record BookingCompletedEvent(
        Long bookingId,
        Long showtimeId,
        Integer bookedSeats
) {}