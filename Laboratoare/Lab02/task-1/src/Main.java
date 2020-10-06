import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Comparator;
import java.util.Arrays;
import java.io.*;


public class Main {
    static class Obj implements Comparable <Obj>{
        public int weight;
        public int price;

        public Obj() {
            weight = 0;
            price = 0;
        }
        public int compareTo (Obj o) {
            double x1,x2;
            x1 = (double)  o.price / o.weight; 
            x2 =  (double) this.price / this.weight;
            if( x1 > x2)
                return 1;
            else if(x1< x2)
                return -1;
            return 0;
        }
    };

    static class Task{
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n, w;
        Obj[] objs;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                w = sc.nextInt();
                objs = new Obj[n];
                for (int i = 0; i < n; i++) {
                    objs[i] = new Obj();
                    objs[i].weight = sc.nextInt();
                    objs[i].price = sc.nextInt();
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(double result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%.4f\n", result);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    







        private double getResult() {
            // TODO: Aflati profitul maxim care se poate obtine cu
            // obiectele date.
            //sort(objs[0],objs[n-1],comparator());
            Arrays.sort(objs);
            double profit = 0;
            int capacitate = w;
            int index = 0;
            double aux;
            while(capacitate > 0){
                if(objs[index].weight <= capacitate){
                    capacitate = capacitate - objs[index].weight;
                    profit = profit + objs[index].price;
                    index++;
                }
                else{
                        aux = (double)capacitate /(double) objs[index].weight * (double)objs[index].price;
                        capacitate = 0;
                        profit = profit + aux;
                        index++;

                }


            }

            

            return profit;
        }

        public void solve() {
            readInput();
            writeOutput(getResult());
        }
    }

    public static void main(String[] args) {
        new Task().solve();

    }
}
