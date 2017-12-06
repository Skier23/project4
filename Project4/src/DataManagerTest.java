import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods of DataManager
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-10-18
 */
public class DataManagerTest
{
    private DataManager manager;
    private ByteArrayOutputStream stream;
    private String[] artist;
    private String[] song;

    /**
     * Initializes the DataManager
     * 
     * @Before - indicates this will be called before the tests
     */
    @Before
    public void setUp()
    {
        manager = new DataManager(100, 100);
        artist = new String[100];
        song = new String[100];
        for (int i = 0; i < artist.length; i++)
        {
            artist[i] = String.format("artist%4d", i);
            song[i] = String.format("song6d", i);
        }
        
    }

    /**
     * Tests the Insert method in DataManager.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testInsert()
    {
        manager.insert(artist[0], song[0]);
        
        /*KVPair inserted = manager.insert("tyler", "the cool blues");
        
        assertEquals(inserted.getKey().getString(), "tyler");
        assertEquals(inserted.getValue().getString(), "the cool blues");
        
        KVPair duplicate = manager.insert("tyler", "the cool blues");
        
        System.out.println(inserted.toString());
        System.out.println(duplicate.toString());
        assertNotEquals(inserted, duplicate);*/

    }
    /**
     * Tests the Insert method in DataManager.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testDelete()
    {
        manager.insert("tyler", "the cool blues");
        manager.delete("tyler", "the cool blues");
    }
}
