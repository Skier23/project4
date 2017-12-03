
/**
 * Represents a node in a binary search tree. Node contains a key value, a
 * generic data value, and up to two child nodes
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-09-24
 * 
 * @param <Key> 
 *          The key value used to locate this node
 * @param <T>
 *          The generic data type stored within the node
 */
public class Node<Key, T>
{

    // ~ Fields
    // .................................................................
    // Node's key value
    private Key key;
    // Generic data held by the node
    private T value;
    // Node's left-hand child node
    private Node<Key, T> left;
    // Node's right-hand child node
    private Node<Key, T> right;

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
    public Node(Key k, T data, Node<Key, T> leftLocal, Node<Key, T> rightLocal)
    {
        key = k;
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
    public Node(Key k, T data)
    {
        key = k;
        value = data;
        left = null;
        right = null;
    }

    // ~ Methods
    // ................................................................
    // ------------------------------------------------------------
    /**
     * @return the key value held by the node
     */
    public Key key()
    {
        return key;
    }

    // ------------------------------------------------------------
    /**
     * Changes the node's key value
     * 
     * @param k
     *            The new key value to be held by the node
     */
    public void setKey(Key k)
    {
        key = k;
    }

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
    public Node<Key, T> left()
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
    public void setLeft(Node<Key, T> newLeft)
    {
        left = newLeft;
    }

    // ------------------------------------------------------------
    /**
     * @return the node's right-hand child node
     */
    public Node<Key, T> right()
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
    public void setRight(Node<Key, T> newRight)
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
