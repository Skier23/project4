import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods of DataManager
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-10-18
 */
public class DataManagerTest
{
    private DataManager manager;
    private ByteArrayOutputStream stream;

    /**
     * Initializes the DataManager
     * 
     * @Before - indicates this will be called before the tests
     */
    @Before
    public void setUp()
    {
        manager = new DataManager();

    }

    /**
     * Tests the checkName name method in DataManager.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testCheckName()
    {
        assertFalse(manager.checkName("0name"));
        assertFalse(manager.checkName("_name"));
        assertFalse(manager.checkName("[name]"));
        assertFalse(manager.checkName("(name)"));
        assertFalse(manager.checkName("\"name\""));
        assertTrue(manager.checkName("name"));
    }

    /**
     * Tests the checkParams method in DataManager.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testCheckParams()
    {
        assertFalse(manager.checkParams(-1, 1));
        assertFalse(manager.checkParams(1, -1));
        assertFalse(manager.checkParams(1025, 1));
        assertFalse(manager.checkParams(1, 1025));
        assertFalse(manager.checkParams(1025, 1025));
        assertTrue(manager.checkParams(1, 1));
    }

    /**
     * Tests the insert and remove methods in DataManager.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testInsertRemove()
    {
        manager.insert("0", 1, 2);
        manager.insert("invalid", -1, -2);
        manager.insert("_extra_invalid", -1, -2);
        manager.insert("0", 1, 2);
        manager.insert("valid2", 1, 2);
        manager.insert("valid", 1, 2);
        assertEquals(manager.getSize(), 2);

        manager.remove("0");
        manager.remove("_invalid");
        manager.remove("fish");

        assertNull(manager.remove(-1, 10));
        assertNull(manager.remove(10, -10));
        assertNull(manager.remove(-10, -10));
        assertNull(manager.remove(10, 10));

        assertEquals(manager.getSize(), 2);

        assertEquals(manager.remove("valid"), "valid");

        assertEquals(manager.getSize(), 1);

        assertEquals(manager.remove(1, 2), "valid2");

        assertEquals(manager.getSize(), 0);

        manager.remove("nothing");

        assertEquals(manager.getSize(), 0);
    }

    /**
     * Tests the Search method in DataManager.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testSearch()
    {
        manager.insert("valid", 1, 2);
        manager.insert("valid", 1, 3);
        manager.insert("valid", 1, 4);
        manager.insert("valid", 1, 5);
        manager.insert("valid2", 600, 2);
        manager.insert("valid3", 700, 50);
        manager.insert("valid4", 800, 900);

        assertTrue(manager.search("valid"));
        assertTrue(manager.search("valid2"));
        assertTrue(manager.search("valid3"));
        assertTrue(manager.search("valid4"));
        assertFalse(manager.search("invalid"));
        assertFalse(manager.search("_invalid"));
        assertFalse(manager.search("0invalid"));

    }

    /**
     * Tests the duplicates method in DataManager with an empty tree
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testDuplicatesEmpty()
    {
        stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        manager.duplicates();
        assertEquals(stream.toString(), "Duplicate Points:\n");
    }

    /**
     * Tests the duplicates method in DataManager with an empty tree
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testDuplicates()
    {
        manager.insert("one", 1, 1);
        manager.insert("one", 1, 1);
        manager.insert("one2", 1, 2);
        manager.insert("one2", 1, 2);
        manager.insert("one3", 1, 3);
        manager.insert("one3", 1, 3);

        stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));

        manager.duplicates();

        assertEquals(stream.toString(), "Duplicate Points:\n" + "(1, 1)\n"
                + "(1, 2)\n" + "(1, 3)\n");

        manager.insert("two", 602, 2);
        manager.insert("two2", 602, 4);
        manager.insert("two2", 602, 4);

        stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));

        manager.duplicates();

        assertEquals(stream.toString(), "Duplicate Points:\n" + "(1, 1)\n"
                + "(1, 2)\n" + "(1, 3)\n" + "(602, 4)\n");

        manager.insert("three", 3, 603);
        manager.insert("three", 3, 603);
        manager.insert("three", 30, 603);
        manager.insert("three", 30, 603);
        manager.insert("three", 40, 603);
        manager.insert("three", 40, 603);
        manager.insert("three", 50, 603);
        manager.insert("three", 50, 603);
        manager.duplicates();

        stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));

        manager.duplicates();

        assertEquals(stream.toString(),
                "Duplicate Points:\n" + "(1, 1)\n" + "(1, 2)\n"
                        + "(1, 3)\n" + "(602, 4)\n" + "(3, 603)\n"
                        + "(30, 603)\n" + "(40, 603)\n" + "(50, 603)\n");

        manager.insert("four", 604, 604);
        manager.insert("four", 604, 604);

        stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        manager.duplicates();

        assertEquals(stream.toString(),
                "Duplicate Points:\n" + "(1, 1)\n" + "(1, 2)\n"
                        + "(1, 3)\n" + "(602, 4)\n" + "(3, 603)\n"
                        + "(30, 603)\n" + "(40, 603)\n" + "(50, 603)\n"
                        + "(604, 604)\n");
    }

    /**
     * Tests the regionSearch method
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testRegionSearch()
    {
        stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        
        manager.regionSearch(0, 0, 1024, 1024);
        assertEquals(stream.toString(),
                "Points Intersecting Region: (0, 0, 1024, 1024)\n"
                        + "1 QuadTree Nodes Visited\n");

        manager.insert("one", 1, 1);
        manager.insert("two", 2, 2);
        manager.insert("three", 3, 3);
        manager.insert("four", 104, 404);
        manager.insert("fourr", 104, 404);
        manager.insert("fourrr", 104, 404);
        manager.insert("five", 505, 505);

        manager.insert("six", 606, 106);
        manager.insert("seven", 707, 207);
        manager.insert("eight", 808, 200);
        manager.insert("nine", 909, 409);
        manager.insert("ten", 1010, 510);

        stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));

        manager.regionSearch(0, 0, 512, 512);

        assertEquals(stream.toString(),
                "Points Intersecting Region: (0, 0, 512, 512)\n"
                        + "(one, 1, 1)\n" + "(two, 2, 2)\n"
                        + "(three, 3, 3)\n" + "(four, 104, 404)\n"
                        + "(fourr, 104, 404)\n" + "(fourrr, 104, 404)\n"
                        + "(five, 505, 505)\n"
                        + "6 QuadTree Nodes Visited\n");

        stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));

        manager.regionSearch(512, 0, 512, 512);

        assertEquals(stream.toString(),
                "Points Intersecting Region: (512, 0, 512, 512)\n"
                        + "(six, 606, 106)\n" + "(seven, 707, 207)\n"
                        + "(eight, 808, 200)\n" + "(nine, 909, 409)\n"
                        + "(ten, 1010, 510)\n"
                        + "6 QuadTree Nodes Visited\n");

        stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));

        manager.regionSearch(62, 62, 450, 450);

        assertEquals(stream.toString(),
                "Points Intersecting Region: (62, 62, 450, 450)\n"
                        + "(four, 104, 404)\n" + "(fourr, 104, 404)\n"
                        + "(fourrr, 104, 404)\n" + "(five, 505, 505)\n"
                        + "6 QuadTree Nodes Visited\n");

        stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));

        manager.regionSearch(0, 0, -10, 10);
        manager.regionSearch(0, 0, 10, -10);
        manager.regionSearch(0, 0, -10, -10);

        assertEquals(stream.toString(),
                "Invalid Region: (0, 0, -10, 10)\n"
                        + "Invalid Region: (0, 0, 10, -10)\n"
                        + "Invalid Region: (0, 0, -10, -10)\n");

    }

    /**
     * Tests the dump method in DataManager.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testDump()
    {
        stream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(stream));
        manager.dump();

        assertEquals(stream.toString(),
                "BST dump:\n" + "Node has depth 0, Value (null)\n"
                        + "BST size is: 0\n" + "QuadTree Dump:\n"
                        + "Node at 0, 0, 1024: Empty\n"
                        + "QuadTree Size: 1 QuadTree Nodes Printed.\n");

    }
}
