import java.nio.ByteBuffer;
import java.util.Arrays;

public class MemoryManager
{

    private byte[] data;
    private int size;

    public MemoryManager(int capacity)
    {
        data = new byte[capacity];
    }

    public int insert(String string)
    {
        byte[] stringBytes = string.getBytes();
<<<<<<< HEAD
=======
        byte[] len = ByteBuffer.allocate(2).putInt(stringBytes.length).array();
>>>>>>> branch 'master' of https://github.com/Skier23/project4.git
        
        if (size + stringBytes.length + 3 > data.length)
        {
            expand();
        }
        
        data[size] = 1;
        data[size + 1] = (byte) stringBytes.length;
        data[size + 2] = (byte) (stringBytes.length >>> 2);
        


        for (int i = 0; i < stringBytes.length; i++)
        {
            data[size + 3 + i] = stringBytes[i];
        }

        int handle = size;
        
        size += stringBytes.length + 3;
        
        return handle;
    }

    public boolean delete(int handle)
    {
        if (handle < size - 4)
        {
            data[handle] = 0;
            return true;
        }
        return false;

    }

    public void expand()
    {
        byte[] newData = new byte[data.length * 2];

        for (int i = 0; i < data.length; i++)
        {
            newData[i] = data[i];
        }

        data = newData;
    }

    /**
     * Retrieves the record stored in a specified position in memory
     * 
     * @param handle
     *            The location of the record to retrieve.
     * @return A byte array that corresponds to a record in memory
     */
    public byte[] getRecord(int handle)
    {
        int length = ByteBuffer
                .wrap(Arrays.copyOfRange(data, handle + 1, handle + 2))
                .getInt();
        return Arrays.copyOfRange(data, handle + 3, handle + length);
    }
    
}
