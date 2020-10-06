/*Savu Ioana Rusalda 325CB*/
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Bani {
	static class Task {
		public static final String INPUT_FILE = "bani.in";
		public static final String OUTPUT_FILE = "bani.out";
		int set;//numarul setului
		int n;//nr de bacnote cu care Gigel trebuie sa realizeze combinatiile
		private static final int MOD = 1000000007;
		private void readInput() {
			//se realizeaza citirea din fisier
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				set = sc.nextInt();
				n = sc.nextInt();
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(long result) {
			//scrierea in fisier a rezultatului
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d\n", result);
				pw.close();//inchidere fisier
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		// functia care realizeaza ridicarea la putere 
		// returneaza base ^ exp % MOD
		long logPow(long base, int exp) {
			if (exp == 0) {
				return 1;
			}

			long tmp = 1;
			while (exp != 1) {
				if (exp % 2 == 0) {
					base = (long)((1L * base * base) % MOD);
					exp /= 2;
				} else {
					tmp = (long)((1L * tmp * base) % MOD);
					exp--;
				}
			}

			return (long)((1L * tmp * base) % MOD);
		}
		//functie care realizeaza adunarea modulo 1000000007
		int logAdd(int a, int b) {
			return (((a % MOD) + (b % MOD)) % MOD);
		}

		private long getResult() {
			int result1;
			long result;
			int bacnota10, bacnota50, bacnota100, bacnota200, bacnota500;
			int aux;
			int aux2;
			result = logPow(2, n - 1);
			if (set == 1) { //set 1 5*(2^(n-1))
				return (long)((1L * 5 * result) % MOD);
			}
			if (set == 2) { //set 2 programare dinamica
				ArrayList<Integer> combinatiiBacnote = new ArrayList<Integer>(5);
				//5 = numarul de bacnote folosite (10, 50, 100, 200, 500)
				for (int i = 0; i < 5; i++) {
					combinatiiBacnote.add(i,1);
					//initializare arraylist cu cel putin o bacnota din fiecare tip
					/*explicatie: Gigel poate incepe combinatiile cu oricare dintre bacnote,
																distingandu-se 5 cazuri*/

				}
				for (int i = 1; i < n ; i++) {
					//calcularea noilor combinatii si actualizarea vectorului
					//stocare numar de bacnote folosite la pasul anterior
					bacnota10 = combinatiiBacnote.get(0);
					bacnota50 = combinatiiBacnote.get(1);
					bacnota100 = combinatiiBacnote.get(2);
					bacnota200 = combinatiiBacnote.get(3);
					bacnota500 = combinatiiBacnote.get(4);
					//recalculare numar de bacnote de 10 folosite
					aux = logAdd(bacnota50,bacnota100);
					aux =  logAdd(aux,bacnota500);
					combinatiiBacnote.set(0,aux);
					//recalculare numar de bacnote de 50 folosite
					combinatiiBacnote.set(1,logAdd(bacnota10,bacnota200));
					//recalculare numar de bacnote de 100 folosite
					aux = logAdd(bacnota10,bacnota100);
					aux = logAdd(aux,bacnota200);
					combinatiiBacnote.set(2,aux);
					//recalculare numar de bacnote de 200 folosite
					combinatiiBacnote.set(3,logAdd(bacnota50,bacnota500));
					//recalculare numar de bacnote de 500 folosite
					combinatiiBacnote.set(4,bacnota200);
				}
				result1 = 0;
				//calcularea rezultatului final prin suma bacnotelor folosite in fiecare caz
				for (int i = 0; i < 5; i++) {
					result1 = logAdd(result1 ,combinatiiBacnote.get(i));
				}
				return result1; //returnare rezultat
			}
			return 0;
		}


		public void solve() {
			readInput();//citire input
			writeOutput(getResult());//scriere output in fisier
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}