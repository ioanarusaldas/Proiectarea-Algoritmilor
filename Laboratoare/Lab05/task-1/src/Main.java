import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        int n, k;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                k = sc.nextInt();
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(ArrayList<ArrayList<Integer>> result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result.size());
                for (ArrayList<Integer> arr : result) {
                    for (int i = 0; i < arr.size(); i++) {
                        pw.printf("%d%c", arr.get(i), i + 1 == arr.size() ?
                                '\n' : ' ');
                    }
                }
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        private boolean check(ArrayList<Integer> solution){
            return true;
        }
        private void back(int step, int stop,ArrayList<Integer> domain,ArrayList<Integer> solution,ArrayList<ArrayList<Integer>> all){
           
            if(step == stop){
                if(check(solution)){
                   // System.out.println("solsize= "+solution.size());
                    ArrayList<Integer> c = new ArrayList <Integer>(solution);
                    all.add(c);
                   
                }
                return;
            }
            for( int i = 0; i < domain.size();i++){
                Integer temp = domain.get(i);
                //System.out.println("temp= "+temp);
                int aux = i;
                solution.add(domain.get(i));
                domain.remove(i);
                back(step + 1 ,stop,domain,solution,all);
                //System.out.println("sizeA= "+all.get(0).size());
                domain.add(aux,temp);
                
                solution.remove(temp);
            

            }
        }
        // private void newSolution(  ArrayList<ArrayList<Integer>> all,){
        //    // all.add(solution);
        // }

        private ArrayList<ArrayList<Integer>> getResult() {
            ArrayList<ArrayList<Integer>> all = new ArrayList<>();

            // TODO: Construiti toate aranjamentele de N luate cate K ale
            // multimii {1, ..., N}.
            ArrayList<Integer> domain = new ArrayList<Integer>();
            ArrayList<Integer> solution = new ArrayList<Integer>();
            for(int i = 0; i < n; i++){
                domain.add(i + 1);
            }
          //  System.out.println("k= "+ k);

            back(0,k,domain,solution,all);

        

            // Pentru a adauga un nou aranjament:
            //   ArrayList<Integer> aranjament;
            //   all.add(aranjament);




            return all;
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
 