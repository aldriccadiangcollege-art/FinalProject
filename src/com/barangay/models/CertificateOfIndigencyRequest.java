package com.barangay.models;

public class CertificateOfIndigencyRequest extends AbstractDocumentRequest {

    public CertificateOfIndigencyRequest(String requestId, ResidentRecord resident, String purpose) {
        super(requestId, resident, purpose);
    }

    @Override
    public String getDocumentLabel() {
        return "Certificate of Indigency";
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.CERTIFICATE_OF_INDIGENCY;
    }
}