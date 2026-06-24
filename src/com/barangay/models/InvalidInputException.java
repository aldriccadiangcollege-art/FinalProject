package com.barangay.models;  


// Custom exception for invalid user input in the main menu
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
    
}

