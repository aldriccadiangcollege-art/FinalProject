package com.barangay.ui;

import com.barangay.models.AbstractDocumentRequest;
import com.barangay.models.DocumentType;
import com.barangay.models.InvalidInputException;
import com.barangay.models.RequestStatus;
import com.barangay.services.DocumentRequestService;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class DocumentRequest {
    private final Scanner scanner;
    private final DocumentRequestService requestService;

    public DocumentRequest(Scanner scanner) {
        this.scanner = scanner;
        this.requestService = new DocumentRequestService();
    }

    public void launch() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== DOCUMENT REQUEST DISPATCHER =====");
            System.out.println("1. Create Document Request");
            System.out.println("2. View All Requests");
            System.out.println("3. View Pending Requests");
            System.out.println("4. Search Request by Resident Name");
            System.out.println("5. Sort Requests by Date");
            System.out.println("6. Sort Requests by Resident Name");
            System.out.println("7. Update Request Status");
            System.out.println("8. Back to Main Menu");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> createRequest();
                    case 2 -> displayRequests(requestService.getAllRequests(), "ALL REQUESTS");
                    case 3 -> displayRequests(requestService.getPendingRequests(), "PENDING REQUESTS");
                    case 4 -> searchByResidentName();
                    case 5 -> displayRequests(requestService.sortByDate(), "REQUESTS SORTED BY DATE");
                    case 6 -> displayRequests(requestService.sortByResidentName(), "REQUESTS SORTED BY RESIDENT NAME");
                    case 7 -> updateRequestStatus();
                    case 8 -> running = false;
                    default -> throw new InvalidInputException("Invalid menu choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number only.");
            } catch (InvalidInputException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private void createRequest() {
        System.out.println("\n--- CREATE DOCUMENT REQUEST ---");

        System.out.print("Resident ID: ");
        String residentId = requireNonEmpty(scanner.nextLine(), "Resident ID is required.");

        System.out.print("First Name: ");
        String firstName = requireNonEmpty(scanner.nextLine(), "First name is required.");

        System.out.print("Last Name: ");
        String lastName = requireNonEmpty(scanner.nextLine(), "Last name is required.");

        System.out.print("Birth Year (e.g. 2005): ");
        int year = parseInt(scanner.nextLine(), "Invalid birth year.");

        System.out.print("Birth Month (1-12): ");
        int month = parseInt(scanner.nextLine(), "Invalid birth month.");

        System.out.print("Birth Day (1-31): ");
        int day = parseInt(scanner.nextLine(), "Invalid birth day.");

        LocalDate birthDate;
        try {
            birthDate = LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new InvalidInputException("Invalid birth date.");
        }

        System.out.print("Gender: ");
        String gender = requireNonEmpty(scanner.nextLine(), "Gender is required.");

        System.out.print("Address: ");
        String address = requireNonEmpty(scanner.nextLine(), "Address is required.");

        DocumentType documentType = chooseDocumentType();

        System.out.print("Purpose: ");
        String purpose = requireNonEmpty(scanner.nextLine(), "Purpose is required.");

        AbstractDocumentRequest request = requestService.createRequest(
                residentId,
                firstName,
                lastName,
                birthDate,
                gender,
                address,
                documentType,
                purpose
        );

        System.out.println("\nRequest created successfully!");
        System.out.println(request);
    }

    private DocumentType chooseDocumentType() {
        System.out.println("\nSelect Document Type:");
        System.out.println("1. Barangay Certificate");
        System.out.println("2. Barangay Clearance");
        System.out.println("3. Certificate of Residency");
        System.out.println("4. Certificate of Indigency");
        System.out.print("Enter choice: ");

        int choice = parseInt(scanner.nextLine(), "Invalid document type.");

        return switch (choice) {
            case 1 -> DocumentType.BARANGAY_CERTIFICATE;
            case 2 -> DocumentType.BARANGAY_CLEARANCE;
            case 3 -> DocumentType.CERTIFICATE_OF_RESIDENCY;
            case 4 -> DocumentType.CERTIFICATE_OF_INDIGENCY;
            default -> throw new InvalidInputException("Document type does not exist.");
        };
    }

    private void searchByResidentName() {
        System.out.print("\nEnter resident name keyword: ");
        String keyword = requireNonEmpty(scanner.nextLine(), "Search keyword cannot be empty.");

        List<AbstractDocumentRequest> results = requestService.searchByResidentName(keyword);
        displayRequests(results, "SEARCH RESULTS");
    }

    private void updateRequestStatus() {
        System.out.print("\nEnter Request ID: ");
        String requestId = requireNonEmpty(scanner.nextLine(), "Request ID is required.");

        System.out.println("Select new status:");
        System.out.println("1. APPROVED");
        System.out.println("2. REJECTED");
        System.out.println("3. RELEASED");
        System.out.print("Enter choice: ");

        int choice = parseInt(scanner.nextLine(), "Invalid status choice.");

        RequestStatus status = switch (choice) {
            case 1 -> RequestStatus.APPROVED;
            case 2 -> RequestStatus.REJECTED;
            case 3 -> RequestStatus.RELEASED;
            default -> throw new InvalidInputException("Invalid status.");
        };

        requestService.updateRequestStatus(requestId, status);
        System.out.println("Request status updated successfully.");
    }

    private void displayRequests(List<AbstractDocumentRequest> requests, String title) {
        System.out.println("\n===== " + title + " =====");

        if (requests.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        for (AbstractDocumentRequest request : requests) {
            System.out.println(request);
            System.out.println("-----------------------------------");
        }
    }

    private String requireNonEmpty(String input, String errorMessage) {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException(errorMessage);
        }
        return input.trim();
    }

    private int parseInt(String input, String errorMessage) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException(errorMessage);
        }
    }
}