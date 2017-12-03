import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the methods of Point1
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-10-18
 */
public class Point1Test
{
    private Point1 point1;
    private ByteArrayOutputStream stream;

    /**
     * Initializes the Point1
     * 
     * @Before - indicates this will be called before the tests
     */
    @Before
    public void setUp()
    {
        point1 = new Point1();

    }
    /**
     * Tests the main method in Point1.
     * 
     * @Test - indicates that this is a test method
     */
    @Test
    public void testMain()
    {
        String[] args = new String[1];
        args[0] = "";
        Point1.main(args);
        args[0] = "P2SyntaxTest1.txt";
        Point1.main(args);
        args = new String[2];
        args[0] = "h";
        args[1] = "j";
        Point1.main(args);
    }
    /* Tests the processCommands method in Point1.
    * 
    * @Test - indicates that this is a test method
    */
   @Test
   public void testProcessCommands()
   {
       String[] args = new String[1];
       args[0] = "P2SyntaxTest1.txt";
       Point1.main(args); 
       args[0] = "P2SyntaxTest2.txt";
       Point1.main(args); 
       args[0] = "SyntaxTest.txt";
       Point1.main(args); 
   }
}