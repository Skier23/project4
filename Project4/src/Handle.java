import java.nio.ByteBuffer;
import java.util.Arrays;

public class Handle implements Comparable<Handle>
{
    
    private int handle;
    private DataManager manager;
    
    public Handle(int myHandle, DataManager myData)
    {
        handle = myHandle;
        manager = myData;
    }
    @Override
    public int compareTo(Handle other)
    {
        
        
        return getString().compareTo(other.getString());
        
    }
    
    public int getHandle()
    {
        return handle;
    }
    
    public String getString()
    {
        if (manager == null)
        {
            return null;
        }
        return new String(manager.getRecord(handle));
    }
}
