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
            song[i] = String.format("song%6d", i);
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
        assertEquals(1, manager.listArtist(artist[0]));
        assertEquals(1, manager.listSong(song[0]));
        manager.insert(artist[0], song[0]);
        manager.insert(artist[0], song[1]);
        assertEquals(2, manager.listArtist(artist[0]));
        assertEquals(1, manager.listSong(song[0]));
        manager.insert(artist[1], song[0]);
        assertEquals(2, manager.listArtist(artist[0]));
        assertEquals(2, manager.listSong(song[0]));
    }
    /**
     * Tests the delete and print methods in DataManager.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testDeletePrint()
    {
        manager.insert(artist[0], song[0]);
        manager.delete(artist[1], song[1]);
        manager.delete(artist[0], song[1]);
        manager.delete(artist[1], song[0]);
        assertEquals(1, manager.listArtist(artist[0]));
        assertEquals(1, manager.listSong(song[0]));
        manager.delete(artist[0], song[0]);
        assertEquals(0, manager.listArtist(artist[0]));
        assertEquals(0, manager.listSong(song[0]));
        manager.insert(artist[0], song[0]);
        manager.insert(artist[1], song[0]);
        manager.insert(artist[0], song[1]);
        manager.delete(artist[0], song[0]);
        manager.delete(artist[1], song[0]);
        manager.delete(artist[0], song[1]);
        assertEquals(0, manager.listArtist(artist[0]));
        assertEquals(0, manager.listSong(song[0]));
        manager.insert(artist[0], song[1]);
        manager.insert(artist[1], song[0]);
        manager.delete(artist[0], song[0]);
        assertEquals(1, manager.listArtist(artist[0]));
        assertEquals(1, manager.listSong(song[0]));
        manager.delete(artist[1], song[0]);
        assertEquals(1, manager.printArtist());
        assertEquals(1, manager.printSong());
        manager.insert(artist[1], song[1]);
        assertEquals(4, manager.printTree());
        assertEquals(1, manager.listArtist(artist[1]));
        assertEquals(2, manager.listSong(song[1]));
    }
    /**
     * Tests the removeArtist and removeSong method in DataManager as they are
     *  basically identical
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testRemoveArtistAndSong()
    {
        manager.insert(artist[0], song[0]);
        manager.insert(artist[0], song[1]);
        manager.removeArtist(artist[0]);
        assertEquals(0, manager.listArtist(artist[0]));
        manager.insert(artist[1], song[1]);
        manager.removeArtist(artist[0]);
        assertEquals(0, manager.listArtist(artist[0]));
        manager.insert(artist[1], song[1]);
        manager.insert(artist[0], song[1]);
        manager.removeArtist(artist[0]);
        assertEquals(0, manager.listArtist(artist[0]));
        manager.removeSong(song[1]);
        
        manager.insert(artist[0], song[0]);
        manager.insert(artist[1], song[0]);
        manager.removeSong(song[0]);
        assertEquals(0, manager.listSong(song[1]));
        manager.insert(artist[1], song[1]);
        manager.removeSong(song[0]);
        assertEquals(0, manager.listSong(song[0]));
        manager.insert(artist[0], song[0]);
        manager.insert(artist[1], song[0]);
        manager.removeSong(song[1]);
        assertEquals(0, manager.listSong(song[1]));
    }
}
