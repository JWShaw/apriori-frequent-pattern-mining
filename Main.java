
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        
        File f = new File(args[0]);
        double minsup = Double.parseDouble(args[1]);

        try {
            Table t = new Table();
            t.generateL1(f, minsup);
            System.out.println(t);
            Table t2 = t.selfJoin();
            System.out.println(t2);
            t2.populateLTable(f);
            System.out.println(t2);
            Table t3 = t2.selfJoin();
            System.out.println(t3);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: file not found.");
            System.exit(0);
        }


    }
}