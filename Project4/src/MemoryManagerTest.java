import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MemoryManagerTest
{
    MemoryManager manager;
    @Before
    public void setUp() throws Exception
    {
        manager = new MemoryManager(100);
    }

    @Test
    public void test()
    {
        int zero = manager.insert("fish");
        assertEquals(zero, 0);
        
        
        int seven = manager.insert("fish");
        assertEquals(seven, 7);
        
        int fourteen = manager.insert("big fish");
        assertEquals(fourteen, 14);
        
        int twentyfive = manager.insert("little pond");
        assertEquals(twentyfive, 25);
        
        int thirtynine = manager.insert("i dunno");
        assertEquals(thirtynine, 39);
        
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
        
    }

}
