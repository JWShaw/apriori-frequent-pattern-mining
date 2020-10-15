import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Scanner;

public class Table {

    /* The rather complicated underlying data structure.  
    Maps each itemset to itself, along with its number of occurrences. */
    private HashMap<HashSet<Integer>, Pair<HashSet<Integer>, Integer>> itemsets;

    private boolean isCTable;
    private double minSupNum;   // Number of occurrences of a "frequent" item

    /* Constructs the first L-table L1 given an input database file.  Should
    only ever be called once for a given dataset. */
    public Table(File f, double minSupPercent) throws FileNotFoundException {
        this.isCTable = false;
        this.itemsets = new HashMap<>();

        Scanner sc = new Scanner(f);
        int dbLines = sc.nextInt();
        this.minSupNum = dbLines * (minSupPercent / 100);

        // Creates and populates the L1 table
        while (sc.hasNextInt()) {
            sc.nextInt();
            int numItems = sc.nextInt();

            /* For each item, hash a 1-element set containing that item with
            the number of times that item has occurred in the database */
            for (int i = 0; i < numItems; i++) {
                HashSet<Integer> singleton = new HashSet<>();
                singleton.add(sc.nextInt());

                if (itemsets.get(singleton) == null) {
                    itemsets.put(singleton, new Pair<>(singleton, 1));
                } else {
                    // Increments the count of the set if it has already been encountered
                    itemsets.get(singleton).second++;
                }
            }
        }
        sc.close();
    }

    /* Used locally by the selfJoin method to build the next C-table from an 
    L-table. */
    // private Table(HashMap<HashSet<Integer>, Integer> underlyingHashMap) {
    //     this.itemsets = underlyingHashMap;
    //     this.isCTable = true;
    // }
    //Queries the database to convert the C-table into an L-table

    public void populateLTable(File f) throws FileNotFoundException {
        this.isCTable = false;
    }

    // Returns a new C-table based on this L-table
    // public Table selfJoin(double minsup) {
    //     Iterator iter1 = itemsets.iterator();
    // }
    
    public boolean isCTable() {
        return isCTable;
    }

    public boolean isLTable() {
        return !isCTable;
    }

    public int size() {
        return itemsets.size();
    }
}