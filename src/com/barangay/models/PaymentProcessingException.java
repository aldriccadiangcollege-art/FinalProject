package com.barangay.models;

// Custom exception for invalid user input in the Gcash ui  menu
public class PaymentProcessingException extends RuntimeException {
    public PaymentProcessingException(String message) {
        super(message);
    }
}