package com.hackathon.backend.bookings;

import com.hackathon.backend.bookings.dto.BookingRequest;
import com.hackathon.backend.bookings.dto.BookingResponse;
import com.hackathon.backend.showtime.Showtime;
import com.hackathon.backend.showtime.ShowtimeService;
import com.hackathon.backend.users.User;
import com.hackathon.backend.users.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final ShowtimeService showtimeService;
    private ApplicationEventPublisher publisher;

    public BookingService(
            BookingRepository bookingRepository,
            UserService userService,
            ShowtimeService showtimeService,
            ApplicationEventPublisher publisher
    ) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.showtimeService = showtimeService;
        this.publisher = publisher;
    }

    public BookingResponse create(String username, BookingRequest bookingRequest) {
        Showtime showtime = showtimeService.findShowtimeEntityById(bookingRequest.showtimeId());
        User user = userService.findByUsername(username);

        if (showtime.getAvailableSeats() < bookingRequest.bookedSeats()) {
            throw new IllegalStateException("Not enough available seats for this showtime.");
        }

        Booking bookingEntity =  bookingRepository
                .save(
                        new Booking(
                                user,
                                showtime,
                                bookingRequest.bookedSeats(),
                                showtime.getTicketPrice().multiply(BigDecimal.valueOf(bookingRequest.bookedSeats()))
                        )
                );

         BookingCompletedEvent event = new BookingCompletedEvent(
                 bookingEntity.getId(),
                 bookingEntity.getShowtime().getId(),
                 bookingEntity.getBookedSeats()
         );

         publisher.publishEvent(event);

        return convertToBookingResponse(bookingEntity);
    }

    public BookingResponse findById(Long bookingId) {
        return convertToBookingResponse(bookingRepository.findById(bookingId).orElseThrow(
                () -> new IllegalArgumentException("Booking not found with id " + bookingId)
        ));
    }

    public Booking findBookingEntityById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(
                () -> new IllegalArgumentException("Booking not found with id " + bookingId)
        );
    }

    private BookingResponse convertToBookingResponse(Booking bookingEntity) {
        return new BookingResponse(
                bookingEntity.getId(),
                bookingEntity.getShowtime().getMovie().getId(),
                bookingEntity.getShowtime().getMovie().getTitle(),
                bookingEntity.getUser().getId(),
                bookingEntity.getUser().getUsername(),
                bookingEntity.getUser().getEmail(),
                bookingEntity.getShowtime().getId(),
                bookingEntity.getShowtime().getTheaterName(),
                bookingEntity.getShowtime().getScreenNumber(),
                bookingEntity.getBookedSeats(),
                bookingEntity.getTotalPrice()
        );
    }

    public List<BookingResponse> findAll() {
        return bookingRepository.findAll()
                .stream()
                .map(this::convertToBookingResponse)
                .collect(Collectors.toList());
    }

    public List<BookingResponse> findAllBookingByUser(String username) {
        User user = userService.findByUsername(username);

        return bookingRepository.findAllByUser(user)
                .stream()
                .map(this::convertToBookingResponse)
                .collect(Collectors.toList());
    }

    public BookingResponse findByIdForUser(String username, Long bookingId) {
        Booking booking = findBookingEntityById(bookingId);
        if(booking.getUser().getUsername().equals(username)) {
            return convertToBookingResponse(booking);
        } else {
            throw new IllegalArgumentException("Booking not found with id " + bookingId + " for user " + username);
        }
    }

    public List<BookingResponse> findAllByShowtime(Long showtimeId) {
        Showtime showtime = showtimeService.findShowtimeEntityById(showtimeId);

        return bookingRepository.findAllByShowtime(showtime)
                .stream()
                .map(this::convertToBookingResponse)
                .collect(Collectors.toList());
    }
}
