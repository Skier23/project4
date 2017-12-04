
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
        Handle hanA = new Handle(memManager.insert("a"), memManager);
        Handle hanB = new Handle(memManager.insert("b"), memManager);
        Handle hanC = new Handle(memManager.insert("c"), memManager);
        Handle hanD = new Handle(memManager.insert("d"), memManager);
        Handle hanE = new Handle(memManager.insert("e"), memManager);
        Handle hanF = new Handle(memManager.insert("f"), memManager);
        Handle hanG = new Handle(memManager.insert("g"), memManager);
        Handle hanH = new Handle(memManager.insert("h"), memManager);
        Handle hanI = new Handle(memManager.insert("i"), memManager);
        Handle hanJ = new Handle(memManager.insert("j"), memManager);
        Handle hanK = new Handle(memManager.insert("k"), memManager);
        Handle hanL = new Handle(memManager.insert("l"), memManager);
        Handle hanM = new Handle(memManager.insert("m"), memManager);
        Handle hanN = new Handle(memManager.insert("n"), memManager);
        Handle hanO = new Handle(memManager.insert("o"), memManager);
        Handle hanP = new Handle(memManager.insert("p"), memManager);
        Handle hanQ = new Handle(memManager.insert("q"), memManager);
        Handle hanR = new Handle(memManager.insert("r"), memManager);
        Handle hanS = new Handle(memManager.insert("s"), memManager);
        Handle hanT = new Handle(memManager.insert("t"), memManager);
        Handle hanU = new Handle(memManager.insert("u"), memManager);
        Handle hanV = new Handle(memManager.insert("v"), memManager);
        Handle hanW = new Handle(memManager.insert("w"), memManager);
        Handle hanX = new Handle(memManager.insert("x"), memManager);
        Handle hanY = new Handle(memManager.insert("y"), memManager);
        Handle hanZ = new Handle(memManager.insert("z"), memManager);
        
        hash.insert(hanA);
        assertNotNull(hash.find(hanA.getString()));
    }
}
