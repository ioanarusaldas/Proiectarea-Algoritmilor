import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;



public class P3 {
	static class Task {
		public static final String INPUT_FILE = "p3.in";
		public static final String OUTPUT_FILE = "p3.out";
		public static final int NMAX = 100001;

		int n;
		int m;
		int source;
		int energy;
		PriorityQueue queue ;
		ArrayList<Double> d = new ArrayList<>();
		ArrayList<Integer> p = new ArrayList<>();//vector parinti
		Boolean[] visited;//vector noduri vizitate
		public static StringBuilder sb;
	

		public class Edge implements Comparator<Edge> {
			public int node;
			public double cost;
			public Edge(){}

			Edge(int _node, double _cost) {
				node = _node;
				cost = _cost;
			}
			//functie comparare noduri in functie de cost
			public int compare(Edge e1, Edge e2) { 
				if (e1.cost > e2.cost) {
					return -1; 
				}
				if (e1.cost < e2.cost) {
					return 1; 
				}
				return 0; 
			} 
		}

		@SuppressWarnings("unchecked")
		ArrayList<Edge>[] adj = new ArrayList[NMAX];

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				energy = sc.nextInt();

				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
				}
				for (int i = 1; i <= m; i++) {
					int x, y;
					double w;
					x = sc.nextInt();
					y = sc.nextInt();
					w = sc.nextDouble();
					adj[x].add(new Edge(y, w));
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(ArrayList<Double> result) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(
								OUTPUT_FILE));
				double res = energy * result.get(n);
				bw.write(String.valueOf(res));
				bw.write("\n");
				path(n);
				bw.write(sb.toString());
				bw.close();			
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		//functie actualizare dinstnta in functie de nodul aflat in explorare si drumul minim
		//de la sursa calculat anterior
		private void update_distance(int u) { 
			double distance = 0.0; 
			double newDistance = 0.0; 
			//parcurgere noduri
			for (int i = 0; i < adj[u].size(); i++) { 
				Edge v = adj[u].get(i); 
				//verificare daca nodul a fost vizitat
				if (visited[v.node] == false) { 
					//calcularea noii distante
					distance = v.cost; 
					newDistance = (double)  d.get(u) * ((double)1 - (double)(distance / 100)); 
					if (newDistance > d.get(v.node)) {
						//actualizare distanta + vector parinti daca s-a gasit o dinstanta mai
						//mica fata de cea calculata anterior
						d.set(v.node,newDistance); 
						p.set(v.node,u);
					}
					//actualizare coada prioritati
					queue.add(new Edge(v.node, newDistance)); 
				} 
			} 
		}
		private void minPath(PriorityQueue<Edge> queue) {
			int u;
			//adaugare sursa in coada
			queue.add(new Edge(source,1.0));
			//actualizare distanta in functie de nodul aflat in explorare
			while (queue.size() != 0) {
				u = ((Edge)queue.remove()).node; 
				visited[u] = true;
				update_distance(u); 
			}

		}
		//functie initializare vector de distante + parinti
		private void initialize() {
			for (int i = 0; i <= n; i++) {
				visited[i] = false;
				if (i != source) {
					d.add(0.0);
				} else { 
					d.add(1.0);
				}
				p.add(null);
			}

		}
		//functie afisare drum minim sursa-nod ales
		private static void printPath(int currentNode, 
									ArrayList<Integer> parents,int i) { 
			if (parents.get(currentNode) == null) {   
				return; 
			} 
			printPath(parents.get(currentNode), parents,i);
			if (currentNode != i) {
				sb.append(currentNode).append(' '); 
			} else {
				sb.append(currentNode); 
			}
		} 
		//functie care se ocupa de reproducerea drumului
		private  void path(int i) {
			sb = new StringBuilder();
			if (i != source) {
				if (p.get(i) != null) {
					sb.append(source).append(' ');
					printPath(i,p,i);
				}
			}
		}

		private ArrayList<Double> getResult() {
			visited = new Boolean[n + 1];
			queue = new PriorityQueue<Edge>(n, new Edge()); 
			source = 1;
			//initializare vector distante + parinti
			initialize();
			//calculare drum minim
			minPath(queue);
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