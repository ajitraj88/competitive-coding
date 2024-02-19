package SquareRootDecomposition;

/*
You are given a tree with N nodes (numbered 1 through N). For each valid i, node i has a value Ai.

You should answer Q queries. In each query:

You are given two nodes a and b.
Let S denote the set of all nodes on the simple path between the nodes a and b (including these nodes).
Find the minimum value of |Ax−Ay| over all pairs x,y∈S such that x≠y.

*/

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class MOSAlgoOnTreePlusLCAUsingSegmentTree {
    static int start[];  // MOs stuff
    static int end[];    // MOs stuff
    static int first[];   //LCAs stuff
    static int flatten[]; // MOs stuff
    static int euler[];   // LCAs stuff
    static boolean visited[];
    static int level[];       // LCAs stuff
    static int flattenTimer;  // MOs stuff
    static int eulerTimer;    // LCAs stuff
    static ArrayList<Integer> adj[];
    static int N, Q;
    static int vertex[];
    static int freq[];     // MOs stuff
    static int value[];    // MOs stuff

    public static void main(String ag[]) throws Exception {
        int i, j, k;
        FastReader sc = new FastReader(System.in);
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = sc.nextInt();
            Q = sc.nextInt();

            create();

            for (i = 0; i < N; i++)
                vertex[i] = sc.nextInt();

            for (i = 1; i < N; i++) {
                int a = sc.nextInt() - 1;
                int b = sc.nextInt() - 1;
                adj[a].add(b);
                adj[b].add(a);
            }
            DFS(0, 0);
            //System.out.println("start "+Arrays.toString(start));
            //System.out.println("end "+Arrays.toString(end));
            //System.out.println("first "+Arrays.toString(first));
            //System.out.println("euler "+Arrays.toString(euler));
            //System.out.println("flatten "+Arrays.toString(flatten));
            //System.out.println("level "+Arrays.toString(level));
            SegmentTree st = new SegmentTree(level, euler);

            ArrayList<QueryClass> query = new ArrayList<>();
            for (i = 1; i <= Q; i++) {
                int a = sc.nextInt() - 1;
                int b = sc.nextInt() - 1;

                if (first[a] > first[b]) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                int LCA = st.query(0, euler.length - 1, 0, first[a], first[b]);
                //System.out.println(a+" "+b+" "+LCA);
                if (start[a] > start[b]) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                if (LCA == a || LCA == b) {
                    query.add(new QueryClass(start[a], start[b], -1, i));
                } else {
                    query.add(new QueryClass(end[a], start[b], LCA, i));
                }
            }
            Collections.sort(query, new TComp(1000));

            int mL = -1;
            int mR = -1;
            int ANS[] = new int[Q + 1];
            for (i = 0; i < Q; i++) {
                int l = query.get(i).L;
                int r = query.get(i).R;
                int LCA = query.get(i).LCA;
                int id = query.get(i).ID;
                //System.out.println(l+" "+r+" "+LCA+" "+id);

                while (mL > l) {
                    mL--;
                    add(mL);
                }
                while (mR < r) {
                    mR++;
                    add(mR);
                }
                while (mL < l) {
                    remove(mL);
                    mL++;
                }
                while (mR > r) {
                    remove(mR);
                    mR--;
                }
                int pre = -1;
                int ans = Integer.MAX_VALUE;

                if (LCA != -1)
                    insert(LCA);
                for (j = 1; j <= 100; j++) {
                    if (value[j] > 0) {
                        if (pre != -1)
                            ans = Math.min(ans, j - pre);
                        pre = j;
                        if (value[j] > 1) {
                            ans = 0;
                            break;
                        }
                    }

                }
                if (LCA != -1)
                    delete(LCA);

                ANS[id] = ans;
            }
            for (i = 1; i <= Q; i++)
                sb.append(ANS[i] + "\n");
        }
        System.out.println(sb);
    }

    public static void create() {
        start = new int[N];
        end = new int[N];
        flatten = new int[2 * (N)];
        euler = new int[2 * (N)];
        first = new int[N];
        visited = new boolean[N];
        level = new int[N];
        flattenTimer = 0;
        eulerTimer = 0;
        adj = new ArrayList[N];
        vertex = new int[N];
        freq = new int[N];
        value = new int[101];
        for (int i = 0; i < N; i++)
            adj[i] = new ArrayList<>();
    }

    public static void DFS(int node, int lev) {
        visited[node] = true;
        level[node] = lev;
        start[node] = flattenTimer;
        flatten[flattenTimer++] = node;
        first[node] = eulerTimer;
        euler[eulerTimer++] = node;
        Iterator<Integer> itr = adj[node].iterator();
        while (itr.hasNext()) {
            int next = itr.next();
            if (!visited[next]) {
                DFS(next, lev + 1);
                euler[eulerTimer++] = node;
            }
        }

        end[node] = flattenTimer;
        flatten[flattenTimer++] = node;
    }

    public static void add(int pos) {
        if (pos == -1)
            return;
        freq[flatten[pos]]++;
        if (freq[flatten[pos]] % 2 == 0)
            delete(flatten[pos]);
        else
            insert(flatten[pos]);
    }

    public static void remove(int pos) {
        if (pos == -1)
            return;
        freq[flatten[pos]]--;
        if (freq[flatten[pos]] % 2 == 0)
            delete(flatten[pos]);
        else
            insert(flatten[pos]);
    }

    public static void insert(int node) {
        value[vertex[node]]++;
    }

    public static void delete(int node) {
        value[vertex[node]]--;
    }
}

