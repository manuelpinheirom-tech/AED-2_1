package dataStructures;

import dataStructures.exceptions.EmptyQueueException;
import dataStructures.exceptions.FullQueueException;

class QueueInArray<E> implements Queue<E> {
    @java.io.Serial
    private static final long serialVersionUID = 1L;
    
    // Default capacity of the stack.
    static final int DEFAULTCAPACITY = 1000;
    private final List<E> elements;
    private final int capacity;

    public QueueInArray( ){
        elements = new ArrayClass<>(DEFAULTCAPACITY);
        capacity=DEFAULTCAPACITY;
    }

    public QueueInArray(int capacity){
        elements = new ArrayClass<>(capacity);
        this.capacity=capacity;
    }

    //TODO: Left as an exercise.
    private boolean isFull() {
        return elements.size() == capacity;
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void enqueue(E element) {
        if (isFull())
            throw new FullQueueException();
        elements.addLast(element);
    }

    @Override
    public E peek() {
        return elements.getFirst();
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new EmptyQueueException();
        return elements.removeFirst();
    }
}
