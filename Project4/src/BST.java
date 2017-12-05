import java.util.ArrayList;

/**
 * BST is a Binary Search Tree data structure holding data in nodes sorted in
 * order by key. Its nodes contain a key value, a generic data type, and
 * references to two child nodes, either of which may have child nodes of their
 * own.
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-09-24
 * 
 * @param <T>
 *            The generic data to be held within a node.
 * 
 *            Implementation of Binary Search Tree from 3114 Textbook with
 *            professor's permission
 * 
 * 
 */
public class BST<T extends Comparable<? super T>>

{
    /**
     * @param root
     *            The first node within the tree
     */
    public Node<T> root;

    /**
     * @param nodecount
     *            The number of nodes in the tree
     */
    private int nodecount;

    /**
     * Constructs a new empty binary search tree
     */
    public BST()
    {
        root = null;
        nodecount = 0;
    }

    /**
     * Reinitializes the tree
     */
    public void clear()
    {
        root = null;
        nodecount = 0;
    }

    /**
     * Insert a record into the tree at the appropriate location. This method
     * calls a recursive helper method and increments the node count. Rejects
     * any record that already exists in the tree
     * 
     * @param k
     *            Key value of the record.
     * @param t
     *            The record to insert.
     * @return true if the record was successfully inserted, false if it already
     *         existed in the tree
     */
    public boolean insert(T t)
    {
        if (findhelp(root, t) != null)
        {
            return false;
        }
        root = inserthelp(root, t, 0);
        nodecount++;
        return true;
    }

    /**
     * Remove a record from the tree. This method checks to ensure calls a
     * recursive helper method and increments the node count.
     * 
     * @param t
     *            the value to remove.
     * @return the value remove
     */
    public T remove(T t)
    {

        T found = findhelp(root, t); // First find it
        if (found != null)
        {

            root = removehelp(root, t, found); // Now remove it
            nodecount--;

        }
        return found;
    }

    public ArrayList<T> removeAll(T t)
    {
        ArrayList<T> list = new ArrayList<>();
        findhelp(root, t, list); // First find it

        for (int i = 0; i < list.size(); i++)
        {
            root = removehelp(root, t, null); // Now remove it
            nodecount--;
        }
        return list;
    }

    /**
     * Returns a list of all values that correspond to a given key. If no such
     * value exists, list is empty.
     * 
     * @param t
     *            An object containing the key to look for
     * @return An ArrayList containing all records that correspond to the key.
     * 
     */
    public ArrayList<T> find(T t)
    {
        ArrayList<T> list = new ArrayList<>();
        findhelp(root, t, list);
        return list;
    }

    /**
     * @return The number of records in the dictionary.
     */
    public int size()
    {
        return nodecount;
    }

    /**
     * Recursive helper method to remove a node with value t
     * 
     * @param t
     *            The value to remove
     * @param rt
     *            The current node to search
     * @return The tree with the node removed
     */
    private Node<T> removehelp(Node<T> rt, T t, T found)
    {

        if (rt.value().compareTo(t) > 0) // k is left of rt
        {
            rt.setLeft(removehelp(rt.left(), t, found));
        }
        else if (rt.value().compareTo(t) < 0) // k is right of rt
        {
            rt.setRight(removehelp(rt.right(), t, found));
        }
        else
        { // Found it
            found = rt.value();

            if (rt.left() == null) // left is empty, go right
            {
                return rt.right();
            }
            else if (rt.right() == null) // left not empty but right is
            {
                return rt.left();
            }
            else
            { // Two children
                Node<T> temp = getmin(rt.right());
                rt.setValue(temp.value());
                rt.setRight(deletemin(rt.right()));
            }

        }
        return rt;
    }

    /**
     * A recursive helper method used to find the first node corresponding to a
     * given key and provide its value.
     * 
     * @param rt
     *            The node to search in each iteration of recursion
     * @param k
     *            The key value to search for
     * @param result
     *            An ArrayList containing the values of all nodes corresponding
     *            to the given key
     */
    private T findhelp(Node<T> rt, T t)
    {
        if (rt == null)
        {
            return null;
        }
        if (rt.value().compareTo(t) > 0)
        {

            return findhelp(rt.left(), t);
        }
        else if (rt.value().compareTo(t) == 0)
        {
            return rt.value();

        }
        else
        {

            return findhelp(rt.right(), t);
        }
    }

    /**
     * A recursive helper method used to find all nodes corresponding to a given
     * key and provide their values.
     * 
     * @param rt
     *            The node to search in each iteration of recursion
     * @param k
     *            The key value to search for
     * @param result
     *            An ArrayList containing the values of all nodes corresponding
     *            to the given key
     */
    private void findhelp(Node<T> rt, T t, ArrayList<T> result)
    {
        if (rt == null)
        {
            return;
        }
        if (rt.value().compareTo(t) > 0)
        {
            if (result.size() >= 1)
            {
                return;
            }
            findhelp(rt.left(), t, result);
        }
        else if (rt.value().compareTo(t) == 0)
        {
            result.add(rt.value());
            findhelp(rt.right(), t, result);
        }
        else
        {
            if (result.size() >= 1)
            {
                return;
            }
            findhelp(rt.right(), t, result);
        }
    }

    /**
     * A recursive helper method used to insert a new node to the tree at its
     * proper location
     * 
     * @param rt
     *            The node current node, used to recursively iterate through the
     *            tree
     * @param k
     *            The new node's key value
     * @param t
     *            The new node's data.
     * @param level
     *            The depth of the current node in the tree.
     * 
     * @return The current subtree, modified to contain the new item
     */
    private Node<T> inserthelp(Node<T> rt, T t, int level)
    {
        if (rt == null)
        {
            //Null node, add here
            return new Node<T>(t);
        }
        if (rt.value().compareTo(t) > 0)
        {
            // System.out.println("turning left");
            rt.setLeft(inserthelp(rt.left(), t, level + 1));
        }
        else
        {
            // System.out.println("turning right");
            rt.setRight(inserthelp(rt.right(), t, level + 1));
        }

        return rt;
  
    }

    /**
     * Recursive method that returns the left-most node in the tree.
     * 
     * @param rt
     * @return the left-most node in the tree
     */
    private Node<T> getmin(Node<T> rt)
    {
        if (rt.left() == null)
        {
            return rt;
        }
        return getmin(rt.left());
    }

    /**
     * Recursive method that removes and returns the left-most node in the tree.
     * 
     * @param rt
     * @return the left-most node in the tree
     */
    private Node<T> deletemin(Node<T> rt)
    {
        if (rt.left() == null)
        {
            return rt.right();
        }
        rt.setLeft(deletemin(rt.left()));
        return rt;
    }

}
