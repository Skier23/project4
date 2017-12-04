import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

    /**
     * Initializes the DataManager
     * 
     * @Before - indicates this will be called before the tests
     */
    @Before
    public void setUp()
    {
        manager = new DataManager(100, 100);

    }

    /**
     * Tests the checkName name method in DataManager.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testSearch()
    {
    }
}
