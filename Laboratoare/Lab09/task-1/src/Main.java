import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class Main {
    static class Task {
        public static final String INPUT_FILE = "in";
        public static final String OUTPUT_FILE = "out";
        public static final int NMAX = 50005;

        int n;
        int m;
        int source;
        PriorityQueue queue ;
        ArrayList<Integer> d = new ArrayList<>();
        ArrayList<Integer> p = new ArrayList<>();//vector parinti
        Boolean visited [];//vector noduri vizitate

        public class Edge implements Comparator<Edge> {
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
                source = sc.nextInt();

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
                for (int i = 1; i <= n; i++) {
                    sb.append(result.get(i)).append(' ');
                }
                sb.append('\n');
                bw.write(sb.toString());
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        //functie actualizare dinstnta in functie de nodul aflat in explorare si drumul minim
        //de la sursa calculat anterior
        private void update_distance(int u) 
        { 
            int distance = -1; 
            int newDistance = -1; 
            //parcurgere noduri
            for (int i = 0; i < adj[u].size(); i++) { 
                Edge v = adj[u].get(i); 
                //verificare daca nodul a fost vizitat
                if (visited[v.node] == false) { 
                    //calcularea noii distante
                    distance = v.cost; 
                    newDistance = d.get(u) + distance; 
                    if (newDistance < d.get(v.node)) {
                        //actualizare distanta + vector parinti daca s-a gasit o dinstanta mai
                        //mica fata de cea calculata anterior
                        d.set(v.node,newDistance); 
                        p.set(v.node,u);
                    }
                    //actualizare coada prioritati
                    queue.add(new Edge(v.node, d.get(v.node))); 
                } 
            } 
        }
        private void minPath(PriorityQueue<Edge> queue) {
            int u;
            //adaugare sursa in coada
            queue.add(new Edge(source,0));
            //actualizare distanta in functie de nodul aflat in explorare
            while(queue.size() != 0){
                u = ((Edge)queue.remove()).node; 
                visited[u] = true;
                update_distance(u); 
            }
            //inlocuire valoare maxima(infinit) cu -1
            for (int i = 0; i <= n; i++) {
                if(d.get(i) == Integer.MAX_VALUE)
                    d.set(i,-1);
            }
        }
        //functie initializare vector de distante + parinti
        private void initialize() {

            for (int i = 0; i <= n; i++) {
                visited[i] = false;
                if (i != source)
                    d.add(Integer.MAX_VALUE);
                else 
                    d.add(0);
                p.add(null);
            }

        }
        //functie afisare drum minim sursa-nod ales
        private static void printPath(int currentNode, 
                                    ArrayList<Integer> parents,int i) 
        { 
            if (parents.get(currentNode) == null) 
            {   
                return; 
            } 
            printPath(parents.get(currentNode), parents,i);
            if(currentNode != i)
                System.out.print(currentNode + " -> "); 
            else
                 System.out.print(currentNode + " " ); 
        } 
        //functie care se ocupa de rezolvarea bonusului
        private  void bonus(){
            for(int i = 1; i <=n ;i++) {
                if (i != source) {
                    if(p.get(i) != null){
                        System.out.println("Path from node "+ source +" to "+i);
                        System.out.print(source +" -> ");
                        printPath(i,p,i);
                        System.out.println();}
                    else {
                        System.out.println( "No path from " + source +" to " + i );
                    }
                }
        
            }
        }

        private ArrayList<Integer> getResult() {
            // TODO: Gasiti distantele minime de la nodul source la celelalte noduri
            // folosind Dijkstra pe graful orientat cu n noduri, m arce stocat in adj.
            //  d[node] = costul minim / lungimea minima a unui drum de la source la
            //  nodul node;
            //  d[source] = 0;
            //  d[node] = -1, daca nu se poate ajunge de la source la node.
            // Atentie:
            // O muchie este tinuta ca o pereche (nod adiacent, cost muchie):
            //  adj[x].get(i).node = nodul adiacent lui x,
            //  adj[x].get(i).cost = costul.
         
            visited = new Boolean[n+1];
            queue = new PriorityQueue<Edge>(n, new Edge()); 
            //initializare vector distante + parinti
            initialize();
            //calculare drum minim
            minPath(queue);
            //se decomenteaza apelul functiei bonus
                    // doar in cazul exercitiului de bonus -> rezultatul se poate vedea in err.log


            //bonus();
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