import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int base;
        int exponent;
        int mod;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                base = sc.nextInt();
                exponent = sc.nextInt();
                mod = sc.nextInt();
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(long result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private long fastPow(long base, long exponent, long mod) {
            // TODO: Calculati (base^exponent) modulo mod in O(log exponent).
            if (exponent == 0)
                return 1;
            if (exponent == 1)
                return base % mod;


           // int rezultat = Math.pow(base,exponent/2)%mod;
            //int aux = exponent / 2;
           // if (exponent % 2 == 1)
             //   rezultat = (Math.pow(base,exponent/2)%mod)*(base % mod);
            long aux = exponent / 2;
            long rezultat = fastPow(base,aux,mod);
            long final1 = ((rezultat % mod) * (rezultat % mod )) % mod;
            if (exponent % 2 == 1)
                final1 = ((final1 % mod) * (base % mod)) % mod;
            return final1;
        }

        public void solve() {
            readInput();
            long result = fastPow(base, exponent, mod);
            writeOutput(result);
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}
