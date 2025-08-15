package com.hackathon.backend.bookings.dto;

public record BookingRequest (Long userId, Long showtimeId, Integer bookedSeats) {}
