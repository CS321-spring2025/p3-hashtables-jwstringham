public class LinearProbing extends Hashtable {
    public LinearProbing(int capacity) 
    {
        super(capacity);
    }

    @Override
    protected int probe(int key, int attempt) 
    {
        return attempt; 
    }
}
