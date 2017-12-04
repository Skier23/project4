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

    public void insert(byte[] newRecord)
    {
        if (size + newRecord.length > data.length)
        {
            expand();
        }

        for (int i = 0; i < newRecord.length; i++)
        {
            data[size + i] = newRecord[i];
        }

        size += newRecord.length;
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
