package com.barangay.services;

import java.util.ArrayList;
import java.util.List;

public class GenericRepository<T> implements Repository<T> {
    private final List<T> items = new ArrayList<>();

    @Override
    public void add(T item) {
        items.add(item);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(items);
    }
}