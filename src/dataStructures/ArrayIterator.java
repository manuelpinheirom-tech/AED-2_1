package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

public class ArrayIterator<T> implements TwoWayIterator<T> {
	private static final int DIFF=2;

	private final T[] elems;
	private final int counter;
	private int currentN;
	private int currentP;

    @SuppressWarnings("this-escape")
	public ArrayIterator(T[] elems, int counter) {
		this.elems = elems;
		this.counter = counter;
		rewind();
	}
    //TODO: Left as an exercise.
    @Override
    public boolean hasPrevious() {
        return currentP >= 0;
    }

    @Override
    public T previous() throws NoSuchElementException {
        if(!hasPrevious())
            throw new NoSuchElementException();
        T element = elems[currentP];
        currentN = currentP;
        currentP--;
        return element;
    }

    @Override
    public void fullForward() {
        currentN = counter;
        currentP = counter - 1;
    }

    @Override
    public boolean hasNext() {
        return currentN < counter;
    }

    @Override
    public T next() throws NoSuchElementException {
        if(!hasNext())
            throw new NoSuchElementException();
        T element = elems[currentN];
        currentP = currentN;
        currentN++;
        return element;
    }

    @Override
    public void rewind() {
        currentN = 0;
        currentP = 0;
    }

}
