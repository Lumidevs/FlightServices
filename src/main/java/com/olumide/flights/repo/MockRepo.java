package com.olumide.flights.repo;

import com.olumide.flights.dtos.Response;
import com.olumide.flights.exceptions.BookingNotFoundException;
import com.olumide.flights.models.Booking;
import com.olumide.flights.models.Flight;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.plugins.annotations.Component;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockRepo {

    private static List<Flight> flights = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();

    // Get available flights based on user input
    public static List<Flight> getFlights(String departureCity, String destinationCity, String date) {
        return flights.parallelStream()
                .filter(flight ->
                        flight.getDepartureCity().equalsIgnoreCase(departureCity) &&
                                flight.getDestinationCity().equalsIgnoreCase(destinationCity) &&
                                flight.getDate().equalsIgnoreCase(date))
                .collect(Collectors.toList());


        }

    // Create a booking
    public  static void createBooking(Booking booking) {
        bookings.add(booking);


    }

    // Confirm a booking
    public static void confirmBooking(String bookingId) {
    // Find the booking by its ID in the bookings list
        Booking confirmedBooking = bookings.stream()
                .filter(booking -> booking.getBookingId().equals(bookingId))
                .findFirst()
                .orElse(null);

        if (confirmedBooking != null) {
            //update the status of the booking class
            confirmedBooking.setStatus("confirmed");
        } else {
            throw new BookingNotFoundException("Booking not found with ID: " + bookingId);
        }    }


        public static void loadDummyFlights() {
            // Create and add dummy flight data to the flights list
            flights.add(new Flight("FL123", "New York", "Los Angeles", "2023-09-30", 100.2 ));
            flights.add(new Flight("FL124", "New York", "Los Angeles", "2023-09-30", 100.5 ));
            flights.add(new Flight("FL125", "New York", "Los Angeles", "2023-09-30", 100.6 ));
            flights.add(new Flight("FL456", "Chicago", "Miami", "2023-09-30", 100.2) );
        }

        public static List<Flight> getAllFlights() {
            return flights;
        }

}
