package com.olumide.flights.service.impl;

import com.olumide.flights.dtos.Response;
import com.olumide.flights.exceptions.PaymentFailedException;
import com.olumide.flights.models.Booking;
import com.olumide.flights.models.Flight;
import com.olumide.flights.repo.MockRepo;
import com.olumide.flights.service.BookingService;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    private static int bookingIdCounter = 1;

    @Override
    public String createBooking(Booking booking) {
        // Generate a booking ID
        String bookingId = generateBookingId();

        // Simulate payment confirmation (In a real system, you would connect to a payment gateway)
        boolean paymentConfirmed = simulatePaymentConfirmation();

        if (paymentConfirmed) {
            // Update the booking with the generated ID and mark it as confirmed
            booking.setBookingId(bookingId);
            booking.setStatus("confirmed");

            // Save the booking to the mock database
             MockRepo.createBooking(booking);
            return bookingId + " Successfully created";
        } else {
            throw new PaymentFailedException("Payment for booking failed.");
        }
    }

    @Override
    public String generateBookingId() {
        return "B" + bookingIdCounter++;

    }

    @Override
    public boolean simulatePaymentConfirmation() {

        //this is supposed to integrate with another service to confirm payment but I have to mock it to always return true
        return true;

    }

    @Override
    public List<Flight> getFlights(String departureCity, String destinationCity, String date) {
        return MockRepo.getFlights(departureCity, destinationCity, date);
    }
}
