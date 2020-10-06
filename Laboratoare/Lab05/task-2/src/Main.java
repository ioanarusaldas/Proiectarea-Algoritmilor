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

        int n;
        int stop;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                stop = n;
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
        // private boolean check(ArrayList<Integer> solution){
        //     return true;
        // }
        // private void back(int step, int stop,ArrayList<Integer> domain,ArrayList<Integer> solution,ArrayList<ArrayList<Integer>> all){
        //     if(step == domain.size()){
        //         //ArrayList<Integer> c = new ArrayList <Integer>(solution);
        //             //Collections.copy(c,solution);
        //           //  all.add(c);
        //             return;
        //     }
        //     solution.add(step,domain.get(step));
        //     ArrayList<Integer> c = new ArrayList <Integer>(solution);
        //             Collections.copy(c,solution);
        //             all.add(c);

        //     back(step+1,stop,domain,solution,all);
        //     solution.add(step,domain.get(step));
        //     solution.add(step,-1);
        //     back(step+1,stop,domain,solution,all);
          
    
            
            
        // }
        private boolean check(int step,ArrayList<Integer> solution){
            if(step == 0)
            {
             return true;
            }
            if(solution.get(step) > solution.get(step-1))
                return true;
            return false;
        }
        private void back(int step,ArrayList<Integer> domain,ArrayList<Integer> solution,ArrayList<ArrayList<Integer>> all){
            for(int i=0;i<domain.size();i++)
            {
                solution.add(step,i+1);
                if(check(step,solution))
                {
                    
                    
                    ArrayList<Integer> c = new ArrayList <Integer>();
                    //Collections.copy(c,solution);
                    for(int j =0 ;j<=step; j++)
                        c.add(solution.get(j));
                    all.add(c);
                    //afis(k);
                    back(step+1,domain,solution,all);
                }
            }
        }

        private ArrayList<ArrayList<Integer>> getResult() {
            ArrayList<ArrayList<Integer>> all = new ArrayList<>();

            // TODO: Construiti toate submultimele multimii {1, ..., N}.

            // Pentru a adauga o noua submultime:
            //   ArrayList<Integer> submultime;
            //   all.add(submultime);
            ArrayList<Integer> domain = new ArrayList<Integer>();
            ArrayList<Integer> solution = new ArrayList<Integer>();
           // for(int j = 1; j <= n ;j++){
                for(int i = 0; i < n; i++){
                    domain.add(i + 1);
                }
               // for(int i= n ; i>0 ;i--)
                    back(0,domain,solution,all);
                    ArrayList<Integer> vida = new ArrayList<Integer>();
                    all.add(vida);
           // }

             for(int j = 0; j < all.size() ;j++){
                for(int i = 0; i < all.get(j).size(); i++){
                        System.out.print(all.get(j).get(i)+" ");
                }
                System.out.println();
            }

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
