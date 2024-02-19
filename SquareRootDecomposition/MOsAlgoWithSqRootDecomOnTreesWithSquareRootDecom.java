package SquareRootDecomposition;

/*
given N vertices of a tree and each vertex has some color ,, in each query you are given V,K
The answer to query V, K  is the number of such colors of vertices x, that the subtree of vertex V how many colors are there such that color occurs atleast
K times.
*/

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class MOsAlgoWithSqRootDecomOnTreesWithSquareRootDecom {
    static int color[];
    static int freqcolor[];  //stores freq of color ie how many times each color occurs
    static int freqnode[];  //stores frequency of node whwnever it becomes 2 means it's subtress is visited including it
    static int freqoffreq[];  //stores how many times i th freq occurs so to ans how many colors occur x times check freqofreq from x to N
    // ie O(N) time to chec k
    static int start[];
    static int end[];
    static int flatten[];
    static int bucket[];
    static ArrayList<Integer> adj[];
    static Query arr[];
    static int BLOCK;
    static int timer;
    static boolean visited[];

    public static void main(String ag[]) throws Exception {
        Reader sc = new Reader();
        int i, j, k;
        int N = sc.nextInt();
        int M = sc.nextInt();

        adj = new ArrayList[N + 1];
        start = new int[N + 1];
        end = new int[N + 1];
        color = new int[N + 1];
        freqcolor = new int[100000 + 1];
        freqnode = new int[N + 1];
        freqoffreq = new int[100000 + 1];
        flatten = new int[2 * N + 1];
        visited = new boolean[N + 1];
        bucket = new int[100000 + 1];
        arr = new Query[M];
        BLOCK = 600;
        timer = 1;

        for (i = 1; i <= N; i++)
            color[i] = sc.nextInt();

        for (i = 0; i <= N; i++)
            adj[i] = new ArrayList<>();

        for (i = 1; i < N; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }

        DFS(1);

        for (i = 1; i <= M; i++) {
            int node = sc.nextInt();
            int color = sc.nextInt();
            arr[i - 1] = new Query(start[node], end[node], i, color);  //making start and end time of each node as L & R
        }
        Arrays.sort(arr, new myComparator());

        int ans[] = new int[M + 1];
        int mL = 1;  //index of flat tree
        int mR = 0;  //index of flat tree
        for (i = 0; i < M; i++) {
            int L = arr[i].l;
            int R = arr[i].r;
            int ID = arr[i].id;
            int C = arr[i].c;

            while (mL > L) {
                mL--;
                add(mL);
            }
            while (mR < R) {
                mR++;
                add(mR);
            }
            while (mL < L) {
                remove(mL);
                mL++;
            }
            while (mR > R) {
                remove(mR);
                mR--;
            }

            ans[ID] = find(C, N);
        }

        PrintWriter printr = new PrintWriter(System.out);
        for (i = 1; i <= M; i++)
            printr.println(ans[i]);
        //sb.append(ans[i]+"\n");

        printr.flush();

    }

    public static void DFS(int node) {
        visited[node] = true;
        start[node] = timer;
        flatten[timer] = node;
        timer++;

        Iterator<Integer> itr = adj[node].iterator();
        while (itr.hasNext()) {
            int X = itr.next();
            if (!visited[X])
                DFS(X);
        }

        end[node] = timer;
        flatten[timer] = node;
        timer++;
    }

    public static void add(int pos) {
        int node = flatten[pos];
        int c = color[node];
        freqnode[node]++;

        if (freqnode[node] == 2) {
            freqcolor[c]++;   //say initially frequency of this color was 5 now it's 6 so update freqoffreq as per this now
            freqoffreq[freqcolor[c]]++;
            bucket[get(freqcolor[c])]++;
            freqoffreq[freqcolor[c] - 1]--;
            bucket[get(freqcolor[c] - 1)]--;
        }
    }

    public static void remove(int pos) {
        int node = flatten[pos];
        int c = color[node];
        freqnode[node]--;

        if (freqnode[node] == 1) {
            freqcolor[c]--;
            freqoffreq[freqcolor[c]]++;
            bucket[get(freqcolor[c])]++;  //getting the block of this freqcolor in freqoffreq array and updating it in bucket array
            freqoffreq[freqcolor[c] + 1]--;
            bucket[get(freqcolor[c] + 1)]--; //getting the block of this freqcolor in freqoffreq array and updaing it in bucket array
        }
    }

    public static int find(int C, int N) {
        int LB = get(C);
        int RB = get(N);  //going till N since freq of any color can be atmax N since N nodes are there in a tree
        int i;
        int ans = 0;
        if (LB == RB) {
            for (i = C; i <= N; i++)
                ans += freqoffreq[i];
        } else {
            for (i = C; i <= LB * (BLOCK); i++)
                ans += freqoffreq[i];

            for (i = LB + 1; i < RB; i++)
                ans += bucket[i];


            for (i = (RB) * (BLOCK) + 1; i <= N; i++)
                ans += freqoffreq[i];
        }
        return ans;
    }

    public static int get(int index) {
        return ((index + BLOCK - 1) / BLOCK);  // if block size is 10 then elements from 1 to 10 in block 1, 11-20 in block 2....
        // eg for 32= (32+10-1)/10= 4th block
    }

    static class Query {
        int l, r, id, c;

        Query(int L, int R, int ID, int C) {
            l = L;
            r = R;
            id = ID;
            c = C;
        }
    }

    static class myComparator implements Comparator<Query> {
        public int compare(Query A, Query B) {
            if (A.l / BLOCK != B.l / BLOCK)
                return A.l - B.l;
            else if ((A.l / BLOCK) % 2 == 0)
                return A.r - B.r;
            else
                return B.r - A.r;
        }
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
