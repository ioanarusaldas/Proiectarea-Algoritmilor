import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        double n;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextDouble();
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(double x) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%.4f\n", x);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private double computeSqrt() {

             double end = n+1, start = 0;

             double mid = (end + start)/2;
             double verificare =  Math.abs(mid * mid - n);
             while(verificare > 0.001){
                if(mid*mid > n)
                    end = mid-1;
                else
                    start = mid+1;
                mid = ( end + start ) / 2;
                verificare =  Math.abs(mid*mid - n);
            }
            return mid;

        }

    /* Driver program to test 
    above function*/
   

        

        public void solve() {
            readInput();
            writeOutput(computeSqrt());
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
