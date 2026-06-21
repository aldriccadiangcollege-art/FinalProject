package com.barangay.Main;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an option:");
        System.out.println("1: ");
        System.out.println("2: ");
        System.out.println("3: ");
        System.out.println("3: Exit ");
        System.out.print("Enter your choice  ");

        try {
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("");
            } else if (choice == 2) {
                System.out.println("");
            } else if (choice == 3) {
                System.out.println(" ");
            } else if (choice == 4) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice! .");
            }
        } catch (Exception e) {
            System.out.println("Invalid input! ");
        } finally {
            scanner.close();
        }
    }
}