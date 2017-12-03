import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

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
    private BST<String, Point> binaryTree;

    /**
     * Constructs a new DataManager object. Initializes the binaryTree field.
     */
    public DataManager()
    {
        binaryTree = new BST<String, Point>();
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
    public void insert(String name, int x, int y)
    {

        Point point = new Point(x, y);

        if (!checkParams(x, y) || !checkName(name))
        {
            System.out.println(
                    "Point Rejected: (" + name + ", " + x + ", " + y + ")");
        }
        else
        {
            System.out.println(
                    "Point Inserted: (" + name + ", " + x + ", " + y + ")");
            binaryTree.insert(name, point);
        }
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
        // System.out.println("DataManager: remove name");

        Point p = (Point) binaryTree.remove(name);
        if (!checkName(name) || p == null)
        {
            System.out.println("Point Rejected: " + name);
            return null;
        }

        quadTree.remove(name, p);

        return name;
    }

    /**
     * Removes the first Point found with the specified height, width, and
     * location from the tree. Fails if the tree is empty
     * 
     * @param x
     *            X coordinate of the new Point's upper left corner
     * @param y
     *            y coordinate of the new Point's upper right corner
     * @return the name of the point removed
     */
    public String remove(int x, int y)
    {
        if (checkParams(x, y))
        {
            Point p = new Point(x, y);
            String name = quadTree.remove(null, p);
            if (name != null)
            {
                binaryTree.remove(name, p);
                return name;
            }
        }
        System.out.println("Point Rejected: (" + x + ", " + y + ")");
        return null;
    }

    /**
     * Prints out a list of all Points that intersect with the region with the
     * provided location and size. Fails if the region has 0 or negative
     * dimensions. Points whose sides abut the given region but don't overlap
     * are not counted by this method.
     * 
     * @param x
     *            X coordinate of the search region's upper left corner
     * @param y
     *            y coordinate of the search region's upper right corner
     * @param w
     *            width of the search region
     * @param h
     *            height of the search region
     */
    public void regionSearch(int x, int y, int w, int h)
    {
        if (h <= 0 || w <= 0) // search region can be out of bounds
        {
            System.out.println("Invalid Region: (" + x + ", " + y + ", " + w
                    + ", " + h + ")");
            return;
        }

        System.out.println("Points Intersecting Region: (" + x + ", " + y + ", "
                + w + ", " + h + ")");

        int visited = recursiveRegionSearch(new Rectangle(x, y, w, h),
                quadTree.root, 0, 0, 1024);

        System.out.println(visited + " QuadTree Nodes Visited");
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
        if (!checkName(name))
        {
            System.out.println("Point Not Found: " + name);
            return false;
        }
        
        ArrayList<Point> result = binaryTree.find(name);
        if (result.isEmpty())
        {
            System.out.println("Point Not Found: " + name);
            return false;
        }

        for (int i = 0; i < result.size(); i++)
        {
            System.out.println("Point Found: (" + name + ", "
                    + (int) result.get(i).x + ", "
                    + (int) result.get(i).y + ")");
        }
        return true;
        

    }

    /**
     * Prints out a list of all Points within the BST, followed by a list of
     * the points and nodes in the QuadTree
     * Split to assist in testing
     */
    public void dump()
    {
        binaryDump();

    }
    /**
     * Prints out a list of all Points within the BST
     */
    public void binaryDump()
    {
        System.out.println("BST dump:");
        if (binaryTree.root == null)
        {
            System.out.println("Node has depth 0, Value (null)");
        }
        binaryDumpHelper(binaryTree.root, 0);
        System.out.println("BST size is: " + binaryTree.size());
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
    private void binaryDumpHelper(Node<String, Point> root, int level)
    {
        if (root != null)
        {

            binaryDumpHelper(root.left(), level + 1);

            // Print out the node's data.
            System.out.println(
                    "Node has depth " + level + ", Value (" + root.key() + ", "
                            + root.value().x + ", " + root.value().y + ")");

            binaryDumpHelper(root.right(), level + 1);
        }
    }

 

    /**
     * @return the number of points held in the trees
     */
    public int getSize()
    {
        return binaryTree.size();
    }

    /**
     * Checks to see if a given string is a valid point name. All names must
     * begin with a letter
     * 
     * @param name
     *            The name to check
     * @return true if the name is valid, false otherwise.
     */
    public boolean checkName(String name)
    {
        return Character.isLetter(name.charAt(0));
    }

    /**
     * Checks to ensure a given set of coordinates are valid. The point must fit
     * within a 1024 x 1024 space.
     * 
     * @param x
     *            X coordinate of the search region's upper left corner
     * @param y
     *            y coordinate of the search region's upper right corner
     * @return true if it fits in the space, false otherwise
     */
    public boolean checkParams(int x, int y)
    {
        if (x < 0) // left edge is out of bounds
        {
            return false;
        }
        if (y < 0) // top edge is out of bounds
        {
            return false;
        }
        if (x > 1024) // right edge is out of bounds
        {
            return false;
        }
        return (y <= 1024); // true if bottom edge is in bounds

    }

}
