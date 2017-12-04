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
        
        String[] command = {"nothing"};
        
        assertFalse(SongSearch.execute(command, data));
    }

}
