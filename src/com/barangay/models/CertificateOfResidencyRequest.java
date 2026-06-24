package com.barangay.models;

public class CertificateOfResidencyRequest extends AbstractDocumentRequest {

    public CertificateOfResidencyRequest(String requestId, ResidentRecord resident, String purpose) {
        super(requestId, resident, purpose);
    }

    @Override
    public String getDocumentLabel() {
        return "Certificate of Residency";
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.CERTIFICATE_OF_RESIDENCY;
    }
}