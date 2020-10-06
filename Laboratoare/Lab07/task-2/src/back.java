import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class Main {
	static class Task {
		public static final String INPUT_FILE = "in";
		public static final String OUTPUT_FILE = "out";
		public static final int NMAX = 100005; // 10^5

		int n;
		int m;

		@SuppressWarnings("unchecked")
		ArrayList<Integer> adj[] = new ArrayList[NMAX];

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();

				for (int i = 1; i <= n; i++)
					adj[i] = new ArrayList<>();
				for (int i = 1; i <= m; i++) {
					int x, y;
					x = sc.nextInt();
					y = sc.nextInt();
					adj[x].add(y);
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(ArrayList<Integer> result) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
								OUTPUT_FILE)));
				for (int i = 0; i < result.size(); i++) {
					pw.printf("%d ", result.get(i));
				}
				pw.printf("\n");
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private ArrayList<Integer> getResult() {
			// TODO: Faceti sortarea topologica a grafului stocat cu liste de
			// adiacenta in adj.
			// *******
			// ATENTIE: nodurile sunt indexate de la 1 la n.
			// *******
			ArrayList<Integer> topsort = new ArrayList<>();
            Stack<Integer> stack = new Stack<Integer>();
            Stack<Integer> stack2 = new Stack<Integer>();
            Boolean finalizat [] = new Boolean[n+1];
            Integer nod;
            int index = 0;
            int nr = 0;
            int aux;

            for (int i = 1; i <= n; i++) {
                finalizat[i] = false;
                //if (adj[i].size == 0)

            }
            for (int i = 1; i <= n; i++) {
                 System.out.println("i= " + i);
                if (finalizat[i] == false) {
                    stack2.add(i);
                }
                while(!stack2.empty()){

                    nod = stack2.peek();
                    System.out.println("nod= " + nod);
                    //stack2.push(nod);
                    if(adj[nod].size() != 0) {
                        aux = adj[nod].get(0);
                        System.out.println("copil= " + aux);
                        adj[nod].remove(0);
                        if(finalizat[aux] == false)
                        stack2.add(aux);
                    }
                    else {
                        stack.add(nod);
                        finalizat[nod] = true;
                        stack2.pop();
                    }
                }
            }
            while(!stack.empty()) {
                topsort.add(stack.pop());
            }
			return topsort;
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
