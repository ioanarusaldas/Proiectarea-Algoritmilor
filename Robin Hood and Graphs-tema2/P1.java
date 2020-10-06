import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class P1 {
	static class Task implements Cloneable  {
		public static final String INPUT_FILE = "p1.in";
		public static final String OUTPUT_FILE = "p1.out";
		public static final int NMAX = 100005; // 10^5

		int n;
		int m;
		int k;
		@SuppressWarnings("unchecked")
		ArrayList<Integer>[] adj = new ArrayList[NMAX];
		ArrayList<Integer> lorzi;
		int source;
		int [] permutari;

		boolean [] block;
		boolean [] visited;
		int left;
		int right;
		int mij,min;
		int towns_blocked;



		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				k = sc.nextInt();
				lorzi = new ArrayList();
				permutari = new int[n + 1];

				//orase in care se afla lorzi
				for (int i = 1; i <= k; i++) {
					lorzi.add(sc.nextInt());
				}
				//permutarile de orase
				for (int i = 1; i <= n - 1; i++) {
					permutari[i] = sc.nextInt();
				}
				//harta orase (graph reprezentat prin adj)
				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
				}
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

		private void writeOutput(int result) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
								OUTPUT_FILE)));
				pw.printf("%d",result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		private int bfs(boolean [] visited_copy) {
			Queue<Integer> queue = new LinkedList<>(); 
			Integer top;
			int aux;
			int x;
			boolean [] visited_aux = new boolean[n + 1];
			visited_aux = visited_copy.clone();
			//adaugare sursa in coada
			queue.add(source);
			visited_aux[source] = true;
			//parcurgere coada
			while (queue.size() != 0) {
				top = queue.remove();
				//parcurgere muchii nod
				for (int i = 0; i < adj[top].size(); i++) {
					aux = adj[top].get(i);
					if ((visited_aux[aux] == false) && (block[aux] == false)) {
						//daca nodul copil nu a fost vizitat sau orasul nu a fost blocat
						//se marcheaza ca vizitat
						//se adauga in coada
						visited_aux [aux] = true;
						queue.add(aux);
					}
					//cutare oras curent in lista de orase in care se afla lorzii
					x = lorzi.indexOf(aux);
					//orasul este un oras in care sa afla lorzi => Robin poate fi atacat
					//se incearca un alt numar de orase blocate
					if ((x != -1) && (visited_aux[aux] == true)) {
						return 0;
					}
				}
			}
			//Robin nu poate fi atacat
			return 1;
		}
		private void initialize() {
			for (int i = 0; i <= n; i++) {
				visited[i] = false;
				block[i] = false;
			}
		}
		private void block_towns() {
			for (int i = 1; i <= n - 1; i++) {
				if (i <= towns_blocked) {
					block[permutari[i]] = true;
				} else {
					block[permutari[i]] = false;
				}
			}
		}

		private int getResult() {
			//vector orase vizitate
			visited = new boolean[n + 1];
			//vector orase blocate
			block = new boolean[n + 1];

			initialize();

			//cautare binara numar minim de orase blocate
			//initializare indici 
			left = 1;
			right = n - 1;
			mij = left + (right - left) / 2;
			min = n - 1;
			//numar de orse blocate la primul pas
			towns_blocked = mij;
			while (right >= left) {
				block_towns();
				source = 1;
				//parcurgere bfs din orasul in care se afla Robin (source)
				int ok = bfs(visited);
				//verificare daca lorzii pot ataca orasul lui Robin
				if (ok == 1) {
					//lorzii nu pot ataca
					if (mij < min) {
						//verificare numar minim orase blocate
						min = mij;
					} else {
						break;
					}
					//reactualizare indici pentru incercare cautare un numar mai mic de 
					//orase blocate
					right = mij - 1;
					mij = left + (right - left) / 2;
					towns_blocked = mij;//actualizare numar de orase ce trebuie blocate
				} else {
					//lorzii pot ataca
					if (mij > min) {
						//reincercare numar mai mic de orase blocate
						right = mij - 1;
						mij = left + (right - left) / 2;
					} else {
						//reincercare numar mai mare de orase blocate
						left = mij + 1;
						mij = left + (right - left) / 2;
						towns_blocked = mij;
					}
				}
			}
			return min;
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