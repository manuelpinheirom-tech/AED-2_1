package dataStructures;

public class SetLinkedList<E> extends LinkedList<E> implements Set<E> {

    public SetLinkedList(){
        super();
    }

    @Override
    void addElem(E element) {
        //TODO: Left as an exercise.
        DoublyListNode<E> newNode = new DoublyListNode<>(element, tail, null);
        if (head == null)
            head = newNode;
        else
            tail.setNext(newNode);
        tail = newNode;
        currentSize++;
    }

    private DoublyListNode<E> findNode(E element) {
        DoublyListNode<E> node = (DoublyListNode<E>) head;
        while (node != null) {
            if (node.getElement().equals(element))
                return node;
            node = (DoublyListNode<E>) node.getNext();
        }
        return null;
    }

    /**
     * If there is an element in the set equals to a specified element,
     * returns the element; otherwise, returns null.
     *
     * @param element
     * @return an element in the set equals to a specified element,
     * or null otherwise
     */
    @Override
    public E get(E element) {
        //TODO: Left as an exercise.
        DoublyListNode<E> node = (DoublyListNode<E>) head;
        while (node != null) {
            if (node.getElement().equals(element))
                return node.getElement();
            node = (DoublyListNode<E>) node.getNext();
        }
        return null;
        
    }

    /**
     * If there is an element in the set equals to a specified element,
     * returns true; otherwise, returns false.
     *
     * @param element
     * @return
     */
    @Override
    public boolean contains(E element) {
        if (isEmpty())
            return false;
        return get(element)!=null;
    }

    /**
     * Inserts the element,
     * if there is not an element in the set equals to the specified element and return true.
     * otherwise returns false
     * @param element to be added
     * @return true if insert the element, otherwise false
     */
    public boolean put(E element){
        //TODO: Left as an exercise.
        if (findNode(element) != null)
            return false;
        addElem(element);
        invariant();
        return true;
    }

    /**
     * If there is an element in the set equals to the specified element
     * replace the element and return old element.
     * otherwise returns null
     *
     * @param element to be added
     * @return old element, otherwise null
     */
    @Override
    public E replace(E element) {
        //TODO: Left as an exercise.
        DoublyListNode<E> node = findNode(element);
        if (node == null)
            return null;
        E old = node.getElement();
        node.setElement(element);
        return old;
    }

    /**
     * If there is an element in the set equals to the specified element,
     * removes it from the set and returns the element;
     * otherwise, returns null.
     *
     * @param element to be removed
     * @return element removed,
     * or null if the set does not an element equals
     */
    @Override
    public E remove(E element) {
        //TODO: Left as an exercise.
        DoublyListNode<E> node = findNode(element);
        if (node == null)
            return null;

        DoublyListNode<E> prev = (DoublyListNode<E>) node.getPrevious();
        DoublyListNode<E> next = (DoublyListNode<E>) node.getNext();
        if (prev == null)
            head = next;
        else
            prev.setNext(next);

        if (next == null)
            tail = prev;
        else
            next.setPrevious(prev);
        currentSize--;
        return node.getElement();
    }
    private void invariant(){
        assert allDif();
    }

    private boolean allDif() {
        
        return true;
    }
}
