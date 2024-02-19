package Graph;

import java.util.*;

public class DijkstraAlgo {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        HashMap<Integer, Integer> hm = new HashMap<>();
        ArrayList<PairClass> adj[] = new ArrayList[N + 1];

        int i, j, k;
        for (i = 1; i <= N; i++) {
            int x = sc.nextInt();
            hm.put(x, i);
        }
        for (i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        int E = sc.nextInt();
        for (i = 1; i <= E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int x = hm.get(a);
            int y = hm.get(b);
            adj[x].add(new PairClass(y, c));
        }
        int s = sc.nextInt();
        int d = sc.nextInt();
        s = hm.get(s);
        d = hm.get(d);
        PriorityQueue<PairClass> pq = new PriorityQueue<>();
        boolean visited[] = new boolean[N + 1];
        int D[] = new int[N + 1];
        Arrays.fill(D, 9999999);
        pq.add(new PairClass(s, 0));
        D[s] = 0;
        while (!pq.isEmpty()) {
            PairClass T = pq.poll();
            int a = T.a;
            int b = T.b;

            visited[a] = true;
            for (PairClass e : adj[a]) {
                int x = e.a;
                int y = e.b;
                if (!visited[x] && b + y < D[x]) { // this visited array can be avoided as well.
                    D[x] = b + y;
                    pq.add(new PairClass(x, b + y));
                }
            }
        }
        if (D[d] == 9999999)
            System.out.println(-1);
        else
            System.out.println(D[d]);
    }
}

class PairClass implements Comparable<PairClass> {
    int a, b;

    PairClass(int p, int q) {
        a = p;
        b = q;
    }

    public int compareTo(PairClass P) {
        return this.b - P.b;
    }
}