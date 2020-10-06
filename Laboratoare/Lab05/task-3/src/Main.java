import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static class Task {
		public final static String INPUT_FILE = "in";
		public final static String OUTPUT_FILE = "out";

		int n;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new File(INPUT_FILE));
				n = sc.nextInt();
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(ArrayList<Integer> result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				for (int i = 0; i < n; i++) {
					pw.printf("%d%c", result.get(i), i == n ? '\n' : ' ');
				}
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		boolean check(int board[][], int row, int col) 
    	{ 
        	int i, j; 
        	for (i = 0; i < row; i++) 
            	if (board[i][col] == 1) 
                	return false; 
        	for (i = row, j = col; i >= 0 && j >= 0; i--, j--) 
            	if (board[i][j] == 1) 
                	return false; 
        	for (i = row, j = col; j < n && i >= 0; i--, j++) 
            	if (board[i][j] == 1) 
                	return false; 
        	return true; 
    	} 
   		boolean back(int board[][], int row) 
    	{ 
        	if (row >= n) 
            	return true; 
        	for (int i = 0; i < n; i++) { 
            	if (check(board, row, i)) { 
                	board[row][i] = 1;
                if (back(board, row + 1) == true) 
                    return true; 
                board[row][i] = 0; 
            } 
        } 
        return false; 
    } 


		private ArrayList<Integer> getResult() {
			ArrayList<Integer> sol = new ArrayList<Integer>(n);

			int board[][] = new int [n][n];
			 for (int i = 0; i <n ; i++) 
    		{ 
        		for (int j = 0; j < n; j++) {
        				board[i][j] = 0;
        		}
        	}


  
        	back(board, 0) ;
            	
         //    for (int i = 0; i < n; i++) { 
         //    for (int j = 0; j < n; j++) 
         //        System.out.print(" " + board[i][j] 
         //                         + " "); 
         //    	System.out.println(); 
        	// } 

            
            for (int i = 0; i <n ; i++) 
    		{ 
        		for (int j = 0; j < n; j++) 
            		if(board[i][j] == 1){
            			//System.out.println("i= "+i+"j= "+j);
            			sol.add(i,j+1);
            		}
    		} 
   //  		for (int i = 0; i < n; i++)
			// 	System.out.print(sol.get(i)+" ");
			// System.out.println();
   

			return sol;
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
