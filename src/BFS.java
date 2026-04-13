import java.util.*;

public class BFS {

    static List<Integer>[] grafo;

    static void agregar(int u, int v) {
        grafo[u].add(v);
        grafo[v].add(u);
    }

    static void bfs(int inicio, int destino) {
        boolean[] visitado = new boolean[6];
        int[] padre = new int[6];

        Queue<Integer> cola = new LinkedList<>();
        cola.add(inicio);
        visitado[inicio] = true;
        padre[inicio] = -1;

        while (!cola.isEmpty()) {
            int u = cola.poll();

            for (int v : grafo[u]) {
                if (!visitado[v]) {
                    visitado[v] = true;
                    padre[v] = u;
                    cola.add(v);
                }
            }
        }

        List<Integer> camino = new ArrayList<>();
        for (int v = destino; v != -1; v = padre[v])
            camino.add(v);

        Collections.reverse(camino);
        System.out.println("Camino más corto: " + camino);
    }

    public static void main(String[] args) {
        grafo = new ArrayList[6];
        for (int i = 0; i < 6; i++) grafo[i] = new ArrayList<>();

        agregar(0,1);
        agregar(0,2);
        agregar(1,3);
        agregar(2,3);
        agregar(3,4);
        agregar(4,5);

        bfs(0,5);
    }
}