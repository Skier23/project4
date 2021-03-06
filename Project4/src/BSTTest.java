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
    private Random random;
    private ArrayList<Handle> handle;
    private ArrayList<KVPair> pair;
    private int duplicates;
    
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

        
        duplicates = 0;
        
        random = new Random();

        for (int i = 0; i < 101; i++)
        {

            int x = 100 + random.nextInt(900);
            
            handle.add(new Handle(manager.insert(x + ""), manager));
        }
        for (int i = 0; i < 50; i++)
        {
            if (random.nextInt(5) == 0)
            {
                pair.add(new KVPair(handle.get(100), handle.get(i + 50)));
                duplicates++;
            }
            else
            {
                pair.add(new KVPair(handle.get(i), handle.get(i + 50)));
            }
        }
        
    }


    /**
     * Tests the insert remove methods in BST.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testInsertRemove()
    {
        assertNull(bst.getRoot());
        bst.insert(pair.get(0)); 
        assertEquals(bst.size(), 1);
        assertNotNull(bst.getRoot());
              
        KVPair removeMe = new KVPair(pair.get(1).getKey(), Handle.SEARCH);
        assertNull(bst.remove(removeMe));
        removeMe = new KVPair(pair.get(0).getKey(), Handle.SEARCH);
        assertNotNull(bst.remove(removeMe));
        

        
        ArrayList<KVPair> removeList = new ArrayList<KVPair>(pair);
        
        while (!pair.isEmpty())
        {
            int i = random.nextInt(pair.size());
            assertTrue(bst.insert(pair.get(i)));
            assertFalse(bst.insert(pair.get(i)));
            pair.remove(i);
        }
        
        assertEquals(bst.size(), removeList.size());
        
        
        while (!removeList.isEmpty())
        {
            int i = random.nextInt(removeList.size());
            removeMe = new KVPair(removeList.get(i).getKey(), Handle.SEARCH);

            assertEquals(removeMe.compareTo(bst.remove(removeMe)), 0);

            removeList.remove(i);
        }
        
        assertNull(bst.remove(removeMe));
        
        assertEquals(bst.size(), 0);
    }
    
    /**
     * Tests the find method in BST.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testFind()
    {
        bst.insert(new KVPair(handle.get(0), handle.get(1))); 
        
        KVPair findMe = new KVPair(handle.get(0), Handle.SEARCH);
        
        assertEquals(bst.find(findMe).size(), 1);
        
        bst.clear();

        while (!pair.isEmpty())
        {
            int i = random.nextInt(pair.size());
            assertTrue(bst.insert(pair.get(i)));
            assertFalse(bst.insert(pair.get(i)));
            pair.remove(i);
        }

        findMe = new KVPair(handle.get(100), Handle.SEARCH);
        
        ArrayList<KVPair> dupes = bst.find(findMe);
        
        assertEquals(dupes.size(), duplicates);
        
        
        for (KVPair thisPair:dupes)
        {
            assertEquals(thisPair.getKey(), handle.get(100));
        }
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