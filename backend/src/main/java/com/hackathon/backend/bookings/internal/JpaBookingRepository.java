package com.hackathon.backend.bookings.internal;

import com.hackathon.backend.bookings.Booking;
import com.hackathon.backend.bookings.BookingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookingRepository extends BookingRepository, JpaRepository<Booking, Long> {
}
