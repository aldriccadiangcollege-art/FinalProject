package com.barangay.ui;

import com.barangay.models.InvalidInputException;
import java.util.Scanner;

public class GCashUI {
    private Scanner scanner;

    // Constructor to pass the scanner from Main
    public GCashUI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Welcome to GCash Payment System ---");
            System.out.println("1. Establishing account");
            System.out.println("2. GCash Payment");
            System.out.println("3. View Payment");
            System.out.println("4. Sorting Payment");
            System.out.println("5. Exit");
            System.out.print("Please select an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    System.out.println("You selected Option 1 - Establishing Account");
                } else if (choice == 2) {
                    System.out.println("You selected Option 2 - GCash Payment");
                } else if (choice == 3) {
                    System.out.println("You selected Option 3 - View Payment History");
                } else if (choice == 4) {
                    System.out.println("You selected Option 4 - Sorting Payment");
                } else if (choice == 5) {
                    System.out.println("Returning to Main Menu...");
                    break;
                } else {
                    throw new InvalidInputException("Invalid choice! Please select 1, 2, 3, 4, or 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Input must be a valid number!");
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}