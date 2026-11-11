package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * Breadth First Search Binary Tree iterator
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 */

class BreadthIterator<E> implements Iterator<E> {
    //TODO: Left as an exercise.

    private final BTNode<E> root;

    private Queue<BTNode<E>> queue;

    public BreadthIterator(BTNode<E> root) {
        this.root = root;
        this.rewind();
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public E next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        BTNode<E> currentNode = queue.dequeue();
        if(currentNode.getLeftChild() != null) {
            queue.enqueue((BTNode<E>) currentNode.getLeftChild());
        }
        if(currentNode.getRightChild() != null) {
            queue.enqueue((BTNode<E>) currentNode.getRightChild());
        }
        return currentNode.getElement();
    }

    @Override
    public void rewind() {
        queue = new QueueInArray<BTNode<E>>();
    }

}
