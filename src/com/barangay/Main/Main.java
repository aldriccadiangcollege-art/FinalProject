package com.barangay.Main;

import com.barangay.models.InvalidInputException;
import com.barangay.ui.DocumentRequestUI;
import com.barangay.ui.GCashUI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DocumentRequestUI documentRequestUI = new DocumentRequestUI(scanner);
        GCashUI gCashUI = new GCashUI(scanner);

        while (true) {
            System.out.println("\n===== BARANGAY SYSTEM MAIN MENU =====");
            System.out.println("1: Option 1 - Resident");
            System.out.println("2: Option 2 - Gcash");
            System.out.println("3: Option 3 - Document Request");
            System.out.println("4: Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                 if (choice < 1 || choice > 4) {
               throw new InvalidInputException("Choice must be between 1 and 4.");
                }

                switch (choice) {
                    case 1:
                        System.out.println("You selected Option 1 - Resident");
                        break;
                    case 2:
                        System.out.println("You selected Option 2 - Gcash");
                        gCashUI.start();
                        break;
                    case 3:
                        System.out.println("You selected Option 3 - Document Request");
                        documentRequestUI.launch();
                        break;
                    case 4:
                        System.out.println("Exiting system. Goodbye!");
                        scanner.close(); 
                        return; 
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid numeric input.");
            }catch (InvalidInputException e) 
            {
            System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
            System.out.println("An unexpected error occurred");
            }
        }
    }
}