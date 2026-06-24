package com.barangay.models;

public class BarangayClearanceRequest extends AbstractDocumentRequest {

    public BarangayClearanceRequest(String requestId, ResidentRecord resident, String purpose) {
        super(requestId, resident, purpose);
    }

    @Override
    public String getDocumentLabel() {
        return "Barangay Clearance";
    }

    @Override
    public DocumentType getDocumentType() {
        return DocumentType.BARANGAY_CLEARANCE;
    }
}