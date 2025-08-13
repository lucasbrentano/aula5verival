package com.boundedIntQueue;

import com.vev.boundedIntQueue.BoundedIntQueue;
import com.vev.boundedIntQueue.BoundedIntQueueImpl;
import com.vev.boundedIntQueue.QueueEmptyException;
import com.vev.boundedIntQueue.QueueFullException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BoundedIntQueue Tests")
public class BoundedIntQueueTests {
    
    private BoundedIntQueue queue;
    private final int DEFAULT_CAPACITY = 3;
    
    @BeforeEach
    void setUp() {
        queue = new BoundedIntQueueImpl(DEFAULT_CAPACITY);
    }
    
    // Testes do construtor
    @Test
    @DisplayName("Constructor with valid capacity should create queue")
    void testConstructorValidCapacity() {
        BoundedIntQueue newQueue = new BoundedIntQueueImpl(5);
        assertEquals(5, newQueue.capacity());
        assertEquals(0, newQueue.size());
        assertTrue(newQueue.isEmpty());
        assertFalse(newQueue.isFull());
    }
    
    @Test
    @DisplayName("Constructor with zero capacity should throw exception")
    void testConstructorZeroCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new BoundedIntQueueImpl(0));
    }
    
    @Test
    @DisplayName("Constructor with negative capacity should throw exception")
    void testConstructorNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new BoundedIntQueueImpl(-1));
    }
    
    // Testes do método capacity()
    @Test
    @DisplayName("capacity() should return correct capacity")
    void testCapacity() {
        assertEquals(DEFAULT_CAPACITY, queue.capacity());
        
        BoundedIntQueue largeQueue = new BoundedIntQueueImpl(100);
        assertEquals(100, largeQueue.capacity());
    }
    
    // Testes do método size()
    @Test
    @DisplayName("size() should return 0 for empty queue")
    void testSizeEmpty() {
        assertEquals(0, queue.size());
    }
    
    @Test
    @DisplayName("size() should return correct size after additions")
    void testSizeAfterAdditions() {
        queue.addLast(1);
        assertEquals(1, queue.size());
        
        queue.addLast(2);
        assertEquals(2, queue.size());
        
        queue.addLast(3);
        assertEquals(3, queue.size());
    }
    
    @Test
    @DisplayName("size() should return correct size after removals")
    void testSizeAfterRemovals() {
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        
        queue.removeFirst();
        assertEquals(2, queue.size());
        
        queue.removeFirst();
        assertEquals(1, queue.size());
        
        queue.removeFirst();
        assertEquals(0, queue.size());
    }
    
    // Testes do método isEmpty()
    @Test
    @DisplayName("isEmpty() should return true for new queue")
    void testIsEmptyNew() {
        assertTrue(queue.isEmpty());
    }
    
    @Test
    @DisplayName("isEmpty() should return false for non-empty queue")
    void testIsEmptyNonEmpty() {
        queue.addLast(1);
        assertFalse(queue.isEmpty());
    }
    
    @Test
    @DisplayName("isEmpty() should return true after all elements removed")
    void testIsEmptyAfterRemoval() {
        queue.addLast(1);
        queue.removeFirst();
        assertTrue(queue.isEmpty());
    }
    
    // Testes do método isFull()
    @Test
    @DisplayName("isFull() should return false for new queue")
    void testIsFullNew() {
        assertFalse(queue.isFull());
    }
    
    @Test
    @DisplayName("isFull() should return false for partially filled queue")
    void testIsFullPartial() {
        queue.addLast(1);
        assertFalse(queue.isFull());
        
        queue.addLast(2);
        assertFalse(queue.isFull());
    }
    
    @Test
    @DisplayName("isFull() should return true when capacity reached")
    void testIsFullAtCapacity() {
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        assertTrue(queue.isFull());
    }
    
    @Test
    @DisplayName("isFull() should return false after removal from full queue")
    void testIsFullAfterRemoval() {
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        queue.removeFirst();
        assertFalse(queue.isFull());
    }
    
    // Testes do método addLast()
    @Test
    @DisplayName("addLast() should add elements to empty queue")
    void testAddLastToEmpty() {
        queue.addLast(42);
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
    }
    
    @Test
    @DisplayName("addLast() should add multiple elements in order")
    void testAddLastMultiple() {
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        
        assertEquals(3, queue.size());
        assertTrue(queue.isFull());
    }
    
    @Test
    @DisplayName("addLast() should throw exception when queue is full")
    void testAddLastToFull() {
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        
        assertThrows(QueueFullException.class, () -> queue.addLast(4));
    }
    
    @Test
    @DisplayName("addLast() should handle negative numbers")
    void testAddLastNegative() {
        queue.addLast(-1);
        queue.addLast(-100);
        assertEquals(2, queue.size());
    }
    
    @Test
    @DisplayName("addLast() should handle zero")
    void testAddLastZero() {
        queue.addLast(0);
        assertEquals(1, queue.size());
    }
    
    @Test
    @DisplayName("addLast() should handle maximum integer")
    void testAddLastMaxInt() {
        queue.addLast(Integer.MAX_VALUE);
        assertEquals(1, queue.size());
    }
    
    @Test
    @DisplayName("addLast() should handle minimum integer")
    void testAddLastMinInt() {
        queue.addLast(Integer.MIN_VALUE);
        assertEquals(1, queue.size());
    }
    
    // Testes do método removeFirst()
    @Test
    @DisplayName("removeFirst() should throw exception when queue is empty")
    void testRemoveFirstFromEmpty() {
        assertThrows(QueueEmptyException.class, () -> queue.removeFirst());
    }
    
    @Test
    @DisplayName("removeFirst() should return first element added")
    void testRemoveFirstSingle() {
        queue.addLast(42);
        int result = queue.removeFirst();
        assertEquals(42, result);
        assertTrue(queue.isEmpty());
    }
    
    @Test
    @DisplayName("removeFirst() should maintain FIFO order")
    void testRemoveFirstFIFO() {
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        
        assertEquals(1, queue.removeFirst());
        assertEquals(2, queue.removeFirst());
        assertEquals(3, queue.removeFirst());
    }
    
    @Test
    @DisplayName("removeFirst() should handle negative numbers")
    void testRemoveFirstNegative() {
        queue.addLast(-5);
        assertEquals(-5, queue.removeFirst());
    }
    
    @Test
    @DisplayName("removeFirst() should handle zero")
    void testRemoveFirstZero() {
        queue.addLast(0);
        assertEquals(0, queue.removeFirst());
    }
    
    @Test
    @DisplayName("removeFirst() should handle extreme values")
    void testRemoveFirstExtremeValues() {
        queue.addLast(Integer.MAX_VALUE);
        queue.addLast(Integer.MIN_VALUE);
        
        assertEquals(Integer.MAX_VALUE, queue.removeFirst());
        assertEquals(Integer.MIN_VALUE, queue.removeFirst());
    }
    
    // Testes de integração e cenários complexos
    @Test
    @DisplayName("Queue should handle mixed add/remove operations")
    void testMixedOperations() {
        queue.addLast(1);
        assertEquals(1, queue.removeFirst());
        
        queue.addLast(2);
        queue.addLast(3);
        assertEquals(2, queue.removeFirst());
        
        queue.addLast(4);
        assertEquals(3, queue.removeFirst());
        assertEquals(4, queue.removeFirst());
        
        assertTrue(queue.isEmpty());
    }
    
    @Test
    @DisplayName("Queue should allow refilling after becoming empty")
    void testRefillingAfterEmpty() {
        // Fill and empty
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        
        queue.removeFirst();
        queue.removeFirst();
        queue.removeFirst();
        
        // Refill
        queue.addLast(4);
        queue.addLast(5);
        queue.addLast(6);
        
        assertEquals(4, queue.removeFirst());
        assertEquals(5, queue.removeFirst());
        assertEquals(6, queue.removeFirst());
    }
    
    @Test
    @DisplayName("Queue should handle capacity of 1")
    void testCapacityOne() {
        BoundedIntQueue smallQueue = new BoundedIntQueueImpl(1);
        
        smallQueue.addLast(100);
        assertTrue(smallQueue.isFull());
        
        assertThrows(QueueFullException.class, () -> smallQueue.addLast(200));
        
        assertEquals(100, smallQueue.removeFirst());
        assertTrue(smallQueue.isEmpty());
    }
    
    @Test
    @DisplayName("Queue should handle large capacity")
    void testLargeCapacity() {
        BoundedIntQueue largeQueue = new BoundedIntQueueImpl(1000);
        
        for (int i = 0; i < 1000; i++) {
            largeQueue.addLast(i);
        }
        
        assertTrue(largeQueue.isFull());
        assertEquals(1000, largeQueue.size());
        
        assertThrows(QueueFullException.class, () -> largeQueue.addLast(1000));
    }
    
    // Testes de estado após exceções
    @Test
    @DisplayName("Queue state should remain unchanged after QueueFullException")
    void testStateAfterQueueFullException() {
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        
        int sizeBefore = queue.size();
        boolean isFullBefore = queue.isFull();
        
        assertThrows(QueueFullException.class, () -> queue.addLast(4));
        
        assertEquals(sizeBefore, queue.size());
        assertEquals(isFullBefore, queue.isFull());
    }
    
    @Test
    @DisplayName("Queue state should remain unchanged after QueueEmptyException")
    void testStateAfterQueueEmptyException() {
        int sizeBefore = queue.size();
        boolean isEmptyBefore = queue.isEmpty();
        
        assertThrows(QueueEmptyException.class, () -> queue.removeFirst());
        
        assertEquals(sizeBefore, queue.size());
        assertEquals(isEmptyBefore, queue.isEmpty());
    }
}
