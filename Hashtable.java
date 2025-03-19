import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Abstract class that defines the general logic of
 * a hash table.
 * 
 * @author James Stringham
 */
public abstract class Hashtable {
    protected HashObject[] table;
    protected int capacity;
    int size = 0;

    /**
     * Defines an empty hash table with a provided
     * capacity 
     * @param capacity size of hash table
     */
    public Hashtable(int capacity) {
        this.capacity = capacity;
        this.table = new HashObject[capacity];
        size = 0;
    }

    /**
     * Abstract method to calculate the next step in the
     * probe sequence. Specific methods are defined in child
     * classes.
     * @param key object key
     * @param attempt number of probes
     * @return next step
     */
    protected abstract int hash(Object key, int attempt);

    /**
     * Main hash function that calculates hashing values 
     * for linear hashing and double probing 
     * @param key object's key
     * @return primary hash value
     */
    protected int h1(Object key) {
        return positiveMod(key.hashCode(), capacity);
    }

    /**
     * Ensures the mod is always a positive number to prevent
     * out of bounds errors
     * @param dividend
     * @param divisor
     * @return positive modulus
     */
    protected int positiveMod(int dividend, int divisor) {
        int mod = dividend % divisor;
        return (mod < 0) ? mod + divisor : mod;
    }

    /**
     * Holds the logic for the hash table's insert
     * operation
     * @param key object's key to insert
     * @return index of inserted object's key
     */
    public int insert(Object key) {
        int i = 0;
        int retIdx = -1;
        while (i < table.length) {
            int q = hash(key, i);
            if (table[q] == null) {
                HashObject hashObj = new HashObject(key);
                hashObj.setProbeCount(i + 1);
                table[q] = hashObj;
                retIdx = q;
                break; 
            } else {
                if (table[q].equals(new HashObject(key))) {
                    table[q].incrementFrequency();
                    break;
                } else {
                    i++;
                }
            }
        }
        i++;
        return retIdx;
    }
    
    /**
     * Prints the entire hash table to a file for 
     * easy access
     * @param fileName file to print hash table to
     * 
     */
    public void dumpToFile(String fileName){
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) 
        {
            for (int i = 0; i < capacity; i++) 
            {
                if (table[i] != null) 
                {
                    out.println("table[" + i + "]: " + table[i].getKey() + " " + table[i].getFrequency() + " " + table[i].getProbeCount());
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error Writing to File");
        }
    }

    /**
     * Checks if the hash table is full
     * @return
     */
    public boolean isFull() {
        return size >= capacity;
    }

    /**
     * Returns hash table capacity
     * @return hash table capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Calculates and returns the total numver of insertions
     * from the hash table
     * @return number of insertions
     */
    public int getTotalInsertions() {
        int insertions = 0;
        for (int i = 0; i < table.length; i++) 
        {
            if (table[i] != null) 
            {
                insertions += table[i].getFrequency();
            }
        }
        return insertions;
    }

    /**
     * Calculates and returns the total numver of duplicates
     * from the hash table
     * @return number of duplicates
     */
    public int getTotalDuplicates() {
        int duplicates = 0;
        for (int i = 0; i < table.length; i++) 
        {
            if (table[i] != null) 
            {
                duplicates += table[i].getFrequency() - 1;
            }
        }
        return duplicates;
    }

    /**
     * Calculates and returns the average numver of probes
     * from the hash table
     * @return average number of probes
     */
    public double getAverageProbes() {
        int probes = 0;
        int occupiedPositions = 0;
        for (int i = 0; i < table.length; i++) 
        {
            if (table[i] != null) 
            {
                probes += table[i].getProbeCount();
                occupiedPositions++;
            }
        }
        return (double) probes / occupiedPositions;
    }
}
