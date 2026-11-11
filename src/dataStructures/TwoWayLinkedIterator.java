package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

/**
 * Implementation of Two Way Iterator for DLList 
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 * 
 */
class TwoWayLinkedIterator<E> extends LinkedIterator<E>
        implements TwoWayIterator<E> {
    /**
     * Node with the last element in the iteration.
     */
    private final LinkedNode<E> lastNode;
    /**
     * Node with the previous element in the iteration.
     */
    private LinkedNode<E> prevToReturn;

    /**
     * DoublyLLIterator constructor
     *
     * @param first - Node with the first element of the iteration
     * @param last  - Node with the last element of the iteration
     */
    public TwoWayLinkedIterator(LinkedNode<E> first, LinkedNode<E> last) {
        super(first);
        prevToReturn=null;
        lastNode=last;
    }

    //TODO: Left as an exercise.

    public boolean hasNext() {
        return super.hasNext();
    }

    public E next() throws NoSuchElementException {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        prevToReturn = nextToReturn;
        E element = nextToReturn.getElement();
        nextToReturn=prevToReturn.getNext();
        return element;
    }

    public boolean hasPrevious(){
        return prevToReturn!=null;
    }

    public E previous() throws NoSuchElementException {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        E element = prevToReturn.getElement();
        nextToReturn = prevToReturn;
        if (prevToReturn instanceof DoublyListNode) {
            prevToReturn = ((DoublyListNode<E>) prevToReturn).getPrevious();
        } else {
            prevToReturn = null;
        }
        return element;
    }

    public void fullForward() {
        nextToReturn = null;
        prevToReturn = lastNode;
    }

    @Override
    public void rewind() {
        super.rewind();
        prevToReturn = null;
    }
}
