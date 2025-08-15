package com.hackathon.backend.bookings.internal;

import com.hackathon.backend.bookings.BookingService;
import com.hackathon.backend.bookings.dto.BookingRequest;
import com.hackathon.backend.bookings.dto.BookingResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public BookingResponse book(@RequestBody BookingRequest bookingRequest, Authentication authentication) {
        String username = authentication.getName();
        return bookingService.create(username, bookingRequest);
    }

    @GetMapping("/myBookings")
    public List<BookingResponse> myBookings(Authentication authentication){
        String username = authentication.getName();
        return bookingService.findAllBookingByUser(username);
    }

    @GetMapping(value = "/myBookings", params = "bookingId")
    public BookingResponse myBookingsForMovie(Authentication authentication, @RequestParam Long bookingId){
        String username = authentication.getName();
        return bookingService.findByIdForUser(username, bookingId);
    }
}
