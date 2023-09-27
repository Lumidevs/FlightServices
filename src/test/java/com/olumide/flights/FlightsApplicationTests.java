package com.olumide.flights;

import com.olumide.flights.models.Flight;
import com.olumide.flights.repo.MockRepo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FlightsApplicationTests {




	@Test
	public void testLoadDummyFlights() {

		// Retrieve the loaded flights
		List<Flight> flights = MockRepo.getAllFlights();

		// Verify the number of loaded flights
		assertEquals(4, flights.size());


	}

}