class QueryClass {
    int L, R, LCA, ID;

    QueryClass(int a, int b, int c, int d) {
        L = a;
        R = b;
        LCA = c;
        ID = d;
    }
}

class TComp implements Comparator<QueryClass> {
    int BLOCK;

    TComp(int a) {
        BLOCK = a;
    }

    public int compare(QueryClass A, QueryClass B) {
        if (A.L / BLOCK != B.L / BLOCK)
            return A.L - B.L;
        else if ((A.L / BLOCK) % 2 == 0)
            return A.R - B.R;
        else
            return B.R - A.R;
    }
}

class SegmentTree {
    int ST[];
    int N;
    static int Level[];

    SegmentTree(int level[], int euler[]) {
        N = euler.length;
        Level = new int[level.length];
        for (int i = 0; i < level.length; i++)
            Level[i] = level[i];
        ST = new int[4 * (N) + 2];
        build(0, N - 1, euler, 0);

    }

    public void build(int l, int r, int euler[], int pos) {
        if (l == r) {
            ST[pos] = euler[l];
            return;
        } else {
            int mid = (l + r) >> 1;
            build(l, mid, euler, 2 * pos + 1);
            build(mid + 1, r, euler, 2 * pos + 2);

            int left = Level[ST[2 * pos + 1]];
            int right = Level[ST[2 * pos + 2]];
            if (left <= right)
                ST[pos] = ST[2 * pos + 1];
            else
                ST[pos] = ST[2 * pos + 2];
        }
    }

    public int query(int l, int r, int pos, int qs, int qe) {
        if (l > r || r < qs || l > qe)
            return -1;

        if (l >= qs && r <= qe)
            return ST[pos];

        int mid = (l + r) >> 1;
        int P1 = query(l, mid, 2 * pos + 1, qs, qe);
        int P2 = query(mid + 1, r, 2 * pos + 2, qs, qe);

        if (P1 == -1 && P2 == -1)
            return -1;
        if (P1 == -1)
            return P2;
        if (P2 == -1)
            return P1;
        if (Level[P1] <= Level[P2])
            return P1;
        return P2;
    }
}

//class FastReader {
//
//    byte[] buf = new byte[2048];
//    int index, total;
//    InputStream in;
//
//    FastReader(InputStream is) {
//        in = is;
//    }
//
//    int scan() throws IOException {
//        if (index >= total) {
//            index = 0;
//            total = in.read(buf);
//            if (total <= 0) {
//                return -1;
//            }
//        }
//        return buf[index++];
//    }
//
//    String next() throws IOException {
//        int c;
//        for (c = scan(); c <= 32; c = scan()) ;
//        StringBuilder sb = new StringBuilder();
//        for (; c > 32; c = scan()) {
//            sb.append((char) c);
//        }
//        return sb.toString();
//    }
//
//    int nextInt() throws IOException {
//        int c, val = 0;
//        for (c = scan(); c <= 32; c = scan()) ;
//        boolean neg = c == '-';
//        if (c == '-' || c == '+') {
//            c = scan();
//        }
//        for (; c >= '0' && c <= '9'; c = scan()) {
//            val = (val << 3) + (val << 1) + (c & 15);
//        }
//        return neg ? -val : val;
//    }
//
//    long nextLong() throws IOException {
//        int c;
//        long val = 0;
//        for (c = scan(); c <= 32; c = scan()) ;
//        boolean neg = c == '-';
//        if (c == '-' || c == '+') {
//            c = scan();
//        }
//        for (; c >= '0' && c <= '9'; c = scan()) {
//            val = (val << 3) + (val << 1) + (c & 15);
//        }
//        return neg ? -val : val;
//    }
//}