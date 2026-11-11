package dataStructures;

/**
 * Implementation of Singly Linked List
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 *
 */
public class SinglyLinkedList<E> extends SequenceLinkedList<E> {

    public SinglyLinkedList( ) {
        super();
    }

    /**
     * Inserts the specified element at the first position in the list.
     *
     * @param element to be inserted
     */
    @Override
    public void addFirst(E element) {
       //TODO: Left as an exercise.
        SinglyListNode<E> node = new SinglyListNode<>(element, (SinglyListNode<E>) head);
        if (isEmpty())
            tail = node;
        head = node;
        currentSize++;
    }

    /**
     * Inserts the specified element at the last position in the list.
     *
     * @param element to be inserted
     */
    @Override
    public void addLast(E element) {
        //TODO: Left as an exercise.
        SinglyListNode<E> node = new SinglyListNode<>(element, null);
        if (isEmpty())
            head = node;
        else
            tail.setNext(node);
        tail = node;
        currentSize++;
    }

    void addMiddle(int position, E element) {
        //TODO: Left as an exercise.
        SinglyListNode<E> prev = (SinglyListNode<E>) getNode(position - 1);
        SinglyListNode<E> next = (SinglyListNode<E>) prev.getNext();
        prev.setNext(new SinglyListNode<>(element, next));
        currentSize++;
    }
}
