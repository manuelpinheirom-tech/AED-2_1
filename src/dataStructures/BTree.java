package dataStructures;
/**
 * Binary Tree
 * @author AED  Team
 * @version 1.0
 * @param <E> Generic Element
 */
abstract class BTree<E> extends LinkedTree<E> {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    public BTree(){
      super();
    }

    /**
     * Returns the height of the tree.
     */
    public int getHeight() {
        if(isEmpty())
            return -1;
        return ((BTNode<E>)root).getHeight();
    }

    /**
     * Return the further left node of the tree
     * @return
     */
    BTNode<E> furtherLeftElement() {
        //TODO: Left as an exercise.
        BTNode<E> node = (BTNode<E>)root;
        while(node.getLeftChild() != null) {
            node = (BTNode<E>)node.getLeftChild();
        }
        return node;
    }

    /**
     * Return the further right node of the tree
     * @return
     */
    BTNode<E> furtherRightElement() {
        //TODO: Left as an exercise.
        BTNode<E> node = (BTNode<E>)root;
        while(node.getRightChild() != null) {
            node = (BTNode<E>)node.getRightChild();
        }
        return node;
    }

}
