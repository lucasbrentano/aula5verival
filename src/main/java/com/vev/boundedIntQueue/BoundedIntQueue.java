package com.vev.boundedIntQueue;

public interface BoundedIntQueue {
    // retorna a capacidade da fila
    int capacity();
    // retorna a quantidade de elementos na fila
    int size();
    // retorna true se a fila está vazia
    boolean isEmpty();
    // retorna true se a fila está cheia
    boolean isFull();
    // insere um elemento no fim da
    // fila (gera QueueFullException se a fila está cheia)
    void addLast(int newElement);
    // retira o primeiro da fila (gera
    // QueueEmptyException se a fila está vazia)
    int removeFirst();
}