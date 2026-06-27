package com.barangay.ui;

import com.barangay.models.InvalidInputException;
import com.barangay.models.PaymentProcessingException;
import com.barangay.services.TransactionService;
import java.util.List;
import java.util.Scanner;

public class Payment {
    private final Scanner scanner;
    private final TransactionService service = new TransactionService();

    public Payment(Scanner scanner) { this.scanner = scanner; }

    public void start() {
        while (true) {
            displayHeader();
            try {
                String input = scanner.nextLine();
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1 -> registerAccount();
                    case 2 -> processTransfer();
                    case 3 -> viewHistory();
                    case 4 -> searchAccount();
                    case 5 -> {
                        System.out.println("\n>> Shutting down system. Goodbye!");
                        return;
                    }
                    default -> System.out.println(">> [!] Invalid option. Please select 1-5.");
                }
            } catch (NumberFormatException e) {
                System.out.println(">> [!] Error: Please enter a valid number (1-5).");
            } catch (InvalidInputException | PaymentProcessingException e) {
                System.out.println(">> [!] System Error: " + e.getMessage());
            }
        }
    }

    private void displayHeader() {
        System.out.println("\n=============================================");
        System.out.println("           Payment Simulation System           ");
        System.out.println("=============================================");
        System.out.println(" 1. [Register]       Create a new user account");
        System.out.println(" 2. [Transfer]       Send money between accounts");
        System.out.println(" 3. [History]        View sorted transaction logs");
        System.out.println(" 4. [Search]         Search accounts with payment history");
        System.out.println(" 5. [Exit]           Close application");
        System.out.print(" Select operation: ");
    }

    private void registerAccount() {
        System.out.println("\n--- Registration ---");
        System.out.print("Enter Numeric ID: ");
        String id = scanner.nextLine();
        if (!id.matches("\\d+")) throw new InvalidInputException("ID must be numbers only.");
        
        System.out.print("Enter Account Name: ");
        String name = scanner.nextLine();
        if (!name.matches("[a-zA-Z\\s]+")) throw new InvalidInputException("Name must be letters only.");
        
        service.addAccount(id, name);
        System.out.println(">> Success: Account [" + name + "] registered.");
    }

    private void processTransfer() {
        var accs = service.getAccounts();
        if (accs.size() < 2) throw new InvalidInputException("Insufficient participants: Need at least 2 accounts.");

        System.out.println("\n--- Active Participants ---");
        accs.forEach(a -> System.out.println("Index [" + accs.indexOf(a) + "] : " + a));
        
        System.out.print("\nSender Index: ");
        int fromIdx = Integer.parseInt(scanner.nextLine()); 
        System.out.print("Receiver Index: ");
        int toIdx = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Transfer Amount: ");
        double amt = Double.parseDouble(scanner.nextLine());

        if (fromIdx < 0 || fromIdx >= accs.size() || toIdx < 0 || toIdx >= accs.size())
            throw new InvalidInputException("Invalid index selected.");
        
        if (fromIdx == toIdx) throw new InvalidInputException("Source and destination cannot be the same.");
        
        if (amt <= 0) throw new PaymentProcessingException("Payment amount must be greater than zero.");
        
        service.transfer(accs.get(fromIdx), accs.get(toIdx), amt);
        System.out.println(">> Success: Transfer completed successfully.");
    }

    private void viewHistory() {
        System.out.println("\n--- Transaction Log (Sorted by Date) ---");
        List<com.barangay.models.Transfer> logs = service.getSortedHistory();
        if (logs.isEmpty()) System.out.println("No records found.");
        else logs.forEach(System.out::println);
    }

    private void searchAccount() {
        System.out.println("\n--- Searching accounts with transaction history ---");
        var results = service.getAccountsWithPaymentHistory();
        if (results.isEmpty()) System.out.println(">> No accounts with payment history found.");
        else results.forEach(System.out::println);
    }
}