import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    static class Homework implements Comparable <Homework> {
        public int deadline;
        public int score;

        public Homework() {
            deadline = 0;
            score = 0;
        }
        public int compareTo (Homework o) {
            return o.score - this.score;
        }
    }

    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n;
        Homework[] hws;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                hws = new Homework[n];
                for (int i = 0; i < n; i++) {
                    hws[i] = new Homework();
                    hws[i].deadline = sc.nextInt();
                    hws[i].score = sc.nextInt();
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(int result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private int getResult() {
            // TODO: Aflati punctajul maxim pe care il puteti obtine
            // planificand optim temele.
            Arrays.sort(hws);

            int max = 0;
            for(int i = 0 ;i <= hws.length-1; i++)
                if(hws[i].deadline > max)
                    max = hws[i].deadline;


            int aux [] = new int[max];
            int index = 0;
            int k;
            while(index < hws.length){
                k=hws[index].deadline -1;
                for(int j=k;j>=0;j--){
                    if(aux[j] == 0){
                        aux[j] = hws[j].score;
                        break;
                    }
             
                }
                index++;
            }
            int scor = 0;
            for(int j = 0 ;j <= aux.length-1; j++){
                scor = scor + aux[j];
                System.out.println(aux[j]);
            }


            return scor;
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
