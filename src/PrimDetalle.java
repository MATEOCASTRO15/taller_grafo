import java.util.*;

public class PrimDetalle {

    static final int V = 6;

    static int[][] grafo = {
        {0,6,1,5,0,0},
        {6,0,2,0,5,0},
        {1,2,0,2,6,0},
        {5,0,2,0,0,4},
        {0,5,6,0,0,3},
        {0,0,0,4,3,0}
    };

    static int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < V; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        return minIndex;
    }

    static void prim(int inicio) {
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[inicio] = 0;
        parent[inicio] = -1;

        System.out.println("\nInicio en: " + inicio);

        for (int i = 0; i < V; i++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;

            if (parent[u] != -1)
                System.out.println(parent[u] + " - " + u + " peso: " + key[u]);

            for (int v = 0; v < V; v++) {
                if (grafo[u][v] != 0 && !mstSet[v] && grafo[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = grafo[u][v];
                }
            }
        }

        int total = Arrays.stream(key).sum();
        System.out.println("Costo total: " + total);
    }

    public static void main(String[] args) {
        prim(0);
        prim(2);
    }
}