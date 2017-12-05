
import static org.junit.Assert.*;

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
     * Tests the insert name method in HashTable.
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
        Handle hanAI = new Handle(memManager.insert("ai"), memManager);
        
        hash.insert(hanA);
        assertEquals(hash.find(hanA.getString()), hanA);
        hash.insert(hanB);
        assertEquals(hash.find(hanB.getString()), hanB);
        hash.insert(hanC);
        assertEquals(hash.find(hanC.getString()), hanC);
        hash.insert(hanD);
        assertEquals(hash.find(hanD.getString()), hanD);
        hash.insert(hanE);
        assertEquals(hash.find(hanE.getString()), hanE);
        hash.insert(hanF);
        assertEquals(hash.find(hanF.getString()), hanF);
        hash.insert(hanG);
        assertEquals(hash.find(hanG.getString()), hanG);
        hash.insert(hanH);
        assertEquals(hash.find(hanH.getString()), hanH);
        hash.insert(hanI);
        assertEquals(hash.find(hanI.getString()), hanI);
        hash.remove("i");
        hash.insert(hanI);
        hash.insert(hanJ);
        assertEquals(hash.find(hanJ.getString()), hanJ);
        hash.remove("j");
        hash.insert(hanAI);
        hash.remove("ai");
        hash.insert(hanK);
        hash.insert(hanL);
        //hash.insert(hanAI);
        hash.insert(hanJ);
        //hash.remove(hanJ.getString());
        //assertNull(hash.find(hanJ.getString()));
        hash.insert(hanK);
        assertEquals(hash.find(hanK.getString()), hanK);
        hash.remove("a");
        //System.out.println(hash.find("a").getString());
        assertNull(hash.find("a"));
        hash.insert(hanM);
        hash.insert(hanN);
        assertEquals(hash.find(hanN.getString()), hanN);
        
    }
    /**
     * Tests the remove name method in HashTable.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testRemove()
    {
        Handle hanJ = new Handle(memManager.insert("j"), memManager);
        Handle hanAq = new Handle(memManager.insert("aq"), memManager);
        assertNull(hash.remove("a"));
        hash.insert(hanJ);
        assertEquals(hanJ, hash.remove("j"));
        assertNull(hash.remove("j"));
        hash.insert(hanAq);
        assertNull(hash.remove("i"));
        assertNull(hash.remove("abcdefg"));
    }
    /**
     * Tests the find name method in HashTable.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testFind()
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
       // Handle hanJ = new Handle(memManager.insert("j"), memManager);
        Handle hanAq = new Handle(memManager.insert("aq"), memManager);
        assertNull(hash.find("a"));
        hash.insert(hanJ);
        assertEquals(hanJ, hash.find("j"));
        //hash.remove("j");
        hash.insert(hanA);
        hash.insert(hanB);
        hash.insert(hanC);
        hash.insert(hanD);
        hash.insert(hanE);
        hash.insert(hanF);
        hash.insert(hanG);
        hash.insert(hanH);
        hash.insert(hanI);
       
        hash.find("aq");
        hash.remove("j");
        hash.find("aq");
        hash.remove("e");
        hash.remove("f");
        hash.insert(hanJ);
        hash.insert(hanAq);
        hash.find("aq");
    }
}
