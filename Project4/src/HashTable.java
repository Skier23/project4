
/**
 * The Class HashTable.
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-12-3
 */
public class HashTable
{
    
    /** The hash table. */
    private Handle[] hashTable;
    
    /** The capacity. */
    private int capacity;
    
    /** The size. */
    private int size;
    
    /** Static tombstone variable */
    public static Handle tombstone = new Handle(-1, null);

    /**
     * Instantiates a new hash table.
     *
     * @param size the size of the hashtable
     */
    public HashTable(int size)
    {
        this.size = 0;
        this.capacity = size;
        hashTable = new Handle[this.capacity];
    }

    /**
     * Inserts a new handle into the hash table
     *
     * @param toStore the handle to insert
     */
    public void insert(Handle toStore)
    {
        String toHash = toStore.getString();
        if ((size + 1) > capacity / 2)
        {
            capacity *= 2;
            Handle[] tempTable = hashTable;
            hashTable = new Handle[capacity];
            size = 0;
            for (int i = 0; i < capacity / 2; i++)
            {
                if (tempTable[i] != null && tempTable[i] != tombstone)
                {
                    insert(tempTable[i]);
                }
            }
        }
        int locationToStore = hashFunction(toHash);
        if (hashTable[locationToStore] == null
                || hashTable[locationToStore] == tombstone)
        {
            hashTable[locationToStore] = toStore;
            size++;
        }
        else
        {
            int offset = 1;
            while (hashTable[(locationToStore + (offset * offset))
                    % capacity] != tombstone
                    && hashTable[(locationToStore + (offset * offset))
                            % capacity] != null)
            {
                offset++;
            }
            hashTable[(locationToStore + (offset * offset))
                    % capacity] = toStore;
            size++;
        }
    }

    /**
     * Removes the string from the hashtable if it contains it
     *
     * @param toRemove the string to remove
     * @return the handle if removed. Otherwise null if the hashtable did not
     * contain the string
     */
    public Handle remove(String toRemove)
    {
        int locationToStore = hashFunction(toRemove);
        if (hashTable[locationToStore] == null)
        {
            return null;
        }
        if (hashTable[locationToStore] != tombstone
                && hashTable[locationToStore].getString().equals(toRemove))
        {
            Handle temp = hashTable[locationToStore];
            hashTable[locationToStore] = tombstone;
            size--;
            return temp;
        }
        else
        {
            int offset = 1;
            while (hashTable[(locationToStore + (offset * offset))
                    % capacity] == tombstone
                    || (hashTable[(locationToStore + (offset * offset))
                            % capacity] != null
                            && !hashTable[(locationToStore + (offset * offset))
                                    % capacity].getString().equals(toRemove)))
            {
                offset++;
            }
            if (hashTable[(locationToStore + (offset * offset))
                    % capacity] == null)
            {
                return null;
            }
            Handle temp = hashTable[(locationToStore + (offset * offset))
                    % capacity];
            hashTable[(locationToStore + (offset * offset))
                    % capacity] = tombstone;
            size--;
            return temp;
        }
    }

    /**
     * Finds the string in the hash table
     *
     * @param toFind the string to find
     * @return the handle if it was found. Else returns null
     */
    public Handle find(String toFind)
    {
        int locationToStore = hashFunction(toFind);
        if (hashTable[locationToStore] == null)
        {
            return null;
        }
        if (hashTable[locationToStore] != tombstone
                && hashTable[locationToStore].getString().equals(toFind))
        {
            Handle temp = hashTable[locationToStore];
            return temp;
        }
        else
        {
            int offset = 1;
            while (hashTable[(locationToStore + (offset * offset))
                    % capacity] == tombstone
                    || (hashTable[(locationToStore + (offset * offset))
                            % capacity] != null
                            && !hashTable[(locationToStore + (offset * offset))
                                    % capacity].getString().equals(toFind)))
            {
                offset++;
            }
            if (hashTable[(locationToStore + (offset * offset))
                    % capacity] == null)
            {
                return null;
            }
            Handle temp = hashTable[(locationToStore + (offset * offset))
                    % capacity];
            return temp;
        }
    }

    /**
     * To array.
     *
     * @return the handle[]
     */
    public Handle[] toArray()
    {
        return hashTable.clone();
    }

    /**
     * Hash function.
     *
     * @param toHash the string to hash
     * @return the hash code
     */
    private int hashFunction(String toHash)
    {
        // System.out.println(toHash);
        int intLength = toHash.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++)
        {
            char[] c = toHash.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++)
            {
                sum += c[k] * mult;
                mult *= 256;
            }
        }
        char[] c = toHash.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++)
        {
            sum += c[k] * mult;
            mult *= 256;
        }
        return (int) (Math.abs(sum) % capacity);
    }

}
