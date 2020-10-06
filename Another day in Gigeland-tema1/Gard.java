//Savu Ioana Rusalda 325CB
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.*;


public class Gard {
	static class Pair {
		Integer first;
		Integer second;

		public Pair(Integer first, Integer second) { //coordonate gard
			this.first = first; //capat inferior bucata gard
			this.second = second; //capat superior bucata gard
		}
       
	}
    
	static class Task {
		public static final String INPUT_FILE = "gard.in";
		public static final String OUTPUT_FILE = "gard.out";
		//ArrayList<Pair> garduri = new ArrayList<Pair>(); //multimea coordonatelor bucatilor de gard
        Pair [] garduri;
		int n;
		private static final int MOD = 1000000007;
		private void readInput() {
			try {
				int x,y; //variabile auxiliare
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextInt(); //numar bucati de gard 
                garduri = new Pair [n];
				for (int i = 0; i < n ; i++) {
					x = sc.nextInt(); //capat inferior bucata de gard
					y = sc.nextInt(); //capat superior bucata de gard
					//garduri.add(i,new Pair(x,y)); //adaugare bucata de gard
                    garduri[i] = new Pair(x,y);
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


		private long getResult() {
			int nr = 0; //numarul de bucati de gard redundante
			int index = 0; //variabila auxiliara ce retine ultima pozitie neredundanta
			//sortare crescatoare a multimii coordonatelor bucatilor de gard dupa capatul inferior
			//in caz de egalitate, descrescator dupa capatul superior
			//Collections.sort(garduri);
            Arrays.sort(garduri,new Comparator<Pair>() {
                public int compare(Pair a, Pair b) {
                    if(a.first < b.first)
                        return -1;
                    else if(a.first > b.first)
                        return 1;
                    else {
                        if(a.second> b.second
                            )
                            return -1;
                        else return 1;
                    }
                }
            });

			//parcurgere multime
			for (int i = 1; i < n; i++) {
				//if (garduri.get(i).second <= garduri.get(index).second) {
                if (garduri[i].second <= garduri[index].second) {
					nr++; //numarare in cazul in care se gaseste bucata de gard redundanta
				} else {
					index = i; //actualizare index ultima bucata de gard neredundanta
				}
			}
			return nr; //returnare rezultat
		}
		public void solve() {
			readInput(); //citire input fisier
			writeOutput(getResult()); //afisare output fisier
		}
	}

	public static void main(String[] args) {
		new Task().solve();
	}
}