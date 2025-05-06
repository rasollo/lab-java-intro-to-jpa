package com.ironhack.airline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ironhack.airline.model.Customer;
import com.ironhack.airline.model.Flight;
import com.ironhack.airline.model.FlightBooking;
import com.ironhack.airline.repository.CustomerRepository;
import com.ironhack.airline.repository.FlightBookingRepository;
import com.ironhack.airline.repository.FlightRepository;

@SpringBootTest
public class AirlineApplicationTest {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    FlightBookingRepository flightBookingRepository;

    @Autowired
    FlightRepository flightRepository;

    @BeforeEach
    void setUp() {
        // Clear existing data
        flightBookingRepository.deleteAll();
        customerRepository.deleteAll();
        flightRepository.deleteAll();

        // Create and save customers
        Customer customer1 = new Customer(1, "John Doe", "Silver", 10000);
        Customer customer2 = new Customer(2, "Carl Cox", "Gold", 1000000);
        Customer customer3 = new Customer(3, "Priest John", "Silver", 500000);
        customerRepository.saveAll(List.of(customer1, customer2, customer3));

        // Create and save flights
        Flight flight1 = new Flight(1, "DL143", "Boeing 747", "135");
        Flight flight2 = new Flight(2, "DL122", "Airbus A330", "4370");
        Flight flight3 = new Flight(3, "53", "Boeing 777", "2078");
        flightRepository.saveAll(List.of(flight1, flight2, flight3));

        // Create and save bookings using entity references
        FlightBooking booking1 = new FlightBooking(1, customer1, flight1);
        FlightBooking booking2 = new FlightBooking(2, customer2, flight1);
        FlightBooking booking3 = new FlightBooking(3, customer3, flight3);
        FlightBooking booking4 = new FlightBooking(4, customer3, flight2);
        flightBookingRepository.saveAll(List.of(booking1, booking2, booking3, booking4));
    }

    @Test
    public void createCustomer() {
        // Create and save customers
        Customer customer1 = new Customer(1, "John Doe", "Silver", 10000);
        Customer customer2 = new Customer(2, "Carl Cox", "Gold", 1000000);
        Customer customer3 = new Customer(3, "Priest John", "Silver", 500000);
        customerRepository.saveAll(List.of(customer1, customer2, customer3));

        List<Customer> customerList = customerRepository.findAll();
        System.out.println(customerList);
        assertEquals(3, customerList.size());
    }

    @Test
    public void createFlight() {
        // Create and save flights
        Flight flight1 = new Flight(1, "DL143", "Boeing 747", "135");
        Flight flight2 = new Flight(2, "DL122", "Airbus A330", "4370");
        Flight flight3 = new Flight(3, "53", "Boeing 777", "2078");
        flightRepository.saveAll(List.of(flight1, flight2, flight3));

        List<Flight> flightList = flightRepository.findAll();
        System.out.println(flightList);
        assertEquals(3, flightList.size());
    }

    @Test
    public void findAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        System.out.println(flights);
        List<Customer> customers = customerRepository.findAll();
        System.out.println(customers);
        List<FlightBooking> bookings = flightBookingRepository.findAll();
        System.out.println(bookings);
        assertEquals(3, flights.size());
        assertEquals(3, customers.size());
        assertEquals(4, bookings.size());
    }

    @Test
    public void findCustomersByName_validName_customerPresent() {
        Customer expectedCustomer = new Customer(1, "John Doe", "Silver", 10000);
        Optional<Customer> customerOptional = customerRepository.findByCustomerName("John Doe");

        assertTrue(customerOptional.isPresent());
        assertEquals(expectedCustomer, customerOptional.get()); // Uses equals() method
    }

    @Test
    public void findCustomersByStatus_validStatus_customerPresent() {
        Customer expectedCustomer = new Customer(1, "John Doe", "Silver", 10000);
        Customer expectedCustomer2 = new Customer(2, "Frang Jhomps", "Gold", 10000000);
        Customer expectedCustomer3 = new Customer(3, "Priest John", "Silver", 500000);

        List<Customer> silverCustomers = customerRepository.findByCustomerStatus("Silver");

        assertEquals(2, silverCustomers.size(), "Should find 2 Silver Costumers.");
        assertTrue(silverCustomers.contains(expectedCustomer));
        assertTrue(silverCustomers.contains(expectedCustomer3));
        assertFalse(silverCustomers.contains(expectedCustomer2));
    }

    @Test
    public void findFlightsByFlightNumber_validFlightNumber_flightPresent() {
        Flight flight1 = new Flight(1, "DL143", "Boeing 747", "135");
        Flight flight2 = new Flight(2, "DL122", "Airbus A330", "4370");
        Flight flight3 = new Flight(3, "DL53", "Boeing 777", "2078");
        flightRepository.saveAll(List.of(flight1, flight2, flight3));

        List<Flight> flightsFound = flightRepository.findByFlightNumber("DL143");

        assertEquals(1, flightsFound.size(), "Should find 1 flight with number DL143");
        assertTrue(flightsFound.contains(flight1), "Should contain flight1");
        assertFalse(flightsFound.contains(flight2), "Should not contain flight2");
        assertFalse(flightsFound.contains(flight3), "Should not contain flight3");
    }

    @Test
    public void findAircraftName_validAircraftName_aircraftPresent() {
        Flight flight1 = new Flight(1, "DL143", "Boeing 747", "135");
        Flight flight2 = new Flight(2, "DL122", "Airbus A330", "4370");
        Flight flight3 = new Flight(3, "DL53", "Boeing 777", "2078");
        flightRepository.saveAll(List.of(flight1, flight2, flight3));

        List<Flight> flightsFound = flightRepository.findByAircraftStartsWith("Boeing");

        assertEquals(2, flightsFound.size(), "Should find 2 aircraft named Boeing");
        assertTrue(flightsFound.contains(flight1), "Should contain flight1");
        assertTrue(flightsFound.contains(flight3), "Should contain flight3");
        assertFalse(flightsFound.contains(flight2), "Should not contain flight2");
    }

    @Test
    public void findDistance_validDistance_distance() {
        Flight flight1 = new Flight(1, "DL143", "Boeing 747", "135");
        Flight flight2 = new Flight(2, "DL122", "Airbus A330", "4370");
        Flight flight3 = new Flight(3, "DL53", "Boeing 777", "501");
        flightRepository.saveAll(List.of(flight1, flight2, flight3));

        List<Flight> flightsFound = flightRepository.findByFlightMileageGreaterThan(500);

        assertEquals(2, flightsFound.size(), "Should have 2 flights");
        assertTrue(flightsFound.contains(flight2), "Should contain flight2");
        assertTrue(flightsFound.contains(flight3), "Should contain flight3");
        assertFalse(flightsFound.contains(flight1), "Should not contain flight1");
    }
}