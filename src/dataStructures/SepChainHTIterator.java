package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * SepChain Hash Table Iterator
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key
 * @param <V> Generic Value
 */

class SepChainHTIterator<K,V> implements Iterator<Map.Entry<K,V>> {


    private final Map<K,V>[] table;

    private int nextBucket;

    private Iterator<Map.Entry<K,V>> bucketIterator;

    //TODO: Left as an exercise.
    public SepChainHTIterator(Map<K,V>[] table) {
        this.table = table;
        rewind();
    }

    private void findNextBucket() {
        while ((bucketIterator == null || !bucketIterator.hasNext())
                && nextBucket < table.length) {
            Map<K,V> bucket = table[nextBucket++];
            bucketIterator = (bucket == null) ? null : bucket.iterator();
        }
    }

    @Override
    public boolean hasNext() {
        return bucketIterator != null && bucketIterator.hasNext();
    }

    @Override
    public Map.Entry<K, V> next() throws NoSuchElementException {
        if (!hasNext())
            throw new NoSuchElementException();
        Map.Entry<K,V> entry = bucketIterator.next();
        findNextBucket();
        return entry;
    }

    @Override
    public void rewind() {
        nextBucket = 0;
        bucketIterator = null;
        findNextBucket();
    }
}

