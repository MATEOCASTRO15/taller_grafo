import java.util.*;

class KruskalDetalle {

    static class Arista {
        int o, d, p;
        Arista(int o, int d, int p) {
            this.o = o; this.d = d; this.p = p;
        }
    }

    static int find(int[] parent, int i) {
        if (parent[i] == i) return i;
        return find(parent, parent[i]);
    }

    static void union(int[] parent, int x, int y) {
        parent[find(parent, x)] = find(parent, y);
    }

    public static void main(String[] args) {

        List<Arista> aristas = new ArrayList<>();
        aristas.add(new Arista(0,1,6));
        aristas.add(new Arista(0,2,1));
        aristas.add(new Arista(0,3,5));
        aristas.add(new Arista(1,2,2));
        aristas.add(new Arista(1,4,5));
        aristas.add(new Arista(2,3,2));
        aristas.add(new Arista(2,4,6));
        aristas.add(new Arista(3,5,4));
        aristas.add(new Arista(4,5,3));

        aristas.sort(Comparator.comparingInt(a -> a.p));

        int[] parent = new int[6];
        for (int i = 0; i < 6; i++) parent[i] = i;

        int descartadas = 0;

        System.out.println("Aristas seleccionadas:");

        for (Arista a : aristas) {
            int x = find(parent, a.o);
            int y = find(parent, a.d);

            if (x != y) {
                System.out.println(a.o + "-" + a.d + " peso: " + a.p);
                union(parent, x, y);
            } else {
                descartadas++;
            }
        }

        System.out.println("Aristas descartadas por ciclo: " + descartadas);
    }
}