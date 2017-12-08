/**
 * Stores a pair of linked key and value Handle objects
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-12-03
 *
 */
public class KVPair implements Comparable<KVPair>
{
    private Handle key;
    private Handle value;

    /**
     * Constructs a new KVPair with specified key and value Handles
     * 
     * @param newKey
     *            The key Handle to assign to this KVPair
     * @param newValue
     *            The value Handle to assign to this KVPair
     */
    public KVPair(Handle newKey, Handle newValue)
    {
        key = newKey;
        value = newValue;
    }

    /**
     * @return the key Handle
     */
    public Handle getKey()
    {
        return key;
    }

    /**
     * @return the value Handle
     */
    public Handle getValue()
    {
        return value;
    }

    /**
     * gets the kvpair as a string
     * 
     * @return the kvpair represented as a string of the two handles
     */
    public String toString()
    {
        return "(" + key + "," + value + ")";
    }
    
    /**
     * gets the kvpair as a string of the handle's strings
     * 
     * @return the kvpair represented as a string of the two handles' strings
     */
    public String getString()
    {
        return "(|" + key.getString() + "|,|" + value.getString() + "|)";
    }
    
    /* 
     * compares two KVPairs by their key values with value as a tie breaker
     * 
     * @param the other KVPair to compare
     * @return the result
     */
    @Override
    public int compareTo(KVPair other)
    {
        
        
        int keyCompare = key.compareTo(other.getKey());
        if (value == Handle.search)
        {
            return keyCompare;
        }
        if (other.getValue() == Handle.search)
        {
            return keyCompare;
        }
        
        if ((keyCompare != 0))
        {
            return keyCompare;
        }

        
        return value.compareTo(other.getValue());
    }

}
