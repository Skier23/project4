import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods of BST
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-10-18
 */
public class BSTTest
{
    private BST<KVPair> bst;
    private MemoryManager manager;
    private Random random;
    private ArrayList<Handle> handle;
    private ArrayList<KVPair> pair;

    /**
     * Initializes the BST and various KVPairs and Handles
     * 
     * @Before - indicates this will be called before the tests
     */
    @Before
    public void setUp()
    {
        bst = new BST<KVPair>();
        MemoryManager manager = new MemoryManager(100);
        handle = new ArrayList<Handle>();
        pair = new ArrayList<KVPair>();
        
        random = new Random();

        for (int i = 0; i < 100; i++)
        {
            int x = 100 + random.nextInt(900);
            handle.add(new Handle(manager.insert(x + ""), manager));
        }
        for (int i = 0; i < 50; i++)
        {
            pair.add(new KVPair(handle.get(i), handle.get(i + 50)));
        }

    }


    /**
     * Tests the insert and remove methods in BST.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testInsertRemove()
    {
        bst.insert(pair.get(0)); 
        assertEquals(bst.size(), 1);
        KVPair removeMe = new KVPair(pair.get(1).getKey(), Handle.search);
        assertNull(bst.remove(removeMe));
        removeMe = new KVPair(pair.get(0).getKey(), Handle.search);
        assertNotNull(bst.remove(removeMe));
        
        
        ArrayList<KVPair> remove = new ArrayList<KVPair>(pair);
        
        while (!pair.isEmpty())
        {
            int i = random.nextInt(pair.size());
            assertTrue(bst.insert(pair.get(i)));
            assertFalse(bst.insert(pair.get(i)));
            pair.remove(i);
        }
        
        assertEquals(bst.size(), remove.size());
        
        while (!remove.isEmpty())
        {
            int i = random.nextInt(remove.size());
            removeMe = new KVPair(remove.get(i).getKey(), Handle.search);
            assertEquals(remove.get(i), bst.remove(removeMe));
            remove.remove(i);
        }
        
        assertNull(bst.remove(removeMe));
        
        assertEquals(bst.size(), 0);
    }

    /**
     * Tests the Clear method in BST.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testClear()
    {
        bst.insert(pair.get(0));
        bst.clear();
        assertNull(bst.remove(pair.get(0)));
    }

    



}