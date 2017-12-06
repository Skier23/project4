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

        if (size + stringBytes.length + 3 > data.length)
        {
            expand();
        }

        byte[] length = ByteBuffer.allocate(2)
                .putShort((short) stringBytes.length).array();

        data[size] = 1;
        data[size + 1] = length[0];
        data[size + 2] = length[1];

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
        if (handle > size - 4)
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
     * Retrieves the string stored in a specified position in memory
     * 
     * @param handle
     *            The location of the string to retrieve.
     * @return The string stored at the specified handle
     */
    public String getString(int handle)
    {
        return new String(Arrays.copyOfRange(data, handle + 3,
                handle + 3 + getLength(handle)));
    }

    /**
     * @return the length of a specified record
     * @param handle
     *            The record whose length we want to find
     * 
     */
    public int getLength(int handle)
    {
        if (handle > size - 4)
        {
            return 0;
        }

        byte[] length = new byte[2];

        length[0] = data[handle + 1];
        length[1] = data[handle + 2];

        return (int) ByteBuffer.wrap(length).getShort();
    }

}
