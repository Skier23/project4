
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
    private MemoryManager memManager;
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
        memManager = new MemoryManager(40);
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
        memManager.insert("a");
        memManager.insert("b");
        memManager.insert("c");
        memManager.insert("d");
        memManager.insert("e");
        memManager.insert("f");
        memManager.insert("g");
        memManager.insert("h");
        memManager.insert("i");
        memManager.insert("j");
        memManager.insert("k");
        memManager.insert("l");
        memManager.insert("m");
        memManager.insert("n");
        memManager.insert("o");
        memManager.insert("p");
        memManager.insert("q");
        memManager.insert("r");
        memManager.insert("s");
        memManager.insert("t");
        
        memManager.insert("u");
        memManager.insert("v");
        memManager.insert("w");
        memManager.insert("x");
        memManager.insert("y");
        memManager.insert("z");
        
        hash.insert(new Handle(, myData));
    }
}
