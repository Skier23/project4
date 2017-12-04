import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SongSearchTest
{

    @Before
    public void setUp() throws Exception
    {
        
    }

    @Test
    public void testExecute()
    {
        DataManager data = new DataManager(10, 10);
        
        assertFalse(SongSearch.execute("nothing", data));
        
        assertFalse(SongSearch.execute("print face", data));
        assertTrue(SongSearch.execute("print artist", data));
        assertTrue(SongSearch.execute("print song", data));
        assertTrue(SongSearch.execute("print tree", data));
    }

}
