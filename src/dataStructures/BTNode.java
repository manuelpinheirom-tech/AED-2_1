package dataStructures;

import java.io.Serial;
import java.io.Serializable;

/**
 * Binary Tree Node
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 */
@SuppressWarnings("serial")
class BTNode<E> implements InfoNode<E>, Serializable {
    // Element stored in the node.
    private E element;
    // (Pointer to) the father.
    private InfoNode<E> parent;
    // (Pointer to) the left child.
    private InfoNode<E> leftChild;
    // (Pointer to) the right child.
    private InfoNode<E> rightChild;

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     * @param elem
     */
    BTNode(E elem){
        this(elem,null,null,null);
    }

    /**
     * Constructor
     * @param elem
     * @param parent
     */
    BTNode(E elem, BTNode<E> parent) {
        this(elem,parent,null,null);
    }
    /**
     * Constructor
     * @param elem
     * @param parent
     * @param leftChild
     * @param rightChild
     */
    BTNode(E elem, BTNode<E> parent, BTNode<E> leftChild, BTNode<E> rightChild){
        element=elem;
        this.parent=parent;
        this.leftChild=leftChild;
        this.rightChild=rightChild;
    }

    /**
     *  Returns the element of the node
     * @return
     */
    public E getElement() {
        return element;
    }
    /**
     * Returns the left son of node
     * @return
     */
    InfoNode<E> getLeftChild(){
        return leftChild;
    }
    /**
     * Returns the right son of node
     * @return
     */
    InfoNode<E> getRightChild(){
        return rightChild;
    }
    /**
     * Returns the parent of node
     * @return
     */
    InfoNode<E> getParent(){
        return parent;
    }


    /**
     * Update the element
     * @param elem
     */
    public void setElement(E elem) {
        element=elem;
    }

    /**
     * Update the left child
     * @param node
     */
    void setLeftChild(InfoNode<E> node) {
        leftChild=node;
    }

    /**
     * Update the right child
     * @param node
     */
    void setRightChild(InfoNode<E> node) {
        rightChild=node;
    }

    /**
     * Update the parent
     * @param node
     */
    void setParent(InfoNode<E> node) {
        parent=node;
    }


    /**
     * Returns the height of the subtree rooted at this node.
     */

    int getHeight() {
        //TODO: Left as an exercise.
        int leftHeight = 0;
        int rightHeight = 0;

        if (leftChild != null) {
            leftHeight = ((BTNode<E>) leftChild).getHeight();
        }

        if (rightChild != null) {
            rightHeight = ((BTNode<E>) rightChild).getHeight();
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     *
     * @return
     */
    BTNode<E> furtherLeftElement() {
        //TODO: Left as an exercise.
        BTNode<E> node = this;
        while (node.leftChild != null)
            node = (BTNode<E>) node.leftChild;
        return node;
    }

    BTNode<E> furtherRightElement() {
       //TODO: Left as an exercise.
        BTNode<E> node = this;
        while (node.rightChild != null)
            node = (BTNode<E>) node.rightChild;
        return node;
    }
}
