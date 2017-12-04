
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods of HashTable
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-12-3
 */
public class HashTableTest
{
    private DataManager dataManager;
    private HashTable hash;
    private ByteArrayOutputStream stream;

    /**
     * Initializes the HashTable
     * 
     * @Before - indicates this will be called before the tests
     */
    @Before
    public void setUp()
    {
        dataManager = new DataManager(blocksize, initialHashSize)
        hash = new HashTable(20);

    }

    /**
     * Tests the checkName name method in HashTable.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testInsert()
    {
        
    }

    /**
     * Tests the checkParams method in HashTable.
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
     * Tests the dump method in HashTable.
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
