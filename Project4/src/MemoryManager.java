import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Stores a byte array that holds records of strings. Each record consists of a
 * single byte that indicates whether the record is valid, two bytes that state
 * the length of the data it stores, and up to
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-10-18
 */
public class MemoryManager
{

    private byte[] data;
    private int size;
    private ArrayList<Integer> handles;

    /**
     * Creates a new MemoryManager with a specified capacity
     * 
     * @param capacity
     *            The capacity of the new MemoryManager
     */
    public MemoryManager(int capacity)
    {
        data = new byte[capacity];
        handles = new ArrayList<Integer>();
    }

    /**
     * Inserts a string record to the MemoryManager. Record has a maximum size
     * of 65536.  String cannot be empty.
     * 
     * @param string
     *            The string to insert
     * @return The location the new record was inserted to. Returns -1 if string
     *         is too large
     */
    public int insert(String string)
    {
        if (string.length() > 65536)
        {
            return -1;
        }
        if (string.length() == 0)
        {
            return -1;
        }

        byte[] stringBytes = string.getBytes();

        while (size + stringBytes.length + 3 > data.length)
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

        handles.add(handle);

        return handle;
    }

    /**
     * Flags a record as deleted, but does not remove it from memory. Only
     * accepts valid handles
     * 
     * @param handle
     *            The location of the record to "delete"
     * @return True if the location is valid, false otherwise.
     */
    public boolean delete(int handle)
    {

        for (int i : handles)
        {
            if (i == handle)
            {
                data[handle] = 0;
                return true;
            }
        }

        return false;

    }

    /**
     * Doubles the capacity of the memoryManager
     */
    private void expand()
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
        int length = getLength(handle);
        
        if (length > 0)
        {
            return new String(Arrays.copyOfRange(data, handle + 3,
                handle + 3 + length));
        }
        
        return null;
    }

    /**
     * Retrieves the length of a record at a specified location.
     * @param handle
     *            The record whose length we want to find 
     * @return the length of a specified record if it exists, -1 otherwise.
     */
    public int getLength(int handle)
    {
        if (handle < 0) 
        {
            return -1;
        }
        
        if (handle > size - 4) 
        {
            return -1;
        }

        for (int i : handles)
        {
            if (i == handle)
            {
                byte[] length = new byte[2];
    
                length[0] = data[handle + 1];
                length[1] = data[handle + 2];
    
                return (int) ByteBuffer.wrap(length).getShort();
            }
        }

        return -1;
    }

    /**
     * @return the number of bytes currently stored by the MemoryManager
     */
    public int size()
    {
        return size;
    }

    /**
     * @return the number of bytes the MemoryManager can store
     */
    public int capacity()
    {
        return data.length;
    }
}
