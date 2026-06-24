package com.barangay.services;

import com.barangay.models.AbstractDocumentRequest;
import com.barangay.models.DocumentType;
import com.barangay.models.RequestStatus;
import com.barangay.models.ResidentRecord;
import com.barangay.ui.CustomException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DocumentRequestService {
    private final GenericRepository<AbstractDocumentRequest> requestRepository;
    private final RequestFactory requestFactory;
    private final RequestSearchEngine searchEngine;
    private final RequestSorter sorter;
    private int requestCounter;

    public DocumentRequestService() {
        this.requestRepository = new GenericRepository<>();
        this.requestFactory = new RequestFactory();
        this.searchEngine = new RequestSearchEngine();
        this.sorter = new RequestSorter();
        this.requestCounter = 1;
    }

    public AbstractDocumentRequest createRequest(ResidentRecord resident,
                                                 DocumentType type,
                                                 String purpose) {
        String requestId = generateRequestId();
        AbstractDocumentRequest request =
                requestFactory.createRequest(requestId, resident, type, purpose);
        requestRepository.add(request);
        return request;
    }

    public AbstractDocumentRequest createRequest(String residentId,
                                                 String firstName,
                                                 String lastName,
                                                 LocalDate birthDate,
                                                 String gender,
                                                 String address,
                                                 DocumentType type,
                                                 String purpose) {
        ResidentRecord resident = new ResidentRecord(
                residentId, firstName, lastName, birthDate, gender, address
        );

        return createRequest(resident, type, purpose);
    }

    private String generateRequestId() {
        return "REQ-" + String.format("%03d", requestCounter++);
    }

    public List<AbstractDocumentRequest> getAllRequests() {
        return requestRepository.getAll();
    }

    public List<AbstractDocumentRequest> getPendingRequests() {
        return searchEngine.filterByStatus(requestRepository.getAll(), RequestStatus.PENDING);
    }

    public List<AbstractDocumentRequest> searchByResidentName(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new CustomException("Search keyword cannot be empty.");
        }
        return searchEngine.searchByResidentName(requestRepository.getAll(), keyword.trim());
    }

    public List<AbstractDocumentRequest> sortByDate() {
        return sorter.sortByDate(requestRepository.getAll());
    }

    public List<AbstractDocumentRequest> sortByResidentName() {
        return sorter.sortByResidentName(requestRepository.getAll());
    }

    public void updateRequestStatus(String requestId, RequestStatus status) {
        AbstractDocumentRequest request = findById(requestId)
                .orElseThrow(() -> new CustomException("Request ID not found."));

        request.setStatus(status);
    }

    public Optional<AbstractDocumentRequest> findById(String requestId) {
        return requestRepository.getAll().stream()
                .filter(r -> r.getId().equalsIgnoreCase(requestId))
                .findFirst();
    }
}