package com.olumide.flights.controller;


import com.olumide.flights.models.Booking;
import com.olumide.flights.models.Flight;
import com.olumide.flights.repo.MockRepo;
import com.olumide.flights.service.BookingService;
import com.olumide.flights.service.impl.BookingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    @Autowired
    BookingService bookingServiceImpl;

    // Endpoint to retrieve available flights based on user input
    @GetMapping
    public ResponseEntity<List<Flight>> getFlights(
            @RequestParam String departureCity,
            @RequestParam String destinationCity,
            @RequestParam String date) {
        List<Flight> availableFlights = bookingServiceImpl.getFlights(departureCity, destinationCity, date);
        return ResponseEntity.ok(availableFlights);
    }

    // Endpoint to create a booking
    @PostMapping("/book")
    public ResponseEntity<String> bookFlight(@RequestBody Booking booking) {
        String response= bookingServiceImpl.createBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Endpoint to confirm a booking
    @PostMapping("/confirm")
    public ResponseEntity<String> confirmBooking(@RequestParam String bookingId) {
        MockRepo.confirmBooking(bookingId);
        return ResponseEntity.ok("Booking confirmed.");
    }

    // Endpoint to load flights
    @GetMapping("/loadFlights")
    public ResponseEntity<String> loadFlights() {
        MockRepo.loadDummyFlights();
        return ResponseEntity.ok("Loading Confirmed.");
    }
}
