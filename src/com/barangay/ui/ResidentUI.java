package com.barangay.ui;

import com.barangay.models.*;
import com.barangay.ui.ResidentUI.ResidentRegistration;
import com.barangay.ui.ResidentUI.ResidentViewer;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResidentUI {
    private Scanner sc = new Scanner(System.in);
   private final InputProvider inputProvider = new ConsoleInputProvider();
    private final ResidentRegistration registration = new ResidentRegistration(inputProvider);
    private final ResidentViewer viewer = new ResidentViewer();
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
                    registration.registerResident();
                    break;
                case 2:
                    List<Resident> currentResidents = registration.getResidents();
                    if (currentResidents.isEmpty()) {
                        System.out.println("No residents found.");
                    } else {
                        viewer.viewNames(currentResidents);
                    }
                    break;
                case 3:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    throw new InvalidInputException("Invalid choice! Please select 1, 2, or 3.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Input must be a valid number!");
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


    class Resident {
        private String firstName;
        private String lastName;
        private LocalDate birthdate;
        private String gender;

        public Resident(String firstName, String lastName, LocalDate birthdate, String gender) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthdate = birthdate;
            this.gender = gender;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }

        public LocalDate getBirthdate() {
            return birthdate;
        }

        public String getGender() {
            return gender;
        }
    }


    interface InputProvider {
        String getInput(String prompt);
    }


    class ConsoleInputProvider implements InputProvider {
        private Scanner sc = new Scanner(System.in);

        @Override
        public String getInput(String prompt) {
            System.out.print(prompt);
            return sc.nextLine();
        }
    }


    class ResidentRegistration {
        private InputProvider inputProvider;
        private List<Resident> residents = new ArrayList<>();

        public ResidentRegistration(InputProvider inputProvider) {
            this.inputProvider = inputProvider;
        }

        public void registerResident() {
            String firstName = inputProvider.getInput("First Name: ");
            String lastName = inputProvider.getInput("Last Name: ");

            LocalDate birthdate;
            try {
                birthdate = LocalDate.parse(inputProvider.getInput("Birthdate (YYYY-MM-DD): "));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Use YYYY-MM-DD.");
                return;
            }

            String genderInput = inputProvider.getInput("Gender (M/F/X): ");

            Resident r = new Resident(firstName, lastName, birthdate, genderInput.toUpperCase());
            residents.add(r);

            System.out.println("Resident registered: " + r.getFullName());
        }

        public List<Resident> getResidents() {
            return residents;
        }
    }


    class ResidentViewer {
        public void viewNames(List<Resident> residents) {
            System.out.println("\nAll registered names:");
            for (Resident r : residents) {
                System.out.println(r.getFullName());
            }
        }
    }




}