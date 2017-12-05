
/**
 * DataManager creates and maintains a Binary Search Tree in the binaryTree
 * field. DataManager acts as middle-man between the BST data structure and the
 * Point1 front end. Point1 calls functions in DataManager, which in turn call
 * the required functions in the BST.

 * 
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-09-24
 */
public class DataManager
{
    private BST<KVPair> artistTree;
    private BST<KVPair> songTree;
    private MemoryManager database;
    private HashTable table;

    
    /**
     * Constructs a new DataManager object. Initializes the binaryTree field.
     */
    public DataManager(int blocksize, int initialHashSize)
    {
        artistTree = new BST<KVPair>();
        database = new MemoryManager(blocksize);
        table = new HashTable(initialHashSize);
        
    }

    /**
     * Inserts a new Point into the tree. Rejects Points that with 0 or negative
     * dimensions or ones that exceed the bounds of a 1024 x 1024 space. Also
     * rejects names that don't start with a letter of the alphabet.
     * 
     * @param name
     *            Name of the Point to insert
     * @param x
     *            X coordinate of the new Point's upper left corner
     * @param y
     *            y coordinate of the new Point's upper right corner
     */
    public void insert(String artist, String name)
    {
        
        
    }

    /**
     * Removes the first Point found with the specified name from the tree.
     * Fails if the tree is empty
     * 
     * @param name
     *            Name of the Point to remove
     * @return the name of the point removed
     */
    public String remove(String name)
    {
        // TODO Auto-generated method stub
        return "false";

    }

    /**
     * Prints out a list of all Point pairs that intersect with each other
     * Points whose sides abut the given region but don't overlap are not
     * counted by this method.
     */

    /**
     * Searches for a Point found with the specified name from the tree and
     * prints its x and y coordinates, height, and width. Fails if the tree is
     * empty
     * 
     * @param name
     *            Name of the Point to search for
     * @return true if a point was found, false otherwise
     */
    public boolean search(String name)
    {
        // TODO Auto-generated method stub

        return false;
    }

    /**
     * @return the number of points held in the trees
     */
    public int getSize()
    {
        return artistTree.size();
    }



    public void delete(String string, String string2)
    {
        // TODO Auto-generated method stub
        
    }

    public void removeArtist(String string)
    {
        // TODO Auto-generated method stub
        
    }

    public void printArtist()
    {
        // TODO Auto-generated method stub
        
    }

    public void printSong()
    {
        // TODO Auto-generated method stub
        
    }

    /**
     * Prints out a list of all Points within the BST, followed by a list of
     * the points and nodes in the QuadTree
     * Split to assist in testing
     */
    public void printTree()
    {

        System.out.println("Printing artist tree:");

        binaryDumpHelper(artistTree.root, 0);
        
        System.out.println("Printing song tree:");

        binaryDumpHelper(artistTree.root, 0);

    }

    /**
     * Helper method called by dump() to iterate through the BST recursively
     * This method is where the names and values of the Points are actually
     * printed when calling the dump() command
     * 
     * @param root
     *            The current node we are looking at
     * @param level
     *            The depth of the current node we're looking at
     */
    private void binaryDumpHelper(Node<KVPair> root, int level)
    {
        if (root != null)
        {
    
            binaryDumpHelper(root.left(), level + 1);
    
            // Print out the node's data.
            System.out.println(root.value().toString());
    
            binaryDumpHelper(root.right(), level + 1);
        }
    }

    public void listArtist(String string)
    {
        // TODO Auto-generated method stub
        
    }

    public void listSong(String string)
    {
        // TODO Auto-generated method stub
        
    }

    public void removeSong(String string)
    {
        // TODO Auto-generated method stub
        
    }

}
