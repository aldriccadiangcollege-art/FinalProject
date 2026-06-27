package com.barangay.models;
import java.time.LocalDateTime;

public record Transfer(String ref, Account from, Account to, double amount, LocalDateTime date) {
    @Override public String toString() {
        return String.format("[%s] Ref: %s | From: %s | To: %s | Amount: PHP %.2f", 
                             date.toLocalDate(), ref, from.name(), to.name(), amount);
    }
}