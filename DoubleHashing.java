/**
 * Class the defines a double hashing implementation
 * for HashTable
 * 
 * @author James Stringham
 */
public class DoubleHashing extends Hashtable {
    /**
     * Calls constructor in Hashtable
     * @param capacity size of hash table
     */
    public DoubleHashing(int capacity) 
    {
        super(capacity);
    }

    @Override
    public int hash(Object key, int probeNumber) 
    {
        return (h1(key) + probeNumber * h2(key)) % capacity;
    }

    /**
     * Calculates secondary hash value
     * @param key Object key
     * @return secondary hash value
     */
    private int h2(Object key) {
        return 1 + positiveMod(key.hashCode(), capacity - 2);
    }
}
