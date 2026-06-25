package com.barangay.services;

import com.barangay.models.AbstractDocumentRequest;
import com.barangay.models.RequestStatus;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RequestSearchEngine {

    public List<AbstractDocumentRequest> filter(List<AbstractDocumentRequest> requests,
                                                Predicate<AbstractDocumentRequest> condition) {
        return requests.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    public List<AbstractDocumentRequest> filterByStatus(List<AbstractDocumentRequest> requests,
                                                        RequestStatus status) {
        return requests.stream()
                .filter(r -> r.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<AbstractDocumentRequest> searchByResidentName(List<AbstractDocumentRequest> requests,
                                                              String keyword) {
        String key = keyword.toLowerCase();

        return requests.stream()
                .filter(r -> r.getResident().getFullName().toLowerCase().contains(key))
                .collect(Collectors.toList());
    }
}