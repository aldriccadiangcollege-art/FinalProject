package com.barangay.Main;

import com.barangay.ui.CustomException;
import com.barangay.ui.DocumentRequestUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DocumentRequestUI documentRequestUI = new DocumentRequestUI(scanner);

        while (true) {
            System.out.println("\n===== BARANGAY SYSTEM MAIN MENU =====");
            System.out.println("1: Option 1 - Resident");
            System.out.println("2: Option 2 - GCash");
            System.out.println("3: Option 3 - Document");
            System.out.println("4: Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.println("\n===== RESIDENT MODULE =====");
                        System.out.println("resident");
                    }

                    case 2 -> {
                        System.out.println("\n===== GCASH MODULE =====");
                        System.out.println("gcash");
                    }

                    case 3 -> {
                        System.out.println("\n===== DOCUMENT MODULE =====");
                        documentRequestUI.launch();
                    }

                    case 4 -> {
                        System.out.println("Goodbye!");
                        System.out.println("Program terminated.");
                        scanner.close();
                        return;
                    }

                    default -> throw new CustomException(
                            "Invalid choice! Please select 1, 2, 3, or 4."
                    );
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Input must be a valid number.");
            } catch (CustomException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}