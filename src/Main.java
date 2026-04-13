import java.util.*;

class Grafo {
    int V;
    List<Arista> aristas = new ArrayList<>();

    static class Arista {
        int origen, destino, peso;

        Arista(int o, int d, int p) {
            origen = o;
            destino = d;
            peso = p;
        }
    }

    Grafo(int v) {
        V = v;
    }

    void agregarArista(int o, int d, int p) {
        aristas.add(new Arista(o, d, p));
    }

    // ===================== PRIM =====================
    void prim() {
        boolean[] visitado = new boolean[V];
        int[] costo = new int[V];
        int[] padre = new int[V];

        Arrays.fill(costo, Integer.MAX_VALUE);
        costo[0] = 0;
        padre[0] = -1;

        for (int i = 0; i < V - 1; i++) {
            int u = minCosto(costo, visitado);
            visitado[u] = true;

            for (Arista a : aristas) {
                if (a.origen == u && !visitado[a.destino] && a.peso < costo[a.destino]) {
                    costo[a.destino] = a.peso;
                    padre[a.destino] = u;
                }
                if (a.destino == u && !visitado[a.origen] && a.peso < costo[a.origen]) {
                    costo[a.origen] = a.peso;
                    padre[a.origen] = u;
                }
            }
        }

        int total = 0;
        System.out.println("PRIM - Aristas seleccionadas:");
        for (int i = 1; i < V; i++) {
            System.out.println(padre[i] + " - " + i + " peso: " + costo[i]);
            total += costo[i];
        }
        System.out.println("Costo total MST (Prim): " + total);
    }

    int minCosto(int[] costo, boolean[] visitado) {
        int min = Integer.MAX_VALUE, indice = -1;

        for (int i = 0; i < V; i++) {
            if (!visitado[i] && costo[i] < min) {
                min = costo[i];
                indice = i;
            }
        }
        return indice;
    }

    // ===================== KRUSKAL =====================
    int encontrar(int[] padre, int i) {
        if (padre[i] == i)
            return i;
        return encontrar(padre, padre[i]);
    }

    void unir(int[] padre, int x, int y) {
        int raizX = encontrar(padre, x);
        int raizY = encontrar(padre, y);
        padre[raizX] = raizY;
    }

    void kruskal() {
        Collections.sort(aristas, Comparator.comparingInt(a -> a.peso));

        int[] padre = new int[V];
        for (int i = 0; i < V; i++)
            padre[i] = i;

        int total = 0;
        System.out.println("\nKRUSKAL - Aristas seleccionadas:");

        for (Arista a : aristas) {
            int x = encontrar(padre, a.origen);
            int y = encontrar(padre, a.destino);

            if (x != y) {
                System.out.println(a.origen + " - " + a.destino + " peso: " + a.peso);
                total += a.peso;
                unir(padre, x, y);
            }
        }

        System.out.println("Costo total MST (Kruskal): " + total);
    }
}

// ===================== MAIN =====================
public class Main {
    public static void main(String[] args) {
        Grafo g = new Grafo(6);

        g.agregarArista(0, 1, 6);
        g.agregarArista(0, 2, 1);
        g.agregarArista(0, 3, 5);
        g.agregarArista(1, 2, 2);
        g.agregarArista(1, 4, 5);
        g.agregarArista(2, 3, 2);
        g.agregarArista(2, 4, 6);
        g.agregarArista(3, 5, 4);
        g.agregarArista(4, 5, 3);

        g.prim();
        g.kruskal();
    }
}