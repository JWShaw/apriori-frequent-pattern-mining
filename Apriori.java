
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Apriori {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws FileNotFoundException {
        
        File f = new File(args[0]);
        double minsup = Double.parseDouble(args[1]);

        PrintWriter outfile = new PrintWriter("MiningResult.txt");

        try {
            // L-tables and count of frequent patterns are stored in memory
            ArrayList<Table> ltables = new ArrayList();
            int frequentPatterns = 0;

            /* First L-table is generated and stored.  Its frequent patterns
            are counted. */
            Table t = new Table();
            t.generateL1(f, minsup);
            ltables.add(t);
            frequentPatterns += t.size();

            /* Subsequent C-tables and L-tables are generated and stored until
            we find an L-table with 1 or 0 frequent patterns */
            boolean finished = false;
            while (!finished) {
                t = t.selfJoin();
                t.populateLTable(f);
                ltables.add(t);
                frequentPatterns += t.size();
                if (t.size() <= 1) {
                    finished = true;
                }
            }

            /* We record the number of frequent patterns to the standard out
            and the file, and we also print each L-table to the file. */
            System.out.println("|FPs| = " + frequentPatterns);
            outfile.println("|FPs| = " + frequentPatterns);
            for (Table table : ltables) {
                outfile.print(table);
            }
        } catch (IOException ioe) {
            System.out.print(ioe);
            System.exit(0);
        } finally {
            outfile.close();
        }
    }
}