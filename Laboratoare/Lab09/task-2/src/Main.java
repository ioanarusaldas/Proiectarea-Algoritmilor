import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static class Task {
		public static final String INPUT_FILE = "in";
		public static final String OUTPUT_FILE = "out";
		public static final int NMAX = 50005;

		int n;
		int m;
		int source;
        ArrayList<Integer> d = new ArrayList<>();
		public class Edge {
			public int node;
			public int cost;

			Edge(int _node, int _cost) {
				node = _node;
				cost = _cost;
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<Edge> adj[] = new ArrayList[NMAX];

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
					int x, y, w;
					x = sc.nextInt();
					y = sc.nextInt();
					w = sc.nextInt();
					adj[x].add(new Edge(y, w));
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(ArrayList<Integer> result) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(
								OUTPUT_FILE));
				StringBuilder sb = new StringBuilder();
				if (result.size() == 0) {
					sb.append("Ciclu negativ!\n");
				} else {
					for (int i = 1; i <= n; i++) {
						sb.append(result.get(i)).append(' ');
					}
					sb.append('\n');
				}
				bw.write(sb.toString());
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
        private int minPath(int nr) 
        { 
            int distance = -1; 
            int newDistance = -1; 
            int u;
            int p = 1;
            //parcurgere noduri de V-2 ori
           for (int j = 1; j <= nr; j++) {
                //parcurgere noduri
                for (int i = 1; i <= n; i++) { 
                    //parcurgere arce
                    for (int k = 0; k < adj[i].size(); k++ ) {
                        Edge v = adj[i].get(k); 
                        //recalculare distanta
                        distance = v.cost;
                        u = i;
                        newDistance = d.get(u) + distance;
                        if (newDistance < d.get(v.node)) 
                            d.set(v.node,newDistance); 
                    }
  
                }
            }
            //verificare ciclu negativ
            //parcurgere noduri
            for (int i = 1; i <= n; i++) { 
                //parcurgere arce
                for (int k = 0; k < adj[i].size(); k++ ) {
                    Edge v = adj[i].get(k); 
                    //recalculare distanta
                    distance = v.cost;
                    u = i;
                    newDistance = d.get(u) + distance;
                    if (newDistance < d.get(v.node)) {
                        //descoperire ciclu negativ
                        return -1;
                    }

                }
            }
            return 0;
 
        }
        //initializare vector distante
        private void initialize() {
            for (int i = 0; i <= n; i++) {
                if (i != source)
                    d.add(50000000);
                else 
                    d.add(0);
            }

        }

		private ArrayList<Integer> getResult() {
			// TODO: Gasiti distantele minime de la nodul source la celelalte noduri
			// folosind BellmanFord pe graful orientat cu n noduri, m arce stocat in
			// adj.
			//	d[node] = costul minim / lungimea minima a unui drum de la source la
			//	nodul node;
			//	d[source] = 0;
			//	d[node] = -1, daca nu se poate ajunge de la source la node.

			// Atentie:
			// O muchie este tinuta ca o pereche (nod adiacent, cost muchie):
			//	adj[x].get(i).node = nodul adiacent lui x,
			//	adj[x].get(i).cost = costul.

			// In cazul in care exista ciclu de cost negativ, returnati un vector gol:
			//	return new ArrayList<Integer>();


            //initializare vector distante
            initialize();
            //calculare drum minim
                //functia intoarce -1 in cazul unui ciclu negativ, 0 altfel
            if (minPath(n-2) == -1){
                return new ArrayList<Integer>();
            }
            //actualizare infinit cu -1
            for (int i = 0; i <= n; i++) {
                if(d.get(i) == 50000000)
                    d.set(i,-1);
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
