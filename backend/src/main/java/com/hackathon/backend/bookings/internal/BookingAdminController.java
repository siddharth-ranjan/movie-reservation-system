package com.hackathon.backend.bookings.internal;

import com.hackathon.backend.bookings.BookingService;
import com.hackathon.backend.bookings.dto.BookingResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class BookingAdminController {

    private final BookingService bookingService;

    public BookingAdminController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(value = "/bookings")
    public List<BookingResponse> findAllBooking(){
        return bookingService.findAll();
    }

    @GetMapping(value = "/bookings", params = "showtimeId")
    public List<BookingResponse> findAllByShowtime(@RequestParam Long showtimeId) {
        return bookingService.findAllByShowtime(showtimeId);
    }

    @GetMapping(value = "/bookings", params = "bookingId")
    public BookingResponse booking(@RequestParam Long bookingId){
        return bookingService.findById(bookingId);
    }


}
