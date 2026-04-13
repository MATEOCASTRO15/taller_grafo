import java.util.*;

public class Dijkstra {

    static final int V = 5;

    static int minDist(int[] dist, boolean[] visitado) {
        int min = Integer.MAX_VALUE, index = -1;
        for (int i = 0; i < V; i++)
            if (!visitado[i] && dist[i] < min) {
                min = dist[i];
                index = i;
            }
        return index;
    }

    public static void main(String[] args) {

        int[][] grafo = {
            {0,10,0,0,3},
            {0,0,2,0,4},
            {0,0,0,9,0},
            {0,0,7,0,0},
            {0,1,8,2,0}
        };

        int[] dist = new int[V];
        int[] padre = new int[V];
        boolean[] visitado = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        padre[0] = -1;

        for (int i = 0; i < V - 1; i++) {
            int u = minDist(dist, visitado);
            visitado[u] = true;

            for (int v = 0; v < V; v++) {
                if (!visitado[v] && grafo[u][v] != 0 &&
                    dist[u] + grafo[u][v] < dist[v]) {
                    dist[v] = dist[u] + grafo[u][v];
                    padre[v] = u;
                }
            }
        }

        for (int i = 0; i < V; i++) {
            System.out.print("0 -> " + i + " = " + dist[i] + " | Camino: ");
            imprimirCamino(padre, i);
            System.out.println();
        }
    }

    static void imprimirCamino(int[] padre, int j) {
        if (j == -1) return;
        imprimirCamino(padre, padre[j]);
        System.out.print(j + " ");
    }
}