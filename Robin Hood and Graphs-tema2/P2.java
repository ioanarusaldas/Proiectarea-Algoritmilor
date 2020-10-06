import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



public class P2 {
	static class Task {
		public static final String INPUT_FILE = "p2.in";
		public static final String OUTPUT_FILE = "p2.out";
		public static final int NMAX = 100001;
		public static final int INF = (int) 1e9;


		int n;
		int m;
		int source;
		int dest;
		@SuppressWarnings("unchecked")
		ArrayList<Edge>[] adj = new ArrayList[NMAX];

		public class Edge {
			public int node;
			public int cost;

			Edge(int _node, int _cost) {
				node = _node;
				cost = _cost;
			}
		}

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				source = sc.nextInt();
				dest = sc.nextInt();

				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
				}
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
				//afisare drum pana la destinatie
				sb.append(result.get(dest));
				
				bw.write(sb.toString());
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		private ArrayList<Integer> solveDfs() {
			ArrayList<Integer> topsort = new ArrayList<>();
			boolean[] visited = new boolean[n + 1];

			// pentru fiecare oras
			for (int i = 1; i <= n; i++) {
				// daca orasul nu a fost vizitat, pornim o parcurgere DFS
				if (!visited[i]) {
					dfs(i, visited, topsort);
				}
			}
			// rezultatul a fost obtinut in ordine inversa
			Collections.reverse(topsort);
			return topsort;
		}


		private void dfs(int node, boolean[] visited, ArrayList<Integer> topsort) {
			// porneste o parcurgere DFS din orasul curent
			// foloseste vectorul visited pentru a marca orasele vizitate
			visited[node] = true;

			for (Edge v: adj[node]) {
				if (!visited[v.node]) {
					dfs(v.node, visited, topsort);
				}
			}
			// dupa ce am terminat de vizitat orasul, il adagugam in sortarea topologica
			topsort.add(node);
		}
		private void bellman(int source, ArrayList<Integer> d) {

			// Initializam distantele catre toate nodurile cu infinit
			for (int i = 1; i <= n; i++) {
				d.set(i, INF);
			}

			// Setez distanta pana la sursa la 0
			d.set(source, 0);
			//sortare topologica
			ArrayList<Integer> sorted = solveDfs();
			//relaxare muchii
			relax_edges(d,sorted);
		}

		private void relax_edges(ArrayList<Integer> d,ArrayList<Integer> topsort) {
			//parcurgere orase din sortarea topologica
			for (int u = 0; u < n; u++) {
				//pentru fiecare oras din sortarea topologica
				int node = topsort.get(u);
				for (Edge e : adj[node]) {
					//relaxare muchii
					int v = e.node, cost = e.cost;
					//daca se gaseste o distanta mai buna
					//se actualizeaza in vectorul de distante
					if (d.get(node) + cost < d.get(v)) {
						d.set(v, d.get(node) + cost);
					}
				}
			}
		}

		private ArrayList<Integer> getResult() {
			ArrayList<Integer> d = new ArrayList<>();
			//initializare vector distante
			for (int i = 0; i <= n; i++) {
				d.add(0);
			}
			//calculare distante minime de la orasul sursa la fiecare oras
			bellman(source, d);

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
