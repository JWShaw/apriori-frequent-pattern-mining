
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        
        File f = new File(args[0]);
        double minsup = Double.parseDouble(args[1]);

        try {
            Table t = new Table();
            t.generateL1(f, minsup);
            System.out.print(t);

            boolean finished = false;
            while (!finished) {
                t = t.selfJoin();
                t.populateLTable(f);
                System.out.print(t);
                if (t.size() <= 1) {
                    finished = true;
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: file not found.");
            System.exit(0);
        }
    }
}