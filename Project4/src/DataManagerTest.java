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
        manager = new DataManager(1);

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
