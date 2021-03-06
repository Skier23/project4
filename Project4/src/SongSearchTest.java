import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/**
 * The Class SongSearchTest.
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-12-4
 */
public class SongSearchTest
{

    /**
     * Tests the scanFile method with a sample input file
     *
     * @Test indicates that this is a test method
     */
    @Test
    public void testScanFile()
    {
        File file = new File("not a sample");
        assertEquals(SongSearch.scanFile(file, 100, 100), 0);
        
        file = new File("P4_Input1_Sample.txt");
        assertEquals(SongSearch.scanFile(file, 100, 100), 473);

    }
    
    /**
     * Tests the checkArgs method with a variety of inputs.
     *
     * @Test indicates that this is a test method
     */
    @Test
    public void testCheckArgs()
    {
        assertFalse(SongSearch.checkArgs(new String[] {"5", "5"}));
        assertFalse(SongSearch.checkArgs(new String[] {"0", "5", "test.txt"}));
        assertFalse(SongSearch.checkArgs(new String[] {"5", "0", "test.txt"}));
        assertTrue(SongSearch.checkArgs(new String[] {"5", "5", "test.txt"}));
    }
    
    /**
     * Tests the checkArgs method with a variety of inputs.
     *
     * @Test indicates that this is a test method
     */
    @Test
    public void testMakeScanner()
    {
        assertNull(SongSearch.makeScanner(new File("fail")));

    }
    
    /**
     * Tests the execute method with a variety of invalid inputs.
     *
     * @Test indicates that this is a test method
     */
    @Test
    public void testExecuteFailures()
    {
        DataManager data = new DataManager(10, 10);
        
        assertFalse(SongSearch.execute("nothing", data));
        
        assertFalse(SongSearch.execute("insert", data));
        assertFalse(SongSearch.execute("insert fail", data));
        
        assertFalse(SongSearch.execute("insert Journey Faithfully", data));
        assertFalse(SongSearch.execute("insert Heart<SEP>Magic<SEP>Man", data));
        
        assertFalse(SongSearch.execute("insert <SEP>", data));
        assertFalse(SongSearch.execute("print", data));
        assertFalse(SongSearch.execute("print nothing", data));
        
        assertFalse(SongSearch.execute("delete", data));
        assertFalse(SongSearch.execute("delete fail", data));
        assertFalse(SongSearch.execute("delete fail fail", data));
        assertFalse(SongSearch.execute("delete BostonPeace", data));
        
        assertFalse(SongSearch.execute("delete <SEP>", data));
        assertFalse(SongSearch.execute("delete Pink<SEP>Floyd<SEP>Money", 
                data));
        
        assertFalse(SongSearch.execute("remove", data));
        assertFalse(SongSearch.execute("remove fail", data));
        assertFalse(SongSearch.execute("remove fail fail", data));
        assertFalse(SongSearch.execute("remove artist", data));
        assertFalse(SongSearch.execute("remove song", data));
        
        assertFalse(SongSearch.execute("list", data));
        assertFalse(SongSearch.execute("list fail", data));
        assertFalse(SongSearch.execute("list fail fail", data));
        assertFalse(SongSearch.execute("list artist", data));
        assertFalse(SongSearch.execute("list song", data));
        
        assertFalse(SongSearch.execute("print", data));
        assertFalse(SongSearch.execute("print nothing", data));
    }
    
    /**
     * Tests the execute method with a variety of valid inputs.
     *
     * @Test indicates that this is a test method
     */
    @Test
    public void testExecuteSuccess()
    {
        DataManager data = new DataManager(10, 10);
        

        assertTrue(SongSearch.execute("insert Journey<SEP>Faithfully", data));
        assertTrue(SongSearch.execute("insert Fleetwood Mac<SEP>Rhiannon",
                data));
        assertTrue(SongSearch.execute("insert Queen<SEP>Killer Queen", data));
        assertTrue(SongSearch.execute("insert Creedence Clearwater Revival<SEP>"
                + "Fortunate Son", data));
        assertTrue(SongSearch.execute("insert Heart<SEP>MagicMan", data));
        

        assertTrue(SongSearch.execute("print artist", data));
        assertTrue(SongSearch.execute("print song", data));
        assertTrue(SongSearch.execute("print tree", data));
              
        assertTrue(SongSearch.execute("delete Journey<SEP>Faithfully", data));
        assertTrue(SongSearch.execute("delete Fleetwood Mac<SEP>Rhiannon",
                data));
        assertTrue(SongSearch.execute("delete Queen<SEP>Killer Queen", data));
        assertTrue(SongSearch.execute("delete Creedence Clearwater Revival<SEP>"
                + "Fortunate Son", data));
        assertTrue(SongSearch.execute("delete Heart<SEP>MagicMan", data));
        

        assertTrue(SongSearch.execute("remove artist Bad Company", data));
        assertTrue(SongSearch.execute("remove song Bad Company", data));


        assertTrue(SongSearch.execute("list artist Bad Company", data));
        assertTrue(SongSearch.execute("list song Bad Company", data));
        

        assertTrue(SongSearch.execute("print artist", data));
        assertTrue(SongSearch.execute("print song", data));
        assertTrue(SongSearch.execute("print tree", data));
        
    }

}
