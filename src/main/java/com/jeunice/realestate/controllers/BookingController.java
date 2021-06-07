package com.jeunice.realestate.controllers;

import com.jeunice.realestate.dao.BookingRepository;
import com.jeunice.realestate.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> findBookingById(@PathVariable(value = "id") long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            return ResponseEntity.ok().body(booking.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/house/{id}")
    public List<Booking> findAllByHouseId(@PathVariable(value = "id") long id) {
        return bookingRepository.findAllByHouseId(id);
    }

    @PostMapping
    public Booking book(@Validated @RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }
}
