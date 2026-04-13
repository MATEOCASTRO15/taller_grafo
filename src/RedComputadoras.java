import java.util.*;

public class RedComputadoras {

    static Map<Integer, List<Integer>> red = new HashMap<>();

    static void agregarConexion(int a, int b) {
        red.putIfAbsent(a, new ArrayList<>());
        red.putIfAbsent(b, new ArrayList<>());
        red.get(a).add(b);
        red.get(b).add(a);
    }

    static boolean puedeComunicarse(int origen, int destino) {
        Set<Integer> visitado = new HashSet<>();
        Queue<Integer> cola = new LinkedList<>();

        cola.add(origen);
        visitado.add(origen);

        while (!cola.isEmpty()) {
            int nodo = cola.poll();
            if (nodo == destino) return true;

            for (int vecino : red.getOrDefault(nodo, new ArrayList<>())) {
                if (!visitado.contains(vecino)) {
                    visitado.add(vecino);
                    cola.add(vecino);
                }
            }
        }
        return false;
    }

    static void caminoCorto(int origen, int destino) {
        Map<Integer, Integer> padre = new HashMap<>();
        Queue<Integer> cola = new LinkedList<>();

        cola.add(origen);
        padre.put(origen, -1);

        while (!cola.isEmpty()) {
            int nodo = cola.poll();
            if (nodo == destino) break;

            for (int vecino : red.getOrDefault(nodo, new ArrayList<>())) {
                if (!padre.containsKey(vecino)) {
                    padre.put(vecino, nodo);
                    cola.add(vecino);
                }
            }
        }

        List<Integer> camino = new ArrayList<>();
        for (int v = destino; v != -1; v = padre.get(v))
            camino.add(v);

        Collections.reverse(camino);
        System.out.println("Camino más corto: " + camino);
    }

    public static void main(String[] args) {

        agregarConexion(0,1);
        agregarConexion(1,2);
        agregarConexion(2,3);
        agregarConexion(3,4);
        agregarConexion(4,5);

        System.out.println("¿0 se comunica con 5? " + puedeComunicarse(0,5));
        caminoCorto(0,5);
    }
}