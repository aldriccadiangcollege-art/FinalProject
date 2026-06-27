package com.barangay.services;

import com.barangay.models.*;
import java.util.*;
import java.util.stream.Collectors;

public class TransactionService {
    private final List<Account> accounts = new ArrayList<>();
    private final List<Transfer> history = new ArrayList<>();

    public void addAccount(String id, String name) { accounts.add(new Account(id, name)); }
    public List<Account> getAccounts() { return new ArrayList<>(accounts); }

    public void transfer(Account from, Account to, double amt) {
        String ref = "REF-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        history.add(new Transfer(ref, from, to, amt, java.time.LocalDateTime.now()));
    }

    public List<Account> searchAccountsByName(String nameQuery) {
        return accounts.stream()
                .filter(a -> a.name().toLowerCase().contains(nameQuery.toLowerCase()))
                .collect(Collectors.toList());
    }

    // --- REPLACEMENT STARTS HERE ---
    public List<Transfer> getSortedHistory() {
        return history.stream()
                // Change: Sort by the 'date' field in the Transfer model
                // .reversed() ensures the newest transaction appears at the top
                .sorted(Comparator.comparing(Transfer::date).reversed())
                .collect(Collectors.toList());
    }
    // --- REPLACEMENT ENDS HERE ---
}