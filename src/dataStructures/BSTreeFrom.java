package dataStructures;

import dataStructures.Map.Entry;
import dataStructures.exceptions.NoSuchElementException;


public class BSTreeFrom<K extends Comparable<K>, V> extends BSTree<K, V> {

    public BSTreeFrom() {
        super();
    }


    public Iterator<Entry<K, V>> iteratorFrom(K from) {
        return new FromIterator(from);
    }

    private class FromIterator implements Iterator<Entry<K, V>> {
        private final K from;
        private BTNode<Entry<K, V>> next;

        FromIterator(K from) {
            this.from = from;
            rewind();
        }


        @Override
        public void rewind() {
            BTNode<Entry<K, V>> candidate = null;
            BTNode<Entry<K, V>> node = (BTNode<Entry<K, V>>) root;
            while (node != null) {
                if (node.getElement().key().compareTo(from) >= 0) {
                    candidate = node;
                    node = (BTNode<Entry<K, V>>) node.getLeftChild();
                } else {
                    node = (BTNode<Entry<K, V>>) node.getRightChild();
                }
            }
            next = candidate;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Entry<K, V> elem = next.getElement();
            advance();
            return elem;
        }

        private void advance() {
            if (next.getRightChild() != null) {
                next = ((BTNode<Entry<K, V>>) next.getRightChild()).furtherLeftElement();
            } else {
                BTNode<Entry<K, V>> child = next;
                BTNode<Entry<K, V>> parent = (BTNode<Entry<K, V>>) next.getParent();
                while (parent != null && parent.getRightChild() == child) {
                    child = parent;
                    parent = (BTNode<Entry<K, V>>) parent.getParent();
                }
                next = parent;
            }
        }
    }
}