package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * SepChain Hash Table
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value
 */
public class SepChainHashTable<K,V> extends HashTable<K,V> {

    //Load factors
    static final float IDEAL_LOAD_FACTOR =0.75f;
    static final float MAX_LOAD_FACTOR =0.9f;
    private static final int NUMCOLLISION = 3;

    // The array of set.
    private transient Set<Entry<K,V>>[] table;

    public SepChainHashTable( ){
        this(DEFAULT_CAPACITY);
    }
    
   public SepChainHashTable( int capacity ){
       super();
       table = createTable(capacity);
    }

    @SuppressWarnings("unchecked")
    private Set<Entry<K,V>>[] createTable(int capacity){
        Set<Entry<K,V>>[] aux;
        int arraySize = HashTable.nextPrime((int) (capacity / IDEAL_LOAD_FACTOR));
        maxSize = (int)(arraySize * MAX_LOAD_FACTOR);
        // Compiler gives a warning.
        aux = (Set<Entry<K,V>>[]) new Set[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            aux[i] = new SetLinkedList<>();
        return aux;
    }

    private void rehash() {
        //TODO: Left as an exercise.Done
        Set<Entry<K, V>>[] oldTable = table;
        table = createTable(table.length * 2);
        for ( int i = 0; i < oldTable.length; i++ ){
            Iterator<Entry<K,V>> it = oldTable[i].iterator();
            while(it.hasNext()){
                Entry<K,V> e = it.next();
                int newIndex = hash(e.getKey());
                table[newIndex].put(e);
            }
        }
        
    }
    // Returns the hash value of the specified key.
    int hash( K key ){
        return Math.abs( key.hashCode() ) % table.length;
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
        //TODO: Left as an exercise.Done
        int index = hash(key);
        Iterator<Entry<K,V>> it =  table[index].iterator();

        while(it.hasNext()){
            Entry<K,V> e = it.next();
            if(e.getKey().equals(key)){
                return e.getValue();
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
     * @return previous value associated with the key,
     * or null if the dictionary does not have an entry with that key
     */
    @Override
    public V put(K key, V value) {
        if (isFull())
            rehash();
        //TODO: Left as an exercise.
        int index = hash(key);
        Iterator<Entry<K, V>> it = table[index].iterator();

        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                table[index].remove(entry);
                table[index].put(new Entry<>(key, value));
                return oldValue;
            }
        }

        table[index].put(new Entry<>(key, value));
        currentSize++;
        return null;
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
    public V remove(K key) {
        //TODO: Left as an exercise.
        int index = hash(key);
        Iterator<Entry<K, V>> it = table[index].iterator();

        while (it.hasNext()) {
            Entry<K, V> entry = it.next();
            if (entry.getKey().equals(key)) {
                V value = entry.getValue();
                table[index].remove(entry);
                currentSize--;
                return value;
            }
        }
        return null;
    }

    /**
     * Returns an iterator of the entries in the dictionary.
     *
     * @return iterator of the entries in the dictionary
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new SepChainHTIterator<>(table);
    }

    void writeData(ObjectOutputStream oos)
            throws IOException {
        //TODO: Left as an exercise.
            oos.writeInt(currentSize);
            oos.writeInt(table.length);
            for (int i = 0; i < table.length; i++) {
                Iterator<Entry<K, V>> it = table[i].iterator();
                while (it.hasNext()) {
                    Entry<K, V> e = it.next();
                    oos.writeObject(e.getKey());
                    oos.writeObject(e.getValue());
                }
            }

    }

    void readData(ObjectInputStream ios)
            throws IOException, ClassNotFoundException {
        //TODO: Left as an exercise.
        int size = ios.readInt();
        int capacity = ios.readInt();

        table = createTable(capacity);
        this.currentSize = 0;

        for (int i = 0; i < size; i++) {
            K key = (K) ios.readObject();
            V value = (V) ios.readObject();
            this.put(key, value);
        }
    }
   
}
