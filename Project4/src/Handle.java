
public class Handle implements Comparable<Handle>
{
    
    private int handle;
    private MemoryManager manager;
    public static Handle search = new Handle(-1, null);
    
    public Handle(int myHandle, MemoryManager myData)
    {
        handle = myHandle;
        manager = myData;
    }
    public int getHandle()
    {
        return handle;
    }
    
    public String toString()
    {

        return handle + "";
    }
    
    public String getString()
    {
        if (manager == null)
        {
            return null;
        }
        return manager.getString(handle);
    }
    @Override
    public int compareTo(Handle other)
    {
        
        return handle - other.getHandle();
        
    }
}
