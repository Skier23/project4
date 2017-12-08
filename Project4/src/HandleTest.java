import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the methods of the Handle class
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-10-18
 */
public class HandleTest
{

    /**
     * Tests all the methods of Handle
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void test()
    {
        assertNull(Handle.SEARCH.getString());
        assertEquals(Handle.SEARCH.getHandle(), -1);
        assertEquals(Handle.SEARCH.toString(), "-1");
        
        MemoryManager manager = new MemoryManager(10);
        
        Handle handle = new Handle(manager.insert("me"), manager);
        
        
        assertEquals(handle.getString(), "me");
        assertEquals(handle.getHandle(), 0);
        assertEquals(handle.toString(), "0");
        
        assertEquals(handle.compareTo(handle), 0);
        assertEquals(handle.compareTo(Handle.SEARCH), 1);
        assertEquals(Handle.SEARCH.compareTo(handle), -1);        
        
    }

}
