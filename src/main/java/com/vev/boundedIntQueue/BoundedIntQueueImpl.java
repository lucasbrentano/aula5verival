package com.vev.boundedIntQueue;

import java.util.ArrayList;
import java.util.List;

public class BoundedIntQueueImpl implements BoundedIntQueue {
    private final int capacity;
    private final List<Integer> queue;
    
    public BoundedIntQueueImpl(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.queue = new ArrayList<>();
    }
    
    @Override
    public int capacity() {
        return capacity;
    }
    
    @Override
    public int size() {
        return queue.size();
    }
    
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    @Override
    public boolean isFull() {
        return queue.size() >= capacity;
    }
    
    @Override
    public void addLast(int value) {
        if (isFull()) {
            throw new QueueFullException();
        }
        queue.add(value);
    }
    
    @Override
    public int removeFirst() {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }
        return queue.remove(0);
    }
}
