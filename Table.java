import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Table {

    private HashMap<HashSet<Integer>, Integer> itemsets;
    private boolean isCTable;

    // Constructs the C1-table given an input database file
    public Table(File f) throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        itemsets = new HashMap<>();

        int dbLines = sc.nextInt();

        while (sc.hasNextLine()) {
            sc.nextInt();
            int items = sc.nextInt();

            for (int i = 0; i < items; i++) {

                HashSet<Integer> item = new HashSet<>(sc.nextInt());

                if (itemsets.get(item) == null) {
                    itemsets.put(item, 0);
                } else {
                    int count = itemsets.get(item);
                    itemsets.replace(item, ++count);
                }
            }
        }
        sc.close();
    }
    
}