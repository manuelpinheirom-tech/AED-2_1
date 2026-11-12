package dataStructures;

import dataStructures.exceptions.EmptyMapException;
import dataStructures.Map.Entry;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Binary Search Tree Sorted Map
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class BSTree<K extends Comparable<K>,V> extends BTree<Entry<K,V>> implements SortedMap<K,V> {
    /**
     * Comparator
     */
    private Comparator<K> comparator;
    /**
     * Default comparator
     * @param <K>
     */
    static class DefaultComparator<K extends Comparable<K>> implements Comparator<K> {
        public int compare(K a, K b)  {
            return a.compareTo(b);
        }
    }
    /**
     * Constructors
     */
    public BSTree(){
        super();
        comparator= new DefaultComparator<>();
    }

    public BSTree(Comparator<K> compare){
        super();
        comparator = compare;
    }
    /**
     * Returns the entry with the smallest key in the map.
     *
     * @return
     * @throws EmptyMapException
     */
    public Entry<K, V> minEntry() {
        if (isEmpty())
            throw new EmptyMapException();
        return furtherLeftElement().getElement();
    }

    /**
     * Returns the entry with the largest key in the map.
     *
     * @return
     * @throws EmptyMapException
     */
    public Entry<K, V> maxEntry() {
        if (isEmpty())
            throw new EmptyMapException();
        return furtherRightElement().getElement();
    }

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * returns its value; otherwise, returns null.
     *
     * @param key whose associated value is to be returned
     * @return value of entry in the dictionary whose key is the specified key,
     * or null if the dictionary does not have an entry with that key
     */
    public V get(K key) {
        //TODO: Left as an exercise.
        BTNode<Entry<K,V>> node = (BTNode<Entry<K,V>>) root;
        while (node != null) {
            int comp = comparator.compare(key, node.getElement().getKey());
            if (comp == 0) {
                return node.getElement().getValue();
            }else if (comp > 0) {
                node = (BTNode<Entry<K, V>>) node.getRightChild();
            }else{
                node = (BTNode<Entry<K, V>>) node.getLeftChild();
            }
        }
        return null;
    }

    
    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * replaces its value by the specified value and returns the old value;
     * otherwise, inserts the entry (key, value) and returns null.
     *
     * @param key   with which the specified value is to be associated
     * @param value to be associated with the specified key
     * @return previous value associated with key,
     * or null if the dictionary does not have an entry with that key
     */
    public V put(K key, V value) {
        //TODO: Left as an exercise.
        BTNode<Entry<K,V>> current = (BTNode<Entry<K,V>>) root;
        BTNode<Entry<K,V>> parent = null;
        int comp = 0;

        while (current != null) {
            comp = comparator.compare(key, current.getElement().key());
            if (comp == 0) {
                V oldValue = current.getElement().value();
                current.setElement(new Entry<>(key, value));
                return oldValue;
            }
            parent = current;
            if (comp < 0)
                current = (BTNode<Entry<K,V>>) current.getLeftChild();
            else
                current = (BTNode<Entry<K,V>>) current.getRightChild();
        }

        BTNode<Entry<K,V>> newNode = new BTNode<>(new Entry<>(key, value), parent);

        if (parent == null)
            root = newNode;
        else if (comp < 0)
            parent.setLeftChild(newNode);
        else
            parent.setRightChild(newNode);

        currentSize++;
        return null;
    }


    /**
     * If there is an entry in the map whose key is the specified key,
     * removes it from the map and returns its value;
     * otherwise, returns null.
     *
     * @param key whose entry is to be removed from the map
     * @return previous value associated with key,
     * or null if the dictionary does not an entry with that key
     */
    public V remove(K key) {
        //TODO: Left as an exercise.
        BTNode<Entry<K,V>> node = (BTNode<Entry<K,V>>) root;
        BTNode<Entry<K,V>> parent = null;
        while (node != null) {
            int comp = comparator.compare(key, node.getElement().key());
            if (comp == 0) {
                break;
            }
            parent = node;
            if (comp < 0)
                node = (BTNode<Entry<K,V>>) node.getLeftChild();
            else
                node = (BTNode<Entry<K,V>>) node.getRightChild();
        }
        if (node == null)
            return null;
        V oldValue = node.getElement().value();
        BTNode<Entry<K,V>> replacement;
        if (node.getLeftChild() == null) {
            replacement = (BTNode<Entry<K,V>>) node.getRightChild();
        } else if (node.getRightChild() == null) {
            replacement = (BTNode<Entry<K,V>>) node.getLeftChild();
        } else {
            BTNode<Entry<K,V>> succParent = node;
            BTNode<Entry<K,V>> succ = (BTNode<Entry<K,V>>) node.getRightChild();
            while (succ.getLeftChild() != null) {
                succParent = succ;
                succ = (BTNode<Entry<K,V>>) succ.getLeftChild();
            }
            if (succParent != node) {
                InfoNode<Entry<K,V>> succRight = succ.getRightChild();
                succParent.setLeftChild(succRight);
                if (succRight != null)
                    ((BTNode<Entry<K,V>>) succRight).setParent(succParent);

                succ.setRightChild(node.getRightChild());
                ((BTNode<Entry<K,V>>) node.getRightChild()).setParent(succ);
            }
            succ.setLeftChild(node.getLeftChild());
            ((BTNode<Entry<K,V>>) node.getLeftChild()).setParent(succ);
            replacement = succ;
        }
        if (replacement != null)
            replacement.setParent(parent);
        if (parent == null)
            root = replacement;
        else if (parent.getLeftChild() == node)
            parent.setLeftChild(replacement);
        else
            parent.setRightChild(replacement);
        currentSize--;
        return oldValue;
        }

    private BTNode<Entry<K,V>> findMin(BTNode<Entry<K,V>> node) {
        while (node.getLeftChild() != null) {
            node = (BTNode<Entry<K, V>>) node.getLeftChild();
        }
        return node;
        }

    /**
     * Returns an iterator of the entries in the dictionary.
     *
     * @return iterator of the entries in the dictionary
     */
    public Iterator<Entry<K, V>> iterator() {
        return new InOrderIterator<>((BTNode<Entry<K,V>>) root);
    }

    //Manual Serialization
    Iterator<Entry<K, V>> iteratorLevel() {
        return new BreadthIterator<>((BTNode<Entry<K,V>>)root);
    }

    void addElem(Entry<K, V> element) {
        put(element.key(),element.value());
    }

    void writeData(ObjectOutputStream oos) throws IOException {
        oos.writeObject(comparator);
    }

    void readData(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        comparator = (Comparator<K>) ois.readObject();
    }

}
