package com.vev.boundedIntQueue;

public class QueueFullException extends RuntimeException {
    public QueueFullException() {
        super("Queue is full");
    }
    
    public QueueFullException(String message) {
        super(message);
    }
}
