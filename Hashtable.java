public abstract class Hashtable {
    protected HashObject[] table;
    protected int size;
    protected int capacity;

    public Hashtable(int capacity) 
    {
        this.capacity = capacity;
        this.size = 0;
        this.table = new HashObject[capacity];
    }

    protected int primaryHash(int key) 
    {
        return key % capacity;
    }

    protected abstract int probe(int key, int attempt);

    public void insert(Object key) 
    {
        int hash = primaryHash(key.hashCode());
        int attempt = 0;

        while (table[(hash + probe(key.hashCode(), attempt)) % capacity] != null) 
        {
            attempt++;
        }

        int finalIndex = (hash + probe(key.hashCode(), attempt)) % capacity;

        if (table[finalIndex] != null && table[finalIndex].getKey().equals(key)) 
        {
            table[finalIndex].incrementFrequency();
        } else 
        {
            table[finalIndex] = new HashObject(key);
            size++;
        }
    }

    public HashObject search(Object key) 
    {
        int hash = primaryHash(key.hashCode());
        int attempt = 0;

        while (table[(hash + probe(key.hashCode(), attempt)) % capacity] != null) 
        {
            int currentIndex = (hash + probe(key.hashCode(), attempt)) % capacity;

            if (table[currentIndex].getKey().equals(key)) 
            {
                return table[currentIndex];
            }

            attempt++;
        }
        return null;
    }

    public boolean isFull() 
    {
        return size == capacity;
    }

    public void display() {
        for (int i = 0; i < capacity; i++) 
        {
            if (table[i] != null) 
            {
                System.out.println("Index " + i + ": " + table[i]);
            } else 
            {
                System.out.println("Index " + i + ": Empty");
            }
        }
    }
}

