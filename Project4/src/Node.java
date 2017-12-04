
/**
 * Represents a node in a binary search tree. Node contains a key value, a
 * generic data value, and up to two child nodes
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-09-24
 * 
 * @param <T>
 *            The generic data type stored within the node
 */
public class Node<T>
{

    // ~ Fields
    // .................................................................
    // Generic data held by the node
    private T value;
    // Node's left-hand child node
    private Node<T> left;
    // Node's right-hand child node
    private Node<T> right;

    // ~ Constructor
    // ............................................................
    // ------------------------------------------------------------
    /**
     * Constructs a new Node object with a given key, data to hold, and two
     * sub-nodes
     * 
     * @param k
     *            The key value for the new node
     * @param data
     *            The data contained within the node
     * @param leftLocal
     *            The new node's left hand sub node
     * @param rightLocal
     *            The new node's right hand sub node
     */
    public Node(T data, Node<T> leftLocal, Node<T> rightLocal)
    {
        value = data;
        left = leftLocal;
        right = rightLocal;
    }

    // ------------------------------------------------------------
    /**
     * Constructs a new Node object with a given key and data value. Initializes
     * the sub nodes to null
     * 
     * @param k
     *            The key value for the new node
     * @param data
     *            The data contained within the node
     */
    public Node(T data)
    {
        value = data;
        left = null;
        right = null;
    }

    // ~ Methods
    // ................................................................
    // ------------------------------------------------------------


    // ------------------------------------------------------------
    /**
     * @return the generic data value held by the node
     */
    public T value()
    {
        return value;
    }

    // ------------------------------------------------------------
    /**
     * Changes the data held by the node
     * 
     * @param val
     *            the new generic data to be held by the node
     */
    public void setValue(T val)
    {
        value = val;
    }

    // ------------------------------------------------------------
    /**
     * @return the node's left-hand child node
     */
    public Node<T> left()
    {
        return left;
    }

    // ------------------------------------------------------------
    /**
     * Changes the node's left-hand child node
     * 
     * @param newLeft
     *            the node's new left-hand child node
     */
    public void setLeft(Node<T> newLeft)
    {
        left = newLeft;
    }

    // ------------------------------------------------------------
    /**
     * @return the node's right-hand child node
     */
    public Node<T> right()
    {
        return right;
    }

    // ------------------------------------------------------------
    /**
     * Changes the node's right-hand child node
     * 
     * @param newRight
     *            the node's new right-hand child node
     */
    public void setRight(Node<T> newRight)
    {
        right = newRight;
    }

    // ------------------------------------------------------------
    /**
     * Checks to see if the node is a leaf node.
     * 
     * @return true if the node has no children, false otherwise.
     */
    public boolean isLeaf()
    {
        return (left == null) && (right == null);
    }
}
