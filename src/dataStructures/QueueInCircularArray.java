package dataStructures;

import dataStructures.exceptions.EmptyQueueException;
import dataStructures.exceptions.FullQueueException;

public class QueueInCircularArray<E> implements Queue<E>{

    // Default capacity of the queue.
    static final int DEFAULT_CAPACITY = 1000;

    // Memory of the queue: a circular array.
    private E[] array;

    // Index of the element at the front of the queue.
    private int front;

    // Number of elements in the queue.
    private int currentSize;

    @SuppressWarnings("unchecked")
    public QueueInCircularArray( int capacity ) {
        // Compiler gives a warning.
        array = (E[]) new Object[capacity];
        front = 0;
        currentSize = 0;
    }

    public QueueInCircularArray( ) {
        this(DEFAULT_CAPACITY);
    }

    //TODO: Left as an exercise.
    private boolean isFull() {
        return currentSize == array.length;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public void enqueue(E element) {
        if (isFull())
            throw new FullQueueException();
        int rear = (front + currentSize) % array.length;
        array[rear] = element;
        currentSize++;
    }

    @Override
    public E peek() {
        return array[front];
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new EmptyQueueException();
        E element = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        currentSize--;
        return element;
    }
}
