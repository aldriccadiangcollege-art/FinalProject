package com.barangay.services;

import com.barangay.models.AbstractDocumentRequest;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RequestSorter {

    public List<AbstractDocumentRequest> sortByDate(List<AbstractDocumentRequest> requests) {
        return requests.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<AbstractDocumentRequest> sortByResidentName(List<AbstractDocumentRequest> requests) {
        return requests.stream()
                .sorted(Comparator.comparing(r -> r.getResident().getFullName().toLowerCase()))
                .collect(Collectors.toList());
    }
}