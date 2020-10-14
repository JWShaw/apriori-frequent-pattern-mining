
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        
        File f = new File(args[0]);
        double minsup = Double.parseDouble(args[1]);

        try {
            Table t = new Table(f); 
            System.out.println(t);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: file not found.");
            System.exit(0);
        }
        
        // try {
        //     Scanner sc = new Scanner(f);

        //     while (sc.hasNextInt()) {
        //         System.out.println(sc.nextInt());
        //     }
        // } catch (Exception e) {                
        //     System.out.println("Error!");
        // }
    }
}