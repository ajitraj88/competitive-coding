package SquareRootDecomposition;

/*
The traffic network in a country consists of N cities (labeled with integers from 1 to N) and N-1 roads connecting the cities. There is a unique path between each pair of different cities, and we know the exact length of each road.

Write a program that will, for each of the K given pairs of cities, find the length of the shortest and the length of the longest road on the path between the two cities.

*/

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class LCAFindingUsingBinaryLiftingToFindRequiredAnswerBetween2NodesInAPath {
    static int LCAparent[][];
    static int min[][];
    static int max[][];
    static boolean visited[];
    static int level[];
    static ArrayList<Pair> adj[];

    public static void main(String ag[]) throws Exception {
        FastReader sc = new FastReader(System.in);
        int i, j, k;
        int N = sc.nextInt();

        LCAparent = new int[N + 1][20];
        min = new int[N + 1][20];
        max = new int[N + 1][20];
        level = new int[N + 1];
        adj = new ArrayList[N + 1];
        for (i = 0; i <= N; i++)
            adj[i] = new ArrayList<>();

        for (i = 1; i < N; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            adj[a].add(new Pair(b, c));
            adj[b].add(new Pair(a, c));
        }
        for (i = 0; i <= N; i++) {
            Arrays.fill(LCAparent[i], -1);
            Arrays.fill(min[i], Integer.MAX_VALUE);
            Arrays.fill(max[i], Integer.MIN_VALUE);
        }

        DFS(1, 0, -1, -1);

        for (j = 1; j < 20; j++) {
            for (i = 1; i <= N; i++) {
                if (LCAparent[i][j - 1] != -1) {
                    int parent = LCAparent[i][j - 1]; //2^j parent cab be written as 2^(j-1)+2^(j-1) ie finding 2^(j-1)th parent & then finding parent 2^(j-1) parent
                    LCAparent[i][j] = LCAparent[parent][j - 1];
                    max[i][j] = Math.max(max[i][j - 1], max[LCAparent[i][j - 1]][j - 1]);
                    min[i][j] = Math.min(min[i][j - 1], min[LCAparent[i][j - 1]][j - 1]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int Q = sc.nextInt();
        while (Q-- > 0) {
            int L = sc.nextInt();
            int R = sc.nextInt();

            int LCA = findLCA(L, R);
            if (LCA == L) {
                Pair P = findAns(R, LCA);
                sb.append(P.b + " " + P.a + "\n");
            } else if (LCA == R) {
                Pair P = findAns(L, LCA);
                sb.append(P.b + " " + P.a + "\n");
            } else {
                Pair P1 = findAns(L, LCA);
                Pair P2 = findAns(R, LCA);
                Pair P = new Pair(Integer.MIN_VALUE, Integer.MAX_VALUE);
                P.a = Math.max(P1.a, P2.a);
                P.b = Math.min(P1.b, P2.b);
                sb.append(P.b + " " + P.a + "\n");
            }
        }
        System.out.println(sb);
    }

    public static void DFS(int node, int lev, int dist, int parent) {
        level[node] = lev;
        LCAparent[node][0] = parent;    //finding immediate parent of every node
        if (dist != -1) {
            min[node][0] = max[node][0] = dist;
        }
        for (Pair P : adj[node]) {
            int next = P.a;
            int D = P.b;
            if (next != parent) {
                DFS(next, lev + 1, D, node);
            }
        }
    }

    public static Pair findAns(int a, int b) {
        int lg;
        Pair P = new Pair(Integer.MIN_VALUE, Integer.MAX_VALUE);

        for (lg = 1; (1 << lg) <= level[a]; lg++) ;
        lg--;

        for (int i = lg; i >= 0; i--) {
            if (level[a] - (1 << i) >= level[b]) {
                P.a = Math.max(P.a, max[a][i]);
                P.b = Math.min(P.b, min[a][i]);
                a = LCAparent[a][i];
            }
        }

        return P;
    }

    public static int findLCA(int a, int b) {
        int lg;

        if (level[a] > level[b])   //making b as greater level node
        {
            int temp = a;
            a = b;
            b = temp;
        }
        int diff = level[b] - level[a];

        while (diff > 0)  //making b come to same level as a
        {
            int i = (int) ((double) Math.log(diff) / (double) Math.log(2));
            b = LCAparent[b][i];  // jump of 2^(i) current b
            diff -= (1 << i);   //since this has been jumped, so decrease it to cover remaining
        }

        if (a == b)
            return a;

        for (int i = 19; i >= 0; i--) {
            //ie this 2^i parent should exists that is not equal to -1 && then for a & b their 2^i th parent should not be same otherwise we may reach above
            //LCA node or LCA node, exactly can't be determined
            if (LCAparent[a][i] != -1 && LCAparent[a][i] != LCAparent[b][i])  //searching for max possible jump together
            {
                a = LCAparent[a][i];  // a being jumped 2^i
                b = LCAparent[b][i];  // b being jumped 2^i
            }
        }
        return LCAparent[a][0];  //since we are just below level of LCA node so either return LCAparent[a][0] or LCAparent[b][0]
    }
}

class Pair {
    int a, b;

    Pair(int p, int q) {
        a = p;
        b = q;
    }
}

class FastReader {

    byte[] buf = new byte[2048];
    int index, total;
    InputStream in;

    FastReader(InputStream is) {
        in = is;
    }

    int scan() throws IOException {
        if (index >= total) {
            index = 0;
            total = in.read(buf);
            if (total <= 0) {
                return -1;
            }
        }
        return buf[index++];
    }

    String next() throws IOException {
        int c;
        for (c = scan(); c <= 32; c = scan()) ;
        StringBuilder sb = new StringBuilder();
        for (; c > 32; c = scan()) {
            sb.append((char) c);
        }
        return sb.toString();
    }

    int nextInt() throws IOException {
        int c, val = 0;
        for (c = scan(); c <= 32; c = scan()) ;
        boolean neg = c == '-';
        if (c == '-' || c == '+') {
            c = scan();
        }
        for (; c >= '0' && c <= '9'; c = scan()) {
            val = (val << 3) + (val << 1) + (c & 15);
        }
        return neg ? -val : val;
    }

    long nextLong() throws IOException {
        int c;
        long val = 0;
        for (c = scan(); c <= 32; c = scan()) ;
        boolean neg = c == '-';
        if (c == '-' || c == '+') {
            c = scan();
        }
        for (; c >= '0' && c <= '9'; c = scan()) {
            val = (val << 3) + (val << 1) + (c & 15);
        }
        return neg ? -val : val;
    }
}