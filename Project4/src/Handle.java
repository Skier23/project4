
/**
 * The Class Handle that stores references to data in the byte array.
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-12-3
 */
public class Handle implements Comparable<Handle>
{
    
    /** The handle aka the address in the array. */
    private int handleAddr;
    
    /** The memory manager. */
    private MemoryManager manager;
    
    /** static search field to compare */
    public static final Handle search = new Handle(-1, null);
    
    /**
     * Instantiates a new handle.
     *
     * @param myHandle the address in the array
     * @param myData the memory manager
     */
    public Handle(int myHandle, MemoryManager myData)
    {
        handleAddr = myHandle;
        manager = myData;
    }
    
    /**
     * Gets the handle.
     *
     * @return the handle
     */
    public int getHandle()
    {
        return handleAddr;
    }
    
    /** 
     * gets the handle address as a string
     * 
     * @return the handle address as a string
     */
    public String toString()
    {
        return handleAddr + "";
    }
    
    /**
     * Gets the string the handle points to
     *
     * @return the string
     */
    public String getString()
    {
        if (manager == null)
        {
            return null;
        }
        return manager.getString(handleAddr);
    }
    
    /* 
     * Compares two handles by their location
     * @param the other handle to compare
     * 
     * @return the result
     */
    @Override
    public int compareTo(Handle other)
    {
        
        return handleAddr - other.getHandle();
        
    }
}
