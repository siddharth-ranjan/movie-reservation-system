package com.hackathon.backend.showtime.internal.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// A simple DTO to represent the ShowTimes data for the API
public class ShowtimeDTO {
    private Long id;
    private Long movieId; // Instead of the whole object, just send the ID
    private String movieTitle; // Or any other movie details you need
    private String theaterName;
    private String screenNumber;
    private LocalDateTime showDateTime;
    private BigDecimal ticketPrice;
    private Integer availableSeats;

    public ShowtimeDTO(Long id, Long movieId, String movieTitle, String theaterName, String screenNumber, LocalDateTime showDateTime, BigDecimal ticketPrice, Integer availableSeats) {
        this.id = id;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.theaterName = theaterName;
        this.screenNumber = screenNumber;
        this.showDateTime = showDateTime;
        this.ticketPrice = ticketPrice;
        this.availableSeats = availableSeats;
    }

    public ShowtimeDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
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

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
}