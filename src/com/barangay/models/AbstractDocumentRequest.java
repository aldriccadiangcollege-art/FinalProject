package com.barangay.models;


import java.time.LocalDateTime;
import java.util.Objects;

public abstract class AbstractDocumentRequest implements DocumentRequest, Identifiable, Trackable, Comparable<AbstractDocumentRequest> {
    private String requestId;
    private ResidentRecord resident;
    private String purpose;
    private RequestStatus status;
    private final LocalDateTime createdAt;

    public AbstractDocumentRequest(String requestId, ResidentRecord resident, String purpose) {
        setRequestId(requestId);
        setResident(resident);
        setPurpose(purpose);
        this.status = RequestStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        if (requestId == null || requestId.trim().isEmpty()) {
            throw new InvalidInputException("Request ID cannot be empty.");
        }
        this.requestId = requestId.trim();
    }

    public ResidentRecord getResident() {
        return resident;
    }

    public void setResident(ResidentRecord resident) {
        if (resident == null) {
            throw new InvalidInputException("Resident cannot be null.");
        }
        this.resident = resident;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        if (purpose == null || purpose.trim().isEmpty()) {
            throw new InvalidInputException("Purpose cannot be empty.");
        }
        this.purpose = purpose.trim();
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        if (status == null) {
            throw new InvalidInputException("Status cannot be null.");
        }
        this.status = status;
    }

    @Override
    public String getId() {
        return requestId;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public abstract DocumentType getDocumentType();

    @Override
    public int compareTo(AbstractDocumentRequest other) {
        return this.createdAt.compareTo(other.createdAt);
    }

    @Override
    public String toString() {
        return "Request ID: " + requestId +
                "\nResident: " + resident.getFullName() +
                "\nDocument: " + getDocumentLabel() +
                "\nPurpose: " + purpose +
                "\nStatus: " + status +
                "\nDate Requested: " + createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractDocumentRequest that = (AbstractDocumentRequest) o;
        return Objects.equals(requestId, that.requestId) &&
                Objects.equals(resident, that.resident) &&
                Objects.equals(purpose, that.purpose) &&
                status == that.status &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, resident, purpose, status, createdAt);
    }
}