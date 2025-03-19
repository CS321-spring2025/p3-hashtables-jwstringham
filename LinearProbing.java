/**
 * Class the defines linear probing implementation
 * for HashTable
 * 
 * @author James Stringham
 */
public class LinearProbing extends Hashtable {
    /**
     * Calls constructor of hash table
     * @param capacity size of hash table
     */
    public LinearProbing(int capacity) 
    {
        super(capacity);
    }

    @Override
    public int hash(Object key, int probeNumber) 
    {
        return (h1(key) + probeNumber) % capacity;
    }
}
