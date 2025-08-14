package com.hackathon.backend.showtime;

import com.hackathon.backend.movies.Movie;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "show_times")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "show_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @Column(name = "theater_name", nullable = false)
    private String theaterName;

    @Column(name = "screen_number", nullable = false)
    private String screenNumber;

    @Column(name = "show_date_time", nullable = false)
    private LocalDateTime showDateTime;

    @Column(name = "ticket_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal ticketPrice;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;


    public Showtime() {
    }

    public Showtime(Movie movie, String theaterName, String screenNumber,
                    LocalDateTime showDateTime, BigDecimal ticketPrice,
                    Integer totalSeats) {
        this.movie = movie;
        this.theaterName = theaterName;
        this.screenNumber = screenNumber;
        this.showDateTime = showDateTime;
        this.ticketPrice = ticketPrice;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(String screenNumber) {
        this.screenNumber = screenNumber;
    }

    public LocalDateTime getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(LocalDateTime showDateTime) {
        this.showDateTime = showDateTime;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }


    // Helper method to book seats
    public boolean bookSeats(int numberOfSeats) {
        if (availableSeats >= numberOfSeats) {
            availableSeats -= numberOfSeats;
            return true;
        }
        return false;
    }

    // Helper method to release seats (for cancellations)
    public void releaseSeats(int numberOfSeats) {
        availableSeats = Math.min(availableSeats + numberOfSeats, totalSeats);
    }

    @Override
    public String toString() {
        return "ShowTimes{" +
                "id=" + id +
                ", movie=" + (movie != null ? movie.getTitle() : null) +
                ", theaterName='" + theaterName + '\'' +
                ", screenNumber='" + screenNumber + '\'' +
                ", showDateTime=" + showDateTime +
                ", ticketPrice=" + ticketPrice +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                '}';
    }
}