package com.ironhack.airline.model;

import jakarta.persistence.*;

@Entity
public class FlightBooking {
    @Id
    private Integer bookingId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "flightId")
    private Flight flight;

    public FlightBooking() {
    }

    // Updated constructor to use entities instead of IDs
    public FlightBooking(Integer bookingId, Customer customer, Flight flight) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.flight = flight;
    }

    // Getters and setters
    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "FlightBooking [bookingId=" + bookingId + ", customer=" + customer + ", flight=" + flight + "]";
    }
}