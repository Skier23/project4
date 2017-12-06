import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods of DataManager
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-10-18
 */
public class MemoryManagerTest
{
    private MemoryManager manager;
    private int zero;
    private int seven;
    private int fourteen;
    private int twentyfive;
    private int thirtynine;

    /**
     * Sets up a MemoryManager for testing
     * @Before indicates that this is run before all tests
     */
    @Before
    public void setUp()
    {
        manager = new MemoryManager(50);
        zero = manager.insert("fish");
        seven = manager.insert("fish");
        fourteen = manager.insert("big fish");
        twentyfive = manager.insert("little pond");
        thirtynine = manager.insert("i dunno");

    }
    /**
     * Tests the insert method of MemoryManager
     * @Test indicates that this is a test method
     */
    @Test
    public void testInsert()
    {
        assertEquals(zero, 0);
        assertEquals(seven, 7);
        assertEquals(fourteen, 14);
        assertEquals(twentyfive, 25);
        assertEquals(thirtynine, 39);
        
        assertEquals(manager.size(), 49);
        assertEquals(manager.capacity(), 50);
        
        String fail = "";
        
        for (int i = 0; i < 2048; i++)
        {
            fail += "aaaaaaaabbbbbbbbccccccccdddddddd";
        }
        
        fail += "a";
        
        assertEquals(manager.insert(fail), -1);
        assertEquals(manager.insert(""), -1);
        
        assertEquals(manager.size(), 49);
        assertEquals(manager.capacity(), 50);
        
        assertEquals(manager.insert("expand"), 49);
        
        assertEquals(manager.size(), 58);
        assertEquals(manager.capacity(), 100);
    }
    /**
     * Tests the delete method of MemoryManager
     * @Test indicates that this is a test method
     */
    @Test
    public void testDelete()
    {
        assertTrue(manager.delete(zero));
        assertTrue(manager.delete(seven));
        assertTrue(manager.delete(fourteen));
        assertTrue(manager.delete(twentyfive));
        assertTrue(manager.delete(thirtynine));
        
        assertFalse(manager.delete(-1));
        assertFalse(manager.delete(1));
        assertFalse(manager.delete(46));
    }
    
    /**
     * Tests the getLength and getString methods of MemoryManager
     * @Test indicates that this is a test method
     */
    @Test
    public void testGetLengthAndGetString()
    {
        assertEquals(manager.getLength(zero), 4);
        assertEquals(manager.getString(zero), "fish");
        assertEquals(manager.getLength(seven), 4);
        assertEquals(manager.getString(seven), "fish");
        assertEquals(manager.getLength(fourteen), 8);
        assertEquals(manager.getString(fourteen), "big fish");
        assertEquals(manager.getLength(twentyfive), 11);
        assertEquals(manager.getString(twentyfive), "little pond");
        assertEquals(manager.getLength(thirtynine), 7);
        assertEquals(manager.getString(thirtynine), "i dunno");
        
        assertEquals(manager.getLength(46), -1);
        assertNull(manager.getString(46));
        assertEquals(manager.getLength(1), -1);
        assertNull(manager.getString(1));
        assertEquals(manager.getLength(-1), -1);
        assertNull(manager.getString(-1));
    }

}
