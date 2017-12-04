import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * BST is a Binary Search Tree data structure holding data in nodes sorted in
 * order by key. Its nodes contain a key value, a generic data type, and
 * references to two child nodes, either of which may have child nodes of their
 * own.
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-09-24
 * @param <Key>
 *            The Key value used to locate a node
 * @param <T>
 *            The generic data to be held within a node.
 * 
 *            Implementation of Binary Search Tree from 3114 Textbook with
 *            professor's permission
 * 
 * 
 */
public class BST<Key extends Comparable<? super Key>, T>
        implements Iterable<Node<Key, T>>
{
    /**
     * @param root
     *            The first node within the tree
     */
    public Node<Key, T> root;

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
     * calls a recursive helper method and increments the node count.
     * 
     * @param k
     *            Key value of the record.
     * @param t
     *            The record to insert.
     */
    public void insert(T t)
    {
        /*
         * if (t == null) { System.out.println("BST insert: t is null"); }
         */
        root = inserthelp(root, t, 0);
        nodecount++;
    }

    /**
     * Remove a record from the tree. This method checks to ensure calls a
     * recursive helper method and increments the node count.
     * 
     * @param k
     *            Key value of record to remove.
     * @return true if a record was removed, false otherwise.
     */
    public T remove(T t)
    {
        ArrayList<T> list = new ArrayList<>();
        findhelp(root, t, list); // First find it
        if (!list.isEmpty())
        {
            root = removehelp(root, t); // Now remove it
            nodecount--;
            return list.get(0);
        }
        return null;
    }

    /**
     * Remove a record from the tree corresponding to the given key containing
     * the given data
     * 
     * @param k
     *            Key value of record to remove.
     * @param data
     *            Key data of the record to remove.
     * @return true if a record was removed, false otherwise.
     */
    public boolean remove(T data)
    {
        ArrayList<T> list = new ArrayList<>();
        findhelp(root, data, list); // First find it
        if (!list.isEmpty())
        {
            root = removehelp(root, data); // Now remove it
            nodecount--;
        }
        return !list.isEmpty();
    }

    /**
     * @return Record with key value k, null if none exist.
     * @param k
     *            The key value to find.
     */
    public ArrayList<T> find(Key k)
    {
        ArrayList<T> list = new ArrayList<>();
        findhelp(root, k, list);
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
     * Recursive helper method to remove a node with key value k
     * 
     * @return The tree with the node removed
     */
    private Node<Key, T> removehelp(Node<T> rt)
    {
        if (rt == null)
        {
            return null;
        }
        if (rt.key().compareTo(k) > 0) // k is left of rt
        {
            rt.setLeft(removehelp(rt.left(), k));
        }
        else if (rt.key().compareTo(k) < 0) // k is right of rt
        {
            rt.setRight(removehelp(rt.right(), k));
        }
        else
        { // Found it
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
                Node<Key, T> temp = getmin(rt.right());
                rt.setValue(temp.value());
                rt.setKey(temp.key());
                rt.setRight(deletemin(rt.right()));
            }
        }
        return rt;
    }

    /**
     * Recursive helper method to remove a node with key value k containing a
     * given data value
     * 
     * @param k
     *            the desired key value
     * @param data
     *            the desired data value
     * 
     * @return The tree with the node removed
     */
    private Node<Key, T> removehelp(Node<Key, T> rt, Key k, T data)
    {
        if (rt == null)
        {
            return null;
        }
        if (rt.key().compareTo(k) > 0) // k is left of rt
        {
            rt.setLeft(removehelp(rt.left(), k, data));
        }
        else if (rt.key().compareTo(k) < 0) // k is right of rt
        {
            rt.setRight(removehelp(rt.right(), k, data));
        }
        else // key values are equal
        {
            if (rt.value().equals(data)) // data values are equal, we found it!
            {

                if (rt.left() == null)
                {
                    // left is empty, so replace this node with right
                    return rt.right();
                }
                else if (rt.right() == null)
                {
                    // right is empty and left isn't so replace this node with
                    // left
                    return rt.left();
                }
                else
                { // Two children
                    Node<Key, T> temp = getmin(rt.right());
                    rt.setValue(temp.value());
                    rt.setKey(temp.key());
                    rt.setRight(deletemin(rt.right()));
                }
            }
            else // duplicates keys always place on the right, so check there
            {
                rt.setRight(removehelp(rt.right(), k, data));
            }
        }

        return rt;
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
    private void findhelp(Node<Key, T> rt, Key k, ArrayList<T> result)
    {
        if (rt == null)
        {
            return;
        }
        if (rt.key().compareTo(k) > 0)
        {
            if (result.size() >= 1)
            {
                return;
            }
            findhelp(rt.left(), k, result);
        }
        else if (rt.key().compareTo(k) == 0)
        {
            result.add(rt.value());
            findhelp(rt.right(), k, result);
        }
        else
        {
            if (result.size() >= 1)
            {
                return;
            }
            findhelp(rt.right(), k, result);
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
    private Node<Key, T> inserthelp(Node<Key, T> rt, Key k, T t, int level)
    {
        if (rt == null)
        {
            /*
             * System.out.println(
             * "BST inserthelp: this node is null, adding node at level " +
             * level);
             */
            return new Node<Key, T>(k, t);
        }
        if (rt.key().compareTo(k) > 0)
        {
            // System.out.println("turning left");
            rt.setLeft(inserthelp(rt.left(), k, t, level + 1));
        }
        else if (rt.key().compareTo(k) < 0)
        {
            // System.out.println("turning right");
            rt.setRight(inserthelp(rt.right(), k, t, level + 1));
        }
        else // duplicate node, adding new node to its right
        {
            // System.out.println("duplicate, turning right");
            // The right node is already occupied
            if (rt.right() != null)
            {
                Node<Key, T> temp = new Node<Key, T>(k, t, null, rt.right());
                rt.setRight(temp);
                /*
                 * System.out.println(
                 * "BST inserthelp: Duplicate node already has node at " +
                 * "right, adding node at level " + level + 1);
                 */
                return rt;
            }
            rt.setRight(inserthelp(rt.right(), k, t, level + 1));
        }
        return rt;
    }

    /**
     * Recursive method that returns the left-most node in the tree.
     * 
     * @param rt
     * @return the left-most node in the tree
     */
    private Node<Key, T> getmin(Node<Key, T> rt)
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
    private Node<Key, T> deletemin(Node<Key, T> rt)
    {
        if (rt.left() == null)
        {
            return rt.right();
        }
        rt.setLeft(deletemin(rt.left()));
        return rt;
    }

    /**
     * Returns the iterator for the BST tree
     * 
     * @return a new Node iterator for this tree
     */
    @Override
    public Iterator<Node<Key, T>> iterator()
    {
        return new PreBSTIterator(root);
    }

    /**
     * Iterates over the nodes in the BST
     * 
     */
    private class PreBSTIterator implements Iterator<Node<Key, T>>
    {
        // The current node the iterator is on
        private Node<Key, T> thisNode;
        // The stack of nodes representing the path to the currentNode
        private Stack<Node<Key, T>> treeStack;

        /**
         * Creates an iterator for the binary search tree with the given root
         * 
         * @param root
         *            the root of the BST
         */
        public PreBSTIterator(Node<Key, T> root)
        {
            thisNode = root;
            treeStack = new Stack<>();
        }

        /**
         * Recursive method that removes and returns the left-most node in the
         * tree.
         * 
         * @return the next entry in the iterator if such entry exists
         * @throws NoSuchElementException
         *             if there is no next element. Avoid by using the hasNext()
         *             method before this method.
         */
        @Override
        public Node<Key, T> next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            Node<Key, T> currentNode = thisNode;
            advance();
            return currentNode;
        }
        
        /**
         * Checks if there is a next Node in the iterator
         * 
         * @return true if there is indeed another Node in the iterator.
         */
        @Override
        public boolean hasNext()
        {
            return thisNode != null;
        }

        /**
         * Advances the iterator to the next node
         * 
         * @return true if successfully advanced the iterator
         */
        private boolean advance()
        {

            boolean result = true;

            if (thisNode.left() != null)
            {
                if (thisNode.right() != null)
                {
                    treeStack.push(thisNode.right());
                }
                thisNode = thisNode.left();
            }
            else if (thisNode.right() != null)
            {
                thisNode = thisNode.right();
            }
            else if (treeStack.size() == 0)
            {
                thisNode = null;
                result = false;
            }
            else
            {
                thisNode = treeStack.pop();
            }

            return result;
        }
    }

}
