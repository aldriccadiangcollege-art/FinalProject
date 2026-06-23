package com.barangay.Main;
import com.barangay.models.CustomException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
            while (true) {
                System.out.println("\nSelect an option:");
                System.out.println("1: Option 1 - Resident");
                System.out.println("2: Option 2 - Gcash");
                System.out.println("3: Option 3 -");
                System.out.println("4: Exit");
                System.out.print("Enter your choice: ");

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine());

                    if (choice == 1) {
                        System.out.println("You selected Option 1 - Resident");
                    } else if (choice == 2) {
                        System.out.println("You selected Option 2 - Gcash");
                    } else if (choice == 3) {
                        System.out.println("You selected Option 3 - ");

                    } else if (choice == 4) {
                        System.out.println("Goodbye!");
                        System.out.println("Program terminated.");
                        scanner.close();
                        break;
                    } else {
                        throw new CustomException("Invalid choice! Please select 1, 2, 3, or 4.");                    }
                } catch (NumberFormatException e) {
                System.out.println("Error: Input must be among the given choises!");
            } catch (CustomException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}