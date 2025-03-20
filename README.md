# Project 3: Experiments with Hashing

- **Author**: James Stringham  
- **Class**: CS321 Section 002  
- **Semester**: Spring 2025  

---

## **Overview**  

This project implements a Hashtable class using open addressing and looks at how the load factor changes the average number of probes for linear probing and double hashing implementations. The project inserts n HashObject instances into a hash table of size m until reaching a specific load factor α = n/m.  

---

## **Reflection**  

This project was a challenging but overall rewarding experience. The actual hashing implementation worked well, and the experiments with linear probing and double hashing were interesting to measure their efficiency. Linear probing showed an increase in probe count at higher load factors, while double hashing maintained better performance. The TwinPrimeGenerator, HashObject, Hashtable, LinearProbing, and DoubleHashing classes were reletively straight forward to implement and I did not run into too much trouble. It was visually helpful for me to actually name the primary hash function h1 and the secondary hash function h2, in order to follow the psudocode more closely and achieve the correct implementation. 

I ran into a few major, time consuming challenges when working on this project. The first of which being the HashtableExperiment class. This class really does not have a whole lot to it, but minor changes make a world of difference when trying to analyze the performance of the hash tables and generate the expected results. One aspect I could not figure out for a while was where to store the HashObjects before insterting them into the hash table since they could come from three different sources. I ended up just making a list to store every single HashObject in, then inserting them into the hash table from the list, which works, however I am sure it is terribly inneficient. The other large issue I ran into was correctly counting the insertions, duplicates, and average probes. I originally tried calculating each within my runExperiment() method in HashTableExperiment, however I kept getting incorrect results, which made me think my Hashtable implementation was incorrect and I started making changes to correct code. When I finally realized that the actual HashTable was displaying correctly, I moved the calculations into the HashTable class as helper methods, which was much more straight forward and was easier to do.

I had some slight troubles getting the permissions modified correctly to run the project in AWS, however the docs are very in depth and I was able find solutions. Overall, this project took me way more time than it probably should have, however I was able to get a much better grasp of the key concepts and the final testing was very rewarding when everything worked as expected.

---

## **Compiling and Using**  
Compile the java program files using:
```
javac *.java
```
Run the program using:
```
java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>] 
```
Where
 * dataSource: 1 for random numbers, 2 for dates, 3 for word list
 * loadFactor: The ratio of objects to table size, denoted by α = n/m
 * debugLevel (optional): 0 print summary of experiment, 1 same as 0 
   and dump the two hash tables to files at end, 2 print debugging output 
   for each insert

## Results 

Results from date values data source:

| Load Factor | Elements Inserted | Duplicates | Linear Probing Avg. Probes | Double Hashing Avg. Probes |
|------------------|------------------|------------|---------------------------|---------------------------|
| 0.50 | 47,896 | 0 | 1.47 | 1.47 |
| 0.60 | 57,475 | 0 | 1.61 | 1.59 |
| 0.70 | 67,054 | 0 | 2.05 | 1.81 |
| 0.80 | 76,633 | 0 | 2.75 | 2.11 |
| 0.90 | 86,212 | 0 | 4.92 | 2.72 |
| 0.95 | 91,002 | 0 | 7.72 | 3.33 |
| 0.99 | 94,834 | 0 | 17.93 | 4.91 |

Results from random numbers data source:

| Load Factor | Elements Inserted | Duplicates | Linear Probing Avg. Probes | Double Hashing Avg. Probes |
|------------------|------------------|------------|---------------------------|---------------------------|
| 0.60 | 57,475 | 0 | 1.74 | 1.52 |
| 0.70 | 67,054 | 0 | 2.15 | 1.73 |
| 0.80 | 76,633 | 1 | 3.03 | 2.01 |
| 0.90 | 86,212 | 1 | 5.55 | 2.55 |
| 0.95 | 91,002 | 1 | 9.52 | 3.12 |
| 0.99 | 94,834 | 2 | 36.96 | 4.64 |

Results from word list data source:

| Load Factor | Elements Inserted | Duplicates | Linear Probing Avg. Probes | Double Hashing Avg. Probes |
|------------------|------------------|------------|---------------------------|---------------------------|
| 0.60 | 1,587,659 | 1,530,184 | 2.15 | 1.53 |
| 0.70 | 1,869,206 | 1,802,152 | 3.60 | 1.72 |
| 0.80 | 2,147,748 | 2,071,115 | 6.71 | 2.02 |
| 0.90 | 2,840,050 | 2,753,838 | 19.81 | 2.57 |
| 0.95 | 3,013,622 | 2,922,620 | 110.59 | 3.19 |
| 0.99 | 3,024,134 | 2,929,300 | 471.67 | 4.70 |

## Sources used

* AWS E2C Docs- https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EC2_GetStarted.html
