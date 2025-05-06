package com.ironhack.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ironhack.airline.model.FlightBooking;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking, String> {
}
