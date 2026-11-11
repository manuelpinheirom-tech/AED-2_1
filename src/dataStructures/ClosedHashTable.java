package dataStructures;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Closed Hash Table
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class ClosedHashTable<K,V> extends HashTable<K,V> {

    //Load factors
    static final float IDEAL_LOAD_FACTOR =0.5f;
    static final float MAX_LOAD_FACTOR =0.8f;
    static final int NOT_FOUND=-1;

    // removed cell
    static final Entry<?,?> REMOVED_CELL = new Entry<>(null,null);

    // The array of entries.
    private transient Entry<K,V>[] table;
    

    /**
     * Constructors
     */

    public ClosedHashTable( ){
        this(DEFAULT_CAPACITY);
    }

    public ClosedHashTable( int capacity ){
        super();
        table=createTable(capacity);
    }

    @SuppressWarnings("unchecked")
    private Entry<K,V>[] createTable(int capacity) {
        int arraySize = HashTable.nextPrime((int) (capacity / IDEAL_LOAD_FACTOR));
        maxSize = (int)(arraySize * MAX_LOAD_FACTOR);
        // Compiler gives a warning.
        Entry<K,V>[] aux = (Entry<K,V>[]) new Entry<?,?>[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            aux[i] = null;
        return aux;
    }

    //Methods for handling collisions. Only one of them should be used!
    // Returns the hash value of the specified key.
    int hash( K key, int i ){
        return Math.abs( key.hashCode() + i) % table.length;
    }
    /**
     * Linear Proving
     * @param key to search
     * @return the index of the table, where is the entry with the specified key, or null
     */
    int searchLinearProving(K key) {
        //TODO: Left as an exercise.
        for (int i = 0; i < table.length; i++) {
            int id = hash(key, i);
            Entry<K,V> e = table[id];
            if (e == null)
                return NOT_FOUND;
            if (e != REMOVED_CELL && e.getKey().equals(key))
                return id;
        }
        return NOT_FOUND;
    }

    
    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * returns its value; otherwise, returns null.
     *
     * @param key whose associated value is to be returned
     * @return value of entry in the dictionary whose key is the specified key,
     * or null if the dictionary does not have an entry with that key
     */
    @Override
    public V get(K key) {
        //TODO: Left as an exercise.
        int idx = searchLinearProving(key);
        if (idx == NOT_FOUND)
            return null;
        return table[idx].getValue();
    }

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * replaces its value by the specified value and returns the old value;
     * otherwise, inserts the entry (key, value) and returns null.
     *
     * @param key   with which the specified value is to be associated
     * @param value to be associated with the specified key
     * @return previous value associated with the key,
     * or null if the dictionary does not have an entry with that key
     */
    @Override
    public V put(K key, V value) {
        if (isFull())
            rehash();
        //TODO: Left as an exercise.
        int firstFree = NOT_FOUND;
        for (int i = 0; i < table.length; i++) {
            int idx = hash(key, i);
            Entry<K,V> e = table[idx];
            if (e == null) {
                int pos = (firstFree != NOT_FOUND) ? firstFree : idx;
                table[pos] = new Entry<>(key, value);
                currentSize++;
                return null;
            }
            if (e == REMOVED_CELL) {
                if (firstFree == NOT_FOUND)
                    firstFree = idx;
            }
            else if (e.getKey().equals(key)) {
                V old = e.getValue();
                table[idx] = new Entry<>(key, value);
                return old;
            }
        }
        if (firstFree != NOT_FOUND) {
            table[firstFree] = new Entry<>(key, value);
            currentSize++;
        }
        return null;
    }

    
    private void rehash() {
        //TODO: Left as an exercise.
        Entry<K,V>[] old = table;
        table = createTable(old.length);
        currentSize = 0;
        for (Entry<K,V> e : old)
            if (e != null && e != REMOVED_CELL)
                put(e.getKey(), e.getValue());
    }

    /**
     * If there is an entry in the dictionary whose key is the specified key,
     * removes it from the dictionary and returns its value;
     * otherwise, returns null.
     *
     * @param key whose entry is to be removed from the map
     * @return previous value associated with the key,
     * or null if the dictionary does not an entry with that key
     */
    @Override
    @SuppressWarnings("unchecked")
    public V remove(K key) {
        //TODO: Left as an exercise.
        int idx = searchLinearProving(key);
        if (idx == NOT_FOUND)
            return null;
        V value = table[idx].getValue();
        table[idx] = (Entry<K,V>) REMOVED_CELL;
        currentSize--;
        return value;
    }

    /**
     * Returns an iterator of the entries in the dictionary.
     *
     * @return iterator of the entries in the dictionary
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new FilterIterator<>(
                new ArrayIterator<>(table,table.length),elem-> elem!=null && elem!= REMOVED_CELL);
    }

    void writeData(ObjectOutputStream oos)
            throws IOException {
        //TODO: Left as an exercise.
        oos.writeInt(currentSize);
        for (Entry<K,V> e : table)
            if (e != null && e != REMOVED_CELL) {
                oos.writeObject(e.getKey());
                oos.writeObject(e.getValue());
            }
    }

    @SuppressWarnings("unchecked")
    void readData(ObjectInputStream ios)
            throws IOException, ClassNotFoundException {
        //TODO: Left as an exercise.
        int n = ios.readInt();
        table = createTable(n > 0 ? n : DEFAULT_CAPACITY);
        currentSize = 0;
        for (int i = 0; i < n; i++) {
            K key = (K) ios.readObject();
            V value = (V) ios.readObject();
            put(key, value);
        }
    }
}
