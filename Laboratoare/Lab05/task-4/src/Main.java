import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static class Task {
		public final static String INPUT_FILE = "in";
		public final static String OUTPUT_FILE = "out";

		int n, k;
		char[] caractere;
		int[] freq;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextInt();
				k = sc.nextInt();
				String s = sc.next().trim();
				s = " " + s;
				caractere = s.toCharArray();
				freq = new int[n + 1];
				for (int i = 1; i <= n; i++) {
					freq[i] = sc.nextInt();
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(ArrayList<ArrayList<Character>> result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d\n", result.size());
				for (ArrayList<Character> arr : result) {
					for (int i = 0; i < arr.size(); i++) {
						pw.printf("%c", arr.get(i));
					}
					pw.printf("\n");
				}
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		private boolean check(ArrayList<Character> solution){
            return true;
        }
        private void back(int step, int stop, ArrayList<Character> domain,ArrayList<Character> solution,
        	ArrayList<ArrayList<Character>> all) {
        	
    		//System.out.println(step);
    		if (step == stop) {
    			//System.out.println("return"+step);
        		//if(checkFreq(freq)) {
            	//printSolution(solution);

        			ArrayList<Character> c = new ArrayList <Character>(solution);
                    all.add(c);
                    return;
        		//}
        		//return;
    		}
    		//if(checkFreq(freq))
    		//	return;
    	
 
   			for ( int i = 0; i < domain.size(); i++) {
   				int aux = i+1;
    		}
		}
 
        
	

		private ArrayList<ArrayList<Character>> getResult() {
			ArrayList<ArrayList<Character>> all = new ArrayList<>();
			ArrayList<Character> domain = new ArrayList<>();
			//ArrayList<Boolean> visited =  new ArrayList<>();
			ArrayList<Character> solution =  new ArrayList<>();


			// TODO: Construiti toate sirurile cu caracterele in stringul
			// caractere (indexat de la 1 la n), si frecventele in vectorul freq
			// (indexat de la 1 la n), stiind ca nu pot fi mai mult de K
			// aparitii consecutive ale aceluiasi caracter.

			// Pentru a adauga un nou sir:
			//   ArrayList<Character> sir;
			//   all.add(sir);
			for(int i = 0 ;i < caractere.length-1; i++){
				//for(int j = 0; j < freq[i]; j++)
					{	
						//System.out.println(i+" " +caractere[i+1]);
						domain.add(caractere[i+1]);
						//visited.add(false);
					}

			}
	
			int sum = 0;
			for(int i : freq)
				sum += i;


			back( 0 ,sum,  domain, solution,all);
			//System.out.println(all.size());


			// System.out.println(all.size());
			// System.out.println(all.get(0).size());
			// for(int i = 0 ;i < all.size(); i++){
			// 	for(int j = 0 ;j < all.get(i).size(); j++)
			// 		{System.out.print(all.get(i).get(j)+" ");}
			// 	System.out.println();
			// }


			//ArrayList<Character> domain = new ArrayList<Character>();
           
			return all;
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
