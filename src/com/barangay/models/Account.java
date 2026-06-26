package com.barangay.models;
public record Account(String id, String name) {
    @Override public String toString() { return name + " (" + id + ")"; }
}