public class HashObject {
    private int frequencyCount;
    private int probeCount;
    private Object key;

    public HashObject(Object key) { 
        this.key = key;
        this.frequencyCount = 1;
        this.probeCount = 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof HashObject) {
            return ((HashObject) obj).getKey().equals(key);
        }
        return false;
    }

    public Object getKey() {
        return key;
    }

    public int getFrequency() {
        return frequencyCount;
    }

    public int getProbeCount() {
        return probeCount;
    }

    public void incrementFrequency() {
        frequencyCount++;
    }

    public void setProbeCount(int probeCount)
    {
        this.probeCount = probeCount;
    }

    public String toString() {
        return key.toString() + " " + frequencyCount + " " + probeCount;
    }
}