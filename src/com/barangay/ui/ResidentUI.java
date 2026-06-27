package com.barangay.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.barangay.models.*;
import java.time.LocalDate;

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
                int choice = Integer.parseInt(scanner.nextLine());

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

    
    //registration
    public Resident registerResident(){
        System.out.println("First Name: ");
        String firstName = sc.nextLine();

        System.out.println("Last Name: ");
        String lastName = sc.nextLine();
        residentName name = new residentName(firstName, lastName);

        System.out.println("Birthdate: ");
        LocalDate birthdate = LocalDate.parse(sc.nextLine());
        residentBirthDate birth = new residentBirthDate(birthdate);
        residentBdate bDate = new residentBDate(birth);

        System.out.print("Gender (M/F/X): ");
        String genderInput = sc.nextLine();
        ResidentGender gender;
        switch (genderInput.toUpperCase()){
            case "M": gender = new Male(); break;
            case "F": gender = new Female(); break; 
            default: gender = new PreferNotToSay(); break; 
        }
        
        System.out.println("Resident: " + name.getFirstName() + " " + name.getlastName() +
        ", Birthday" + bDate.getResidentBirthDay() + 
        ", Gender: " + gender.getResidentGender());
        return null;
        
    }
    
    
}



public class RegistrationSystem {
    private List<residentName> residents = new ArrayList<>();

    // Add a resident
    public void registerResident(residentName name) {
        residents.add(name);
    }

    // Search by first + last name
    public residentName searchByName(String firstName, String lastName) {
        for (residentName r : residents) {
            if (r.getFirstName().equalsIgnoreCase(firstName) &&
                r.getlastName().equalsIgnoreCase(lastName)) {
                return r;
            }
        }
        return null; // not found
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RegistrationSystem system = new RegistrationSystem();

        System.out.print("How many residents to register? ");
        int count = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 0; i < count; i++) {
            System.out.print("First Name: ");
            String firstName = sc.nextLine();
            System.out.print("Last Name: ");
            String lastName = sc.nextLine();

            residentName name = new residentName(firstName, lastName);
            system.registerResident(name);
        }

        // Search
        System.out.print("\nEnter name to search (First Last): ");
        String[] searchInput = sc.nextLine().split(" ");
        residentName found = system.searchByName(searchInput[0], searchInput[1]);

        if (found != null) {
            System.out.println("Resident found: " + found.getFirstName() + " " + found.getlastName());
        } else {
            System.out.println("Resident not found.");
        }

        sc.close();
    }
}

    


