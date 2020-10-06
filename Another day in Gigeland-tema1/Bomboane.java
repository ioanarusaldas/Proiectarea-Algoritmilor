import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Bomboane {
	static class Interval {
		Integer first; //capat inferior interval
		Integer second; //capat superior interval

		public Interval(Integer first, Integer second) {
			this.first = first; 
			this.second = second;
		}
	}
	static class Task {
		public static final String INPUT_FILE = "bomboane.in";
		public static final String OUTPUT_FILE = "bomboane.out";
		int n; //numar de elevi
		int m; //numar de bomboane
		Interval [] probleme; //vector cu intervalele de probleme al tuturor elevilor

		private static final int MOD = 1000000007;
		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextInt(); //citire numar de elevi
				m = sc.nextInt(); //citire numar de bomboane
				int x, y; //variablie auxiliare
				probleme = new Interval [n];
				for (int i = 0; i < n; i++) {
					x = sc.nextInt(); //capat inferior interval
					y = sc.nextInt(); //capat superior interval
					probleme[i] = new Interval(x,y); //completare vector de intervale
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(long result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		//functie care realizeaza suma modulo 1000000007;
		int logAdd(int a, int b) {
			return (((a % MOD) + (b % MOD)) % MOD);
		}

		private long getResult() {
			Integer [][] result = new Integer [n][m + 1];
			int rest_bomboane;
			int bomboane_copil_min;
			int bomboane_copil_max;
			int sum;
			int x, y;
			//initializare moduri de a imparti bomboane primului copil conform intervalului sau
			for (int j = 0; j < m + 1; j++) {
				if (j < probleme[0].first || j > probleme[0].second) {
					result[0][j] = 0;
				} else {
					result[0][j] = 1;
				}
			}
			//parcurgere elevi
			for (int i = 1; i < n; i++) {
				//capetele de interval pentru copilul i
				bomboane_copil_min = probleme[i].first; 
				bomboane_copil_max = probleme[i].second;
				//parcurgere numar de bomboane disponibile
				for (int j = 0; j < m + 1; j++) {
					//x, y capetele intervalului anterior in care se va realiza cautarea
					y = j - probleme[i].first;
					x = j - probleme[i].second;
					//verificare definire corecta interval
					if (x < 0) {
						x = 0;  
					} 
					if (y < 0) {   
						result[i][j] = 0;
						continue; 
					}
					sum = 0;
					//calculare mod de a imparti bomboane copilului curent in functie de modurile
					//anterioare de a imparti bomboane unui numar diferit de copii
					for (int k = x; k <= y; k++) {
						sum = logAdd(sum ,result[i - 1][k]);
					}
					result[i][j] = sum;		
				}

			}

			return (int) result[n - 1][m]; //returnare rezultat 
		}


		public void solve() {
			readInput(); //citire input fisier
			writeOutput(getResult()); //scriere output fisier
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}