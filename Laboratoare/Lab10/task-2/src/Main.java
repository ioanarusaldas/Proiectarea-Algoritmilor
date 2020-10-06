import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.*;

public class Main {
    static class Task {
        public static final String INPUT_FILE = "in";
        public static final String OUTPUT_FILE = "out";
        public static final int NMAX = 200005;

        int n;
        int m;


        public class Edge implements Comparator<Edge>{
            public int node;
            public int cost;
            public Edge(){}

            Edge(int _node, int _cost) {
                node = _node;
                cost = _cost;
            }
            //functie comparare noduri in functie de cost
            public int compare(Edge e1, Edge e2) 
            { 
                if (e1.cost < e2.cost) 
                    return -1; 
                if (e1.cost > e2.cost) 
                    return 1; 
                return 0; 
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

                for (int i = 1; i <= n; i++)
                    adj[i] = new ArrayList<>();
                for (int i = 1; i <= m; i++) {
                    int x, y, w;
                    x = sc.nextInt();
                    y = sc.nextInt();
                    w = sc.nextInt();
                    adj[x].add(new Edge(y, w));
                    adj[y].add(new Edge(x, w));
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(int result) {
            try {
                PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
                pw.printf("%d\n", result);
                pw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private int getResult() {
            /*
            TODO: Calculati costul minim al unui arbore de acoperire
            folosind algoritmul lui Prim.
            */
            int u;
            Edge edge;
            int cost_res = 0;
            int d[] =  new int [n+1];
            int p[] =  new int [n+1];
            Boolean visited []= new Boolean[n+1];
            int cost = 0;
            //initializare
            for (int i = 1; i <= n; i++) {
                d[i] = Integer.MAX_VALUE;
                p[i] = -1;
                visited[i] = false;
            }
            //setare distanta fata de nodul sursa
            d[1] = 0;
            PriorityQueue queue = new PriorityQueue<Edge>(n, new Edge()); 
            //adaugare nod sursa in heap
            queue.add(new Edge(1, 0));
            while(queue.size() != 0) {
                //extragere element din heap
                edge = (Edge)queue.remove();
                u = edge.node; 
                //verificare nod deja vizitat  + actualizare cost
                if (visited[u] == false) { 
                    visited[u] = true;
                    cost_res = cost_res + edge.cost;
                }
                //parcurgere vecini
                for (int i = 0; i < adj[u].size(); i++) { 
                    Edge v = adj[u].get(i); 
                    //verificare nod deja vizitat
                    if (visited[v.node] == false) { 
                        if(v.cost < d[v.node]){
                            d[v.node] = v.cost;
                            p[v.node] = u;
                            //adaugare in heap
                            queue.add(v);
                        }
                    } 
                }
            }
            
            return cost_res;
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
