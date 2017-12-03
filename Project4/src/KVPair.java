/**
 * Stores a pair of linked key and value Handle objects
 * 
 * @author Tyler Bench, ski23
 * @author Christian Dy, k4b0odls
 * @version 2017-12-03
 *
 */
public class KVPair
{
    private Handle key;
    private Handle value;
    
    /**
     * Constructs a new KVPair with specified key and value Handles
     * @param newKey
     *      The key Handle to assign to this KVPair
     * @param newValue
     *      The value Handle to assign to this KVPair
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
}