package com.ironhack.airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ironhack.airline.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    List<Flight> findByFlightNumber(String flightNumber);

    @Query("SELECT f FROM Flight f WHERE f.aircraft LIKE ?1%")
    List<Flight> findByAircraftStartsWith(String prefix);

    @Query("SELECT f FROM Flight f WHERE CAST(f.flightMileage AS int) > :minMileage")
    List<Flight> findByFlightMileageGreaterThan(@Param("minMileage") int minMileage);

}
