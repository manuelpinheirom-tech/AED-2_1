package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * Breadth First Search Binary Tree iterator
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 */

class BreadthIterator<E> implements Iterator<E> {
    private final BTNode<E> root;
    private SinglyLinkedList<BTNode<E>> queue;

    public BreadthIterator(BTNode<E> root) {
        this.root = root;
        rewind();
    }

    @Override
    public boolean hasNext() { return !queue.isEmpty(); }

    @Override
    public E next() {
        if (!hasNext()) throw new NoSuchElementException();
        BTNode<E> cur = queue.removeFirst();
        if (cur.getLeftChild() != null)  queue.addLast((BTNode<E>) cur.getLeftChild());
        if (cur.getRightChild() != null) queue.addLast((BTNode<E>) cur.getRightChild());
        return cur.getElement();
    }

    @Override
    public void rewind() {
        queue = new SinglyLinkedList<>();
        if (root != null) queue.addLast(root);
    }
}
