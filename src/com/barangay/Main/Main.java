package com.barangay.Main;

import com.barangay.models.InvalidInputException;
import com.barangay.ui.DocumentRequest;
import com.barangay.ui.Payment;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DocumentRequest documentRequest = new DocumentRequest(scanner);
        Payment paymentUI = new Payment(scanner);

        while (true) {
            System.out.println("\n===== BARANGAY SYSTEM MAIN MENU =====");
            System.out.println("1: Option 1 - Resident");
            System.out.println("2: Option 2 - Gcash");
            System.out.println("3: Option 3 - Document Request");
            System.out.println("4: Exit");
            System.out.print("Enter your choice: ");

 try {
    // Read input and parse to int
    int choice = Integer.parseInt(scanner.nextLine());

    // Validation
    if (choice < 1 || choice > 4) {
        throw new InvalidInputException("Choice must be between 1 and 4.");
    }

    // Use traditional switch statement
    switch (choice) {
        case 1:
            System.out.println("\n===== RESIDENT MODULE =====");
            System.out.println("resident");
            break;
        case 2:
            System.out.println("\n===== GCASH MODULE =====");
            System.out.println("gcash");
            paymentUI.start();
            break;
        case 3:
            System.out.println("\n===== DOCUMENT MODULE =====");
            documentRequest.launch();
            break;
        case 4:
            System.out.println("Exiting system. Goodbye!");
            scanner.close();
            return;
        default:
            // This is technically unreachable due to the if-check above,
            // but kept as a safety practice.
            System.out.println("Invalid choice.");
            break;
    }

} catch (NumberFormatException e) {
    System.out.println("Error: Please enter a valid numeric input.");
} catch (InvalidInputException e) {
    System.out.println("Error: " + e.getMessage());
} catch (Exception e) {
    System.out.println("An unexpected error occurred: " + e.getMessage());
}
}
}
}