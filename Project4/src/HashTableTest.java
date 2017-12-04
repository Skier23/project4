
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
}
