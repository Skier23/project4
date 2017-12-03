
public class Handle implements Comparable<Handle>
{
    
    private int localHandle;
    public Handle(int handle)
    {
        localHandle = handle;
    }
    @Override
    public int compareTo(Handle other)
    {
        // TODO Auto-generated method stub
        return localHandle - other.getHandle();
    }
    
    public int getHandle()
    {
        return localHandle;
    }
}
