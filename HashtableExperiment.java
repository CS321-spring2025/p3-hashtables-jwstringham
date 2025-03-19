import java.io.*;
import java.util.*;

/**
 * Defines a driver class for HashTable.java allowing the user
 * to pick from three different data sources, choose a load factor,
 * and select different debugging capabilities.
 * 
 * @author James Stringham
 */
public class HashtableExperiment {
    /**
     * Main method with defined usability
     * @param args command line arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 2) 
        {
            System.out.println("Usage: java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]");
            return;
        }

        int dataSource = Integer.parseInt(args[0]);
        double loadFactor = Double.parseDouble(args[1]);
        int debugLevel = (args.length > 2) ? Integer.parseInt(args[2]) : 0;

        int tableSize = TwinPrimeGenerator.generateTwinPrimes(95500, 96000);
        System.out.println("HashtableExperiment: Found a twin prime table capacity: " + tableSize);
        int numElements = (int) Math.ceil(loadFactor * tableSize); 

        LinearProbing linearTable = new LinearProbing(tableSize);
        DoubleHashing doubleTable = new DoubleHashing(tableSize);

        System.out.println("HashtableExperiment: Input: " + getDataSourceName(dataSource) + "   Loadfactor: " + loadFactor);
        System.out.println();

        List<HashObject> objects = generateData(dataSource, numElements);
        System.out.println("Generated " + objects.size() + " elements for insertion.");

        runExperiment("Using Linear Probing", linearTable, numElements, objects, debugLevel, "linear-dump.txt");
        runExperiment("Using Double Hashing", doubleTable,numElements, objects, debugLevel, "double-dump.txt");
    }

    /**
     * Helper method to get the data source from
     * command line argument
     * @param dataSource command line input
     * @return data source string name
     */
    private static String getDataSourceName(int dataSource) {
        return switch (dataSource) 
        {
            case 1 -> "Random Numbers";
            case 2 -> "Date Values";
            case 3 -> "Word-List";
            default -> "Unknown";
        };
    }

    /**
     * Populates array with HashObjects to be processed in the
     * hash table
     * @param dataSource source of data to add to HashObjects array
     * @param numElements amount of data to be added for cases 1 or 2 because it is not a predefined data set
     * @return array of HashObjects
     * @throws IOException
     */
    private static List<HashObject> generateData(int dataSource, int numElements) throws IOException {
        List<HashObject> objects = new ArrayList<>();
        Random rand = new Random();
        long currentTime = new Date().getTime();
    
        switch (dataSource) 
        {
            
            case 1:
                for (int i = 0; i < numElements; i++) 
                {
                    objects.add(new HashObject(Integer.toString(rand.nextInt())));
                }
                break;
            case 2: 
                for (int i = 0; i < numElements; i++) 
                {
                    objects.add(new HashObject(Long.toString(currentTime)));
                    currentTime += 1000; 
                }
                break;
            case 3:
                try (Scanner scanner = new Scanner(new File("word-list.txt"))) 
                {
                    scanner.useDelimiter(System.lineSeparator());
                    
                    while (scanner.hasNext()) 
                    {
                        objects.add(new HashObject(scanner.next()));
                    }
                }
            break;
        }
        return objects;
    }

    /**
     * Runs the experiment for in insertions into the hash table and
     * displays the insertions, duplicates, and average number of probes
     * @param label type of hashing being used
     * @param table hash table to be experimented 
     * @param numElements size of hash table
     * @param objects array of HashObjects to be inserted
     * @param debugLevel debug level
     * @param dumpFileName file name for table to be written to
     * @throws IOException
     */
    private static void runExperiment(String label, Hashtable table, int numElements, List<HashObject> objects, int debugLevel, String dumpFileName) throws IOException {
        System.out.println(label);

        int insertedObjects = 0;
        int tempIdx = 0;

        while (insertedObjects < numElements  && tempIdx < objects.size()) 
        {
            Object key = objects.get(tempIdx).getKey();
            int idx = table.insert(key);

            if (idx != -1) 
            {
                insertedObjects++;
            }
            tempIdx++;
        }

        int insertions = table.getTotalInsertions();
        int duplicates = table.getTotalDuplicates();
        System.out.println("Inserted " + insertions + " elements, of which " + duplicates + " were duplicates");
        System.out.printf("Avg. no. of probes = %.2f%n", table.getAverageProbes());

        if (debugLevel == 1) 
        {
            table.dumpToFile(dumpFileName);
            System.out.println("HashtableExperiment: Saved dump of hash table");
        }
    }
}
