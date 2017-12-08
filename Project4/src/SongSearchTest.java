import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
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
     * Sets up the tests
     *
     * @throws Exception the exception
     */
    @Before
    public void setUp() throws Exception
    {
        System.out.println("todo");
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
     * Tests the execute method with a variety of inputs.
     *
     * @Test indicates that this is a test method
     */
    @Test
    public void testExecute()
    {
        DataManager data = new DataManager(10, 10);
        
        assertFalse(SongSearch.execute("nothing", data));
        
        assertFalse(SongSearch.execute("insert", data));
        assertFalse(SongSearch.execute("insert fail", data));
        
        assertFalse(SongSearch.execute("insert Journey Faithfully", data));
        assertFalse(SongSearch.execute("insert Heart<SEP>Magic<SEP>Man", data));
        
        assertFalse(SongSearch.execute("insert <SEP>", data));
        assertTrue(SongSearch.execute("insert Journey<SEP>Faithfully", data));
        assertTrue(SongSearch.execute("insert Fleetwood Mac<SEP>Rhiannon",
                data));
        assertTrue(SongSearch.execute("insert Queen<SEP>Killer Queen", data));
        assertTrue(SongSearch.execute("insert Creedence Clearwater Revival<SEP>"
                + "Fortunate Son", data));
        assertTrue(SongSearch.execute("insert Heart<SEP>MagicMan", data));
        
        assertFalse(SongSearch.execute("delete", data));
        assertFalse(SongSearch.execute("delete fail", data));
        assertFalse(SongSearch.execute("delete fail fail", data));
        assertFalse(SongSearch.execute("delete BostonPeace", data));
        
        assertFalse(SongSearch.execute("delete <SEP>", data));
        assertFalse(SongSearch.execute("delete Pink<SEP>Floyd<SEP>Money", 
                data));
        
        assertTrue(SongSearch.execute("delete Journey<SEP>Faithfully", data));
        assertTrue(SongSearch.execute("delete Fleetwood Mac<SEP>Rhiannon",
                data));
        assertTrue(SongSearch.execute("delete Queen<SEP>Killer Queen", data));
        assertTrue(SongSearch.execute("delete Creedence Clearwater Revival<SEP>"
                + "Fortunate Son", data));
        assertTrue(SongSearch.execute("delete Heart<SEP>MagicMan", data));
        
        assertFalse(SongSearch.execute("remove", data));
        assertFalse(SongSearch.execute("remove fail", data));
        assertFalse(SongSearch.execute("remove fail fail", data));
        assertFalse(SongSearch.execute("remove artist", data));
        assertFalse(SongSearch.execute("remove song", data));
        assertTrue(SongSearch.execute("remove artist Bad Company", data));
        assertTrue(SongSearch.execute("remove song Bad Company", data));

        assertFalse(SongSearch.execute("list", data));
        assertFalse(SongSearch.execute("list fail", data));
        assertFalse(SongSearch.execute("list fail fail", data));
        assertFalse(SongSearch.execute("list artist", data));
        assertFalse(SongSearch.execute("list song", data));
        assertTrue(SongSearch.execute("list artist Bad Company", data));
        assertTrue(SongSearch.execute("list song Bad Company", data));
        
        assertFalse(SongSearch.execute("print", data));
        assertFalse(SongSearch.execute("print nothing", data));
        assertTrue(SongSearch.execute("print artist", data));
        assertTrue(SongSearch.execute("print song", data));
        assertTrue(SongSearch.execute("print tree", data));
        
        
    }

}
