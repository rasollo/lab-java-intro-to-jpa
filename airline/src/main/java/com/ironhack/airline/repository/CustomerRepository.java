package com.ironhack.airline.repository;

import com.ironhack.airline.model.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByCustomerName(String customerName);

    List<Customer> findByCustomerStatus(String customerStatus);
}