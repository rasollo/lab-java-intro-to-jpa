package com.ironhack.airline.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Flight {
    @Id
    private Integer flightId;
    private String flightNumber;
    private String aircraft;
    private String flightMileage;

    public Flight() {
    }

    public Flight(Integer flightId, String flightNumber, String aircraft, String flightMileage) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.aircraft = aircraft;
        this.flightMileage = flightMileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Flight flight = (Flight) o;
        return Objects.equals(flightId, flight.flightId) &&
                Objects.equals(flightNumber, flight.flightNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, flightNumber);
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getFlightMileage() {
        return flightMileage;
    }

    public void setFlightMileage(String flightMileage) {
        this.flightMileage = flightMileage;
    }

    @Override
    public String toString() {
        return "Flight [flightId=" + flightId + ", flightNumber=" + flightNumber + ", aircraft=" + aircraft
                + ", flightMileage=" + flightMileage + "]";
    }
}
