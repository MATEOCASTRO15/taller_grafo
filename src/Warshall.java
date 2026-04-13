public class Warshall {

    public static void main(String[] args) {

        int[][] grafo = {
            {0,1,0,0},
            {0,0,1,0},
            {1,0,0,1},
            {0,0,0,0}
        };

        int V = 4;

        for (int k = 0; k < V; k++)
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                    grafo[i][j] = (grafo[i][j] == 1 || (grafo[i][k] == 1 && grafo[k][j] == 1)) ? 1 : 0;

        System.out.println("Matriz de alcanzabilidad:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++)
                System.out.print(grafo[i][j] + " ");
            System.out.println();
        }

        boolean conexo = true;
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                if (grafo[i][j] == 0)
                    conexo = false;

        System.out.println("¿Es fuertemente conexo? " + conexo);
    }
}