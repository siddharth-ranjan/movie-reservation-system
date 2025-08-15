package com.hackathon.backend.bookings.dto;

public record BookingResponse (Long bookingId, Long movieId, String movieTitle, Long userId, String userName,
                               String userEmail, Long showtimeId, String theatreName, String screenNumber,
                               Integer bookedSeats, java.math.BigDecimal totalPrice) {
}
