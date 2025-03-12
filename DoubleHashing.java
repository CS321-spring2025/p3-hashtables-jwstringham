public class DoubleHashing extends Hashtable {
    public DoubleHashing(int capacity) 
    {
        super(capacity);
    }

    private int secondaryHash(int key) 
    {
        return 1 + (key % (capacity - 2));
    }

    @Override
    protected int probe(int key, int attempt) 
    {
        return attempt * secondaryHash(key);
    }
}
