package com.barangay.ui;

import com.barangay.models.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ResidentUI {
    private Scanner sc = new Scanner(System.in);

    public void ResidentUIMenu(){
         while (true) {
            System.out.println("\n--- Residents ---");
            System.out.println("1. Add resident");
            System.out.println("2. Find Resident");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("You selected Option 1 - Add Resident");
                        break;
                    case 2:
                        System.out.println("You selected Option 2 - Find Resident");
                        break;
                    case 3:
                        System.out.println("Returning to Main Menu...");
                        return; // Exits the method and the loop
                    default:
                        throw new InvalidInputException("Invalid choice! Please select 1, 2, 3, 4, or 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Input must be a valid number!");
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void registerResident() {
        System.out.print("First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Last Name: ");
        String lastName = sc.nextLine();
        
        System.out.print("Birthdate (YYYY-MM-DD): ");
        LocalDate birthdate;
        try {
            birthdate = LocalDate.parse(sc.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
            return;
        }

        System.out.print("Gender (M/F/X): ");
        String genderInput = sc.nextLine();
        
        // Assuming your models exist and match these types
        System.out.println("Resident registered: " + firstName + " " + lastName + 
                           ", Born: " + birthdate + ", Gender: " + genderInput.toUpperCase());
    }
}

    

    


