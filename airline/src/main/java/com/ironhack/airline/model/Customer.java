package com.ironhack.airline.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    private Integer customerId;
    private String customerName;
    private String customerStatus;
    private Integer totalCustomerMileage;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId) &&
                Objects.equals(customerName, customer.customerName) &&
                Objects.equals(customerStatus, customer.customerStatus) &&
                Objects.equals(totalCustomerMileage, customer.totalCustomerMileage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName, customerStatus, totalCustomerMileage);
    }

    public Customer() {
    }

    public Customer(Integer customerId, String customerName, String customerStatus, Integer totalCustomerMileage) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerStatus = customerStatus;
        this.totalCustomerMileage = totalCustomerMileage;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Integer getTotalCustomerMileage() {
        return totalCustomerMileage;
    }

    public void setTotalCustomerMileage(Integer totalCustomerMileage) {
        this.totalCustomerMileage = totalCustomerMileage;
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerStatus="
                + customerStatus + ", totalCustomerMileage=" + totalCustomerMileage + "]";
    }

}
