import java.nio.ByteBuffer;
import java.util.Arrays;

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
        if (manager == null)
        {
            return null;
        }
        return new String(manager.getRecord(handle));
    }
    @Override
    public int compareTo(Handle other)
    {
        
        return handle - other.getHandle();
        
    }
}
