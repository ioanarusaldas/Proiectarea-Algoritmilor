import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.*;

public class Main {
    static class Task {
        public final static String INPUT_FILE = "in";
        public final static String OUTPUT_FILE = "out";

        private final static int MOD = 1000000007;

        int n;
        char[] expr;

        private void readInput() {
            try {
                Scanner sc = new Scanner(new File(INPUT_FILE));
                n = sc.nextInt();
                String s = sc.next().trim();
                s = " " + s;
                expr = s.toCharArray();
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
        private long evaluate (long x,long y,char op ) {
            long rezultat = 0;
            int MOD = 1000000007;
            if(op == '*')
                rezultat = ((x % MOD) * (y % MOD)) % MOD;
            if(op == '+')
                rezultat = ((x % MOD) + (y % MOD)) % MOD;
            if(op == '-')
                rezultat = ((x % MOD) - (y % MOD)) % MOD;
            return rezultat;



        }

        private int getResult() {
            // TODO: Calculati numarul de moduri in care se pot aseza
            // parantezele astfel incat rezultatul expresiei sa fie TRUE.
            // Numarul de moduri se va calcula modulo MOD (1000000007).
            ArrayList<Character> operanzi = new ArrayList<Character>();
            ArrayList<Character> operatori = new ArrayList<Character>();
            for(int i = 1; i <= n; i++){
                // System.out.println(i);
                if ((expr[i] == 'T' ) || (expr[i] == 'F'))
                    operanzi.add(expr[i]);
                else 
                    operatori.add(expr[i]);
            }
            System.out.println(operanzi.get(0));
            int n1 = operanzi.size();
            System.out.println(n1);
            long [][] T = new long[n1][n1];
            long [][] F = new long[n1][n1];

            for (int i = 0; i < operanzi.size(); i++) {
                
                if (operanzi.get(i) == 'T') {
                    T[i][i] = 1;
                    F[i][i] = 0;
                } else if (operanzi.get(i) == 'F') {
                    T[i][i] = 0;
                    F[i][i] = 1;
                }
            }
            long aux=0;
            for (int l=1; l<n1; ++l) 
            { 
                for (int i=0, j=l; j<n1; ++i, ++j) 
                { 
                    T[i][j] = F[i][j] = 0; 
                    for (int g=0; g<l; g++) 
                    { 
                        int k = i + g; 
                        long total_i_k = evaluate(T[i][k],F[i][k],'+'); 
                        long total_k_j = evaluate(T[k+1][j] , F[k+1][j],'+'); 
                        long total = evaluate(total_i_k,total_k_j,'*');
                        if (operatori.get(k) == '&') 
                        { 
                            aux = evaluate(T[i][k],T[k+1][j],'*');
                            T[i][j] = evaluate(T[i][j],aux,'+'); 
                            

                            F[i][j] = evaluate( F[i][j],evaluate(total,aux,'-'),'+'); 
                        } 
                        if (operatori.get(k) == '|') 
                        { 
                             aux = evaluate(F[i][k],F[k+1][j],'*');
                            F[i][j] = evaluate(F[i][j],aux,'+'); 
                          
                            T[i][j] =evaluate(  T[i][j],evaluate(total, aux,'-'),'+'); 
                        } 
                        if (operatori.get(k) == '^') 
                        { 
                            aux = evaluate(F[i][k],T[k+1][j],'*');
                            long aux1= evaluate(T[i][k],F[k+1][j],'*');
                            T[i][j] = evaluate( T[i][j],evaluate( aux , aux1,'+'),'+'); 
                            aux = evaluate(T[i][k],T[k+1][j],'*');
                            aux1 = evaluate(F[i][k],F[k+1][j],'*');
                            F[i][j] = evaluate( F[i][j],evaluate(aux,aux1,'+'),'+');
                        } 
                    } 
                } 
            }      
            return (int)T[0][n1-1]; 
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
