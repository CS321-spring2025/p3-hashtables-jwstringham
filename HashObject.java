public class HashObject {
    private int frequencyCount;
    private int probeCount;
    private Object key;

    public HashObject(Object key)
    {
        this.key = key;
        this.frequencyCount = 1;
        this.probeCount = 0;
    }

    public boolean equals(Object key)
    {
        if(this.key.equals(key))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Object getKey() 
    {
        return key;
    }

    public void incrementFrequency() 
    {
        frequencyCount++;
    }

    public void incrementProbeCount() 
    {
        probeCount++;
    }

    public String toString() 
    {
        return "Key: " + key + ", Frequency: " + frequencyCount + ", Probe Count: " + probeCount;
    }
}
