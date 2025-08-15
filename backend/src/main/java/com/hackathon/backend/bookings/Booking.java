package com.hackathon.backend.bookings;

import com.hackathon.backend.showtime.Showtime;
import com.hackathon.backend.users.User;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;

    @Column(name = "booked_seats")
    private Integer bookedSeats;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    public Booking(Long id, User user, Showtime showtime, Integer bookedSeats,  BigDecimal totalPrice) {
        this.id = id;
        this.user = user;
        this.showtime = showtime;
        this.bookedSeats = bookedSeats;
        this.totalPrice = totalPrice;
    }

    public Booking(User user, Showtime showtime, Integer bookedSeats,  BigDecimal totalPrice) {
        this.user = user;
        this.showtime = showtime;
        this.bookedSeats = bookedSeats;
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public Integer getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(Integer bookedSeats) {
        this.bookedSeats = bookedSeats;
    }
}
