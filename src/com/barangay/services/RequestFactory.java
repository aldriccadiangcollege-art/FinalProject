package com.barangay.services;

import com.barangay.models.AbstractDocumentRequest;
import com.barangay.models.BarangayCertificateRequest;
import com.barangay.models.BarangayClearanceRequest;
import com.barangay.models.CertificateOfIndigencyRequest;
import com.barangay.models.CertificateOfResidencyRequest;
import com.barangay.models.DocumentType;
import com.barangay.models.InvalidInputException;
import com.barangay.models.ResidentRecord;

public class RequestFactory {

    public AbstractDocumentRequest createRequest(String requestId, ResidentRecord resident,
                                                 DocumentType type, String purpose) {
        if (type == null) {
            throw new InvalidInputException("Document type cannot be null.");
        }

        return switch (type) {
            case BARANGAY_CERTIFICATE ->
                    new BarangayCertificateRequest(requestId, resident, purpose);
            case BARANGAY_CLEARANCE ->
                    new BarangayClearanceRequest(requestId, resident, purpose);
            case CERTIFICATE_OF_RESIDENCY ->
                    new CertificateOfResidencyRequest(requestId, resident, purpose);
            case CERTIFICATE_OF_INDIGENCY ->
                    new CertificateOfIndigencyRequest(requestId, resident, purpose);
        };
    }
}