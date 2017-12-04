
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
        // TODO Auto-generated method stub
        
    }
    
    public int getHandle()
    {
        return localHandle;
    }
    
    public String getString()
    {
        byte[] data = manager.getData();
        
        int length = data[handle + 2]
        
    }
}
