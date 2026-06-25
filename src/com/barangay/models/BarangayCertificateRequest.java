package com.barangay.models;

public class BarangayCertificateRequest extends AbstractDocumentRequest {

    public BarangayCertificateRequest(String requestId, ResidentRecord resident, String purpose) {
        super(requestId, resident, purpose);
    }

    @Override
    public String getDocumentLabel() {
        return "Barangay Certificate";
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.BARANGAY_CERTIFICATE;
    }
}