import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
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

		int source;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				source = sc.nextInt();

				for (int i = 1; i <= n; i++)
					adj[i] = new ArrayList<>();
				for (int i = 1; i <= m; i++) {
					int x, y;
					x = sc.nextInt();
					y = sc.nextInt();
					adj[x].add(y);
					adj[y].add(x);
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int[] result) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
								OUTPUT_FILE)));
				for (int i = 1; i <= n; i++) {
					pw.printf("%d%c", result[i], i == n ? '\n' : ' ');
				}
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int[] getResult() {
            // TODO: Faceti un BFS care sa construiasca in d valorile d[i] = numarul
            // minim de muchii de parcurs de la nodul source la nodul i.
            // d[source] = 0
            // d[x] = -1 daca nu exista drum de la source la x.
            // *******
            // ATENTIE: nodurile sunt indexate de la 1 la n.
            // *******
			int d[] = new int[n + 1];
            boolean visited[] = new boolean[n + 1];
            Queue<Integer> queue = new LinkedList<>(); 
            Integer top;
            int aux;
            //initializare vector distante si vizitat
			for (int i = 0; i <= n; i++) {
                visited[i] = false;
                 d[i] = -1;
            }
            //adaugare sursa in coada
            queue.add(source);
            d[source] = 0;
            visited[source] = true;
            //parcurgere coada
            while(queue.size()!= 0) {
                top = queue.remove();
                //parcurgere muchii nod
                for(int i = 0; i < adj[top].size(); i++) {
                    aux = adj[top].get(i);
                    
                    if(visited[aux] == false){
                        //daca nodul copil nu a fost vizitat se creste distanta
                        //se marcheaza ca vizitat
                        //se adauga in coada
                        d[aux] = d[top] + 1;
                        visited [aux] = true;
                        queue.add(aux);
                    }
                }
            }
			return d;
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
