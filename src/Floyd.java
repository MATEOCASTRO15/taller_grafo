public class Floyd {

    static final int INF = 9999;

    public static void main(String[] args) {

        int[][] dist = {
            {0,10,INF,INF,3},
            {INF,0,2,INF,4},
            {INF,INF,0,9,INF},
            {INF,INF,7,0,INF},
            {INF,1,8,2,0}
        };

        int V = 5;

        for (int k = 0; k < V; k++)
            for (int i = 0; i < V; i++)
                for (int j = 0; j < V; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];

        System.out.println("Matriz de distancias:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++)
                System.out.print(dist[i][j] + " ");
            System.out.println();
        }

        System.out.println("Distancia mínima 0 -> 3: " + dist[0][3]);
    }
}