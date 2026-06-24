package com.barangay.services;

import java.util.List;

public interface Repository<T> {
    void add(T item);
    List<T> getAll();
}