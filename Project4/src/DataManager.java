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
    private PRQT quadTree;

    /**
     * Constructs a new DataManager object. Initializes the binaryTree field.
     */
    public DataManager()
    {
        binaryTree = new BST<String, Point>();
        quadTree = new PRQT();
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
            quadTree.insert(name, point);
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
     * Recursive helper method to find all points in a specified region.
     * Utilizes the java.awt.Rectangle class and its intersects and contains
     * methods to determine if a quadrant intersects the region or a point can
     * be found in the region. Returns the number of nodes visited by the
     * method.
     * 
     * @param rect
     *            The search region in Rectangle form
     * @param root
     *            The node being searched in the current iteration
     * @param x
     *            The x coordinate of the current node's upper-left corner
     * @param y
     *            The x coordinate of the current node's upper-left corner
     * @param size
     *            The size of the current node.
     * @return the number of nodes visited by the method.
     */
    private int recursiveRegionSearch(Rectangle rect, PRNode root, int x, int y,
            int size)
    {

        int visited = 1;

        if (root instanceof PRLeaf)
        {
            PRLeaf leaf = (PRLeaf) root;
            Point p = leaf.getPoint1();
            if (p != null && rect.contains(p))
            {
                Iterator<String> iterator = leaf.getNames1().iterator();
                while (iterator.hasNext())
                {
                    System.out.println(
                            "(" + iterator.next() + ", " + leaf.getPoint1().x
                                    + ", " + leaf.getPoint1().y + ")");
                }
            }
            p = leaf.getPoint2();
            if (p != null && rect.contains(p))
            {
                Iterator<String> iterator = leaf.getNames2().iterator();
                while (iterator.hasNext())
                {
                    System.out.println(
                            "(" + iterator.next() + ", " + leaf.getPoint2().x
                                    + ", " + leaf.getPoint2().y + ")");
                }
            }
            p = leaf.getPoint3();
            if (p != null && rect.contains(p))
            {
                Iterator<String> iterator = leaf.getNames3().iterator();
                while (iterator.hasNext())
                {
                    System.out.println(
                            "(" + iterator.next() + ", " + leaf.getPoint3().x
                                    + ", " + leaf.getPoint3().y + ")");
                }
            }
        }
        else
        {
            PRInternal intern = (PRInternal) root;
            Rectangle nW = new Rectangle(x, y, size / 2, size / 2);
            Rectangle nE = new Rectangle(x + size / 2, y, size / 2, size / 2);
            Rectangle sW = new Rectangle(x, y + size / 2, size / 2, size / 2);
            Rectangle sE = new Rectangle(x + size / 2, y + size / 2, size / 2,
                    size / 2);

            if (rect.intersects(nW))
            {
                visited += recursiveRegionSearch(rect, intern.getNW(), x, y,
                        size / 2);
            }
            if (rect.intersects(nE))
            {
                visited += recursiveRegionSearch(rect, intern.getNE(),
                        x + size / 2, y, size / 2);
            }
            if (rect.intersects(sW))
            {
                visited += recursiveRegionSearch(rect, intern.getSW(), x,
                        y + size / 2, size / 2);
            }
            if (rect.intersects(sE))
            {
                visited += recursiveRegionSearch(rect, intern.getSE(),
                        x + size / 2, y + size / 2, size / 2);
            }

        }
        return visited;
    }

    /**
     * Prints out a list of all Point pairs that intersect with each other
     * Points whose sides abut the given region but don't overlap are not
     * counted by this method.
     */
    public void duplicates()
    {
        System.out.println("Duplicate Points:");
        duplicatesHelper(quadTree.root);

    }

    /**
     * Recursive helper for the duplicates method.
     * 
     * @param root
     *            The node being searched in the current iteration.
     */
    private void duplicatesHelper(PRNode root)
    {
        if (root instanceof PRInternal)
        {
            PRInternal temp = (PRInternal) root;
            if (temp.getNW() != PRQT.empty)
            {
                duplicatesHelper(temp.getNW());
            }
            if (temp.getNE() != PRQT.empty)
            {
                duplicatesHelper(temp.getNE());
            }
            if (temp.getSW() != PRQT.empty)
            {
                duplicatesHelper(temp.getSW());
            }
            if (temp.getSE() != PRQT.empty)
            {
                duplicatesHelper(temp.getSE());
            }
        }
        else
        {
            PRLeaf temp = (PRLeaf) root;
            LinkedList<String> names = temp.getNames1();
            if (names.size() > 1)
            {
                System.out.println("(" + temp.getPoint1().x + ", "
                        + temp.getPoint1().y + ")");

            }

            names = temp.getNames2();
            if (names.size() > 1)
            {
                System.out.println("(" + temp.getPoint2().x + ", "
                        + temp.getPoint2().y + ")");

            }

            names = temp.getNames3();
            if (names.size() > 1)
            {
                System.out.println("(" + temp.getPoint3().x + ", "
                        + temp.getPoint3().y + ")");

            }

        }
    }

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
        quadDump();
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
     * Prints out a list of all nodes and points within the QuadTree
     */
    public void quadDump()
    {
        System.out.println("QuadTree Dump:");

        int visited = quadDumpHelper(quadTree.root, 0, 0, 0, 1024);

        System.out.println(
                "QuadTree Size: " + visited + " QuadTree Nodes Printed.");
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
     * Helper method called by dump() to iterate through the Quadtree
     * recursively This method is where the names and values of the Points are
     * actually printed when calling the dump() command. It also prints out the
     * node structure in the order of NW, NE, SW, and SE.
     * 
     * @param root
     *            The current node we are looking at
     * @param level
     *            The depth of the current node we're looking at
     * @param x
     *            The x coordinate of the top-left corner of the current node
     * @param y
     *            The x coordinate of the top-left corner of the current node
     * @param size
     *            The size of the current node
     * @return the number of nodes visited by the method
     */
    private int quadDumpHelper(PRNode root, int level, int x, int y, int size)
    {
        int visited = 1;
        String spaces = "";
        for (int i = 0; i < level; i++)
        {
            spaces += "  ";
        }
        if (root instanceof PRInternal)
        {
            System.out.println(spaces + "Node at " + x + ", " + y + ", " + size
                    + ": Internal");
            PRInternal temp = (PRInternal) root;

            visited += quadDumpHelper(temp.getNW(), level + 1, x, y, size / 2);

            visited += quadDumpHelper(temp.getNE(), level + 1, x + size / 2, y,
                    size / 2);

            visited += quadDumpHelper(temp.getSW(), level + 1, x, y + size / 2,
                    size / 2);

            visited += quadDumpHelper(temp.getSE(), level + 1, x + size / 2,
                    y + size / 2, size / 2);

        }
        else
        {
            PRLeaf temp = (PRLeaf) root;
            String empty = "";
            if (root == PRQT.empty)
            {
                empty = "Empty";
            }

            System.out.println(spaces + "Node at " + x + ", " + y + ", " + size
                    + ": " + empty);
            Point point1 = temp.getPoint1();
            if (point1 != null)
            {
                Iterator<String> iterator = temp.getNames1().iterator();
                while (iterator.hasNext())
                {
                    System.out.println(spaces + "(" + iterator.next() + ", "
                            + point1.x + ", " + point1.y + ")");

                }
            }
            Point point2 = temp.getPoint2();
            if (point2 != null)
            {
                Iterator<String> iterator = temp.getNames2().iterator();
                while (iterator.hasNext())
                {
                    System.out.println(spaces + "(" + iterator.next() + ", "
                            + point2.x + ", " + point2.y + ")");
                }
            }
            Point point3 = temp.getPoint3();
            if (point3 != null)
            {
                Iterator<String> iterator = temp.getNames3().iterator();
                while (iterator.hasNext())
                {
                    System.out.println(spaces + "(" + iterator.next() + ", "
                            + point3.x + ", " + point3.y + ")");
                }
            }

        }
        return visited;
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
