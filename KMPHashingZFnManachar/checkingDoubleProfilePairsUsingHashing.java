package KMPHashingZFnManachar;

/*
The social network contains n registered profiles, numbered from 1 to n. Some pairs there are friends (the "friendship" relationship is mutual, that is, if i is friends with j, then j is also friends with i). Let's say that profiles i and j ( i ≠ j) are doubles, if for any profile k ( k ≠ i, k ≠ j) one of the two statements is true: either k is friends with i and j, or k isn't friends with either of them. Also, i and j can be friends or not be friends.

Your task is to count the number of different unordered pairs ( i, j), such that the profiles i and j are doubles. Note that the pairs are unordered, that is, pairs ( a, b) and ( b, a) are considered identical.

eg----  4 1
		1 3

o/p---- 2     the doubles are pairs of profiles (1, 3) and (2, 4).
*/

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class checkingDoubleProfilePairsUsingHashing {
    static long power[];
    static long MOD = 13583908434479L;

    public static void main(String ag[]) throws Exception {
        Reader sc = new Reader();
        int i, j, k;
        int N = sc.nextInt();
        int M = sc.nextInt();
        ArrayList<Integer> adj[] = new ArrayList[N + 1];

        power = new long[N + 1];
        power[0] = 1L;
        for (i = 1; i <= N; i++) {
            power[i] = (N * power[i - 1]) % MOD;
        }

        for (i = 0; i <= N; i++)
            adj[i] = new ArrayList<>();

        for (i = 1; i <= M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }

        HashMap<Long, Integer> map = new HashMap<>();
        long cnt = 0;
        long ans = 0;
        for (i = 1; i <= N; i++) {
            if (adj[i].size() == 0)
                cnt++;
        }
        ans = ((cnt) * (cnt - 1)) / 2;

        for (i = 1; i <= N; i++) {
            if (adj[i].size() == 0)
                continue;

            Collections.sort(adj[i]);
            long hash = findHash(adj[i]);
            if (!map.containsKey(hash))
                map.put(hash, 1);
            else
                map.put(hash, map.get(hash) + 1);
        }

        for (Long E : map.keySet()) {
            long e = map.get(E);
            ans += ((e) * (e - 1)) / 2;
        }

        map.clear();
        for (i = 1; i <= N; i++) {
            adj[i].add(i);
            if (adj[i].size() == 1)
                continue;

            Collections.sort(adj[i]);
            long hash = findHash(adj[i]);
            if (!map.containsKey(hash))
                map.put(hash, 1);
            else
                map.put(hash, map.get(hash) + 1);
        }

        for (Long E : map.keySet()) {
            long e = map.get(E);
            ans += ((e) * (e - 1)) / 2;
        }
        System.out.println(ans);
    }

    public static long findHash(ArrayList<Integer> ts) {
        long hash = 0;
        for (int i = 0; i < ts.size(); i++) {
            hash += (power[ts.get(i)]) % MOD;
        }
        return hash;
    }
}

class Reader {
    final private int BUFFER_SIZE = 1 << 16;
    private final DataInputStream din;
    private final byte[] buffer;
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
