import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Table {

    private HashMap<HashSet<Integer>, Integer> itemsets;
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
        System.out.print(minSupNum);

        while (sc.hasNextInt()) {
            sc.nextInt();
            int items = sc.nextInt();

            /* For each item, hash a 1-element set containing that item with
            the number of times that item has occurred in the database */
            for (int i = 0; i < items; i++) {

                HashSet<Integer> item = new HashSet<>();
                item.add(sc.nextInt());

                if (itemsets.get(item) == null) {
                    itemsets.put(item, 1);
                } else {
                    int count = itemsets.get(item);
                    itemsets.replace(item, ++count);
                }
            }
        }
        sc.close();
    }

    // Queries the database to convert the C-table into an L-table
    public void populateLTable(File f) throws FileNotFoundException {
        this.isCTable = false;
    }

    /* Used locally by the selfJoin method to build the next C-table from an 
    L-table. */
    private Table(HashMap<HashSet<Integer>, Integer> underlyingHashMap) {
        this.itemsets = underlyingHashMap;
        this.isCTable = true;
    }

    // Returns a new C-table based on this L-table
    public Table selfJoin(double minsup) {
        return this;


    }
    
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