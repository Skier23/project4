
public class HashTable
{
    private Handle[] hashTable;
    private int capacity;
    private int size;
    private static Handle tombstone = new Handle(-1, null);
    
    public HashTable(int size)
    {
        this.size = 0;
        this.capacity = size;
        hashTable = new Handle[this.capacity];
    }
    public void insert(Handle toStore)
    {
        long k = System.currentTimeMillis();
        String toHash = toStore.getString();
        System.out.println(toStore.getString());
        if ((size + 1) > capacity / 2)
        {
            Handle[] tempTable = hashTable;
            hashTable = new Handle[capacity * 2];
            for (int i = 0; i < capacity; i++)
            {
                if (tempTable[i] != null && tempTable[i] != tombstone)
                {
                    insert(tempTable[i]);
                }
            }
        }
        System.out.println("a " + (System.currentTimeMillis() - k));
        int locationToStore = hashFunction(toHash, capacity);
        System.out.println("a.25 " + (System.currentTimeMillis() - k));
        if (hashTable[locationToStore] == null || hashTable[locationToStore] == tombstone)
        {
            System.out.println("a.5 " + (System.currentTimeMillis() - k));
            hashTable[locationToStore] = toStore;
        }
        else
        {
            int offset = 1;
            System.out.println("a.5 " + (System.currentTimeMillis() - k));
            while (hashTable[(locationToStore + (offset * offset)) % capacity] != tombstone &&
                    hashTable[(locationToStore + (offset * offset)) % capacity] != null)
            {
                offset ++;
            }
            hashTable[locationToStore + (offset * offset) % capacity] = toStore;
        }
        System.out.println("b " + (System.currentTimeMillis() - k));
    }  
    public Handle remove(String toRemove)
    {
        int locationToStore = hashFunction(toRemove, capacity);
        if (hashTable[locationToStore].getString().equals(toRemove))
        {
            Handle temp = hashTable[locationToStore];
            hashTable[locationToStore] = tombstone;
            return temp;
        }
        else
        {
            int offset = 1;
            while (!hashTable[(locationToStore + (offset * offset)) % capacity].getString().equals(toRemove) &&
                    hashTable[(locationToStore + (offset * offset)) % capacity] != null)
            {
                offset ++;
            }
            if (hashTable[locationToStore + (offset * offset) % capacity] == null)
            {
                return null;
            }
            Handle temp = hashTable[locationToStore + (offset * offset) % capacity];
            hashTable[locationToStore + (offset * offset) % capacity] = tombstone;
            return temp;
        }
    }
    public Handle find(String toFind)
    {
        int locationToStore = hashFunction(toFind, capacity);
        if (hashTable[locationToStore].getString().equals(toFind))
        {
            Handle temp = hashTable[locationToStore];
            return temp;
        }
        else
        {
            int offset = 1;
            while (!hashTable[(locationToStore + (offset * offset)) % capacity].getString().equals(toFind) &&
                    hashTable[(locationToStore + (offset * offset)) % capacity] != null)
            {
                offset ++;
            }
            if (hashTable[locationToStore + (offset * offset) % capacity] == null)
            {
                return null;
            }
            Handle temp = hashTable[locationToStore + (offset * offset) % capacity];
            return temp;
        }
    }
    
    
    public int hashFunction(String toHash, int size)
    {
        //System.out.println(toHash);
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
        return (int)(Math.abs(sum) % size);
    }
}
