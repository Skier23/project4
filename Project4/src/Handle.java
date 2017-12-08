
/**
 * The Class Handle that stores references to data in the byte array.
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-12-3
 */
public class Handle implements Comparable<Handle>
{
    
    /** The handle aka the address in the array. */
    private int handle;
    
    /** The memory manager. */
    private MemoryManager manager;
    
    /** static search field to compare */
    public static Handle search = new Handle(-1, null);
    
    /**
     * Instantiates a new handle.
     *
     * @param myHandle the address in the array
     * @param myData the memory manager
     */
    public Handle(int myHandle, MemoryManager myData)
    {
        handle = myHandle;
        manager = myData;
    }
    
    /**
     * Gets the handle.
     *
     * @return the handle
     */
    public int getHandle()
    {
        return handle;
    }
    
    /* 
     * gets the handle address as a string
     * 
     * @return the handle address as a string
     */
    public String toString()
    {
        return handle + "";
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
        return manager.getString(handle);
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
        
        return handle - other.getHandle();
        
    }
}
