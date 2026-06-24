package com.barangay.models;

import com.barangay.ui.CustomException;

import java.time.LocalDate;
import java.util.Objects;

public class ResidentRecord {
    private String residentId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String address;

    public ResidentRecord(String residentId, String firstName, String lastName,
                          LocalDate birthDate, String gender, String address) {
        setResidentId(residentId);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthDate(birthDate);
        setGender(gender);
        setAddress(address);
    }

    public String getResidentId() {
        return residentId;
    }

    public void setResidentId(String residentId) {
        if (residentId == null || residentId.trim().isEmpty()) {
            throw new CustomException("Resident ID cannot be empty.");
        }
        this.residentId = residentId.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new CustomException("First name cannot be empty.");
        }
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new CustomException("Last name cannot be empty.");
        }
        this.lastName = lastName.trim();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new CustomException("Birth date cannot be null.");
        }
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender == null || gender.trim().isEmpty()) {
            throw new CustomException("Gender cannot be empty.");
        }
        this.gender = gender.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new CustomException("Address cannot be empty.");
        }
        this.address = address.trim();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Resident ID: " + residentId +
                "\nName: " + getFullName() +
                "\nBirth Date: " + birthDate +
                "\nGender: " + gender +
                "\nAddress: " + address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResidentRecord that = (ResidentRecord) o;
        return Objects.equals(residentId, that.residentId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(residentId, firstName, lastName, birthDate, gender, address);
    }
}