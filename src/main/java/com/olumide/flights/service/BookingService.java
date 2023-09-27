package com.olumide.flights.service;

import com.olumide.flights.models.Booking;
import com.olumide.flights.models.Flight;
import java.util.List;

public interface BookingService {

     String createBooking(Booking booking);
     String generateBookingId();
     boolean simulatePaymentConfirmation();

    List<Flight>  getFlights(String departureCity, String destinationCity, String date);

    }
