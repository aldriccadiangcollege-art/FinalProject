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

    public List<Account> getAccountsWithPaymentHistory() {
        return accounts.stream()
                .filter(a -> history.stream().anyMatch(t -> t.from().equals(a) || t.to().equals(a)))
                .collect(Collectors.toList());
    }

    public List<Transfer> getSortedHistory() {
        return history.stream()
                .sorted(Comparator.comparing(Transfer::date).reversed())
                .collect(Collectors.toList());
    }
}