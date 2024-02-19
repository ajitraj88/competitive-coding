package SegmentTrees;

/*
Ant fight, given strength of each ant ,,, ant in range L to R fight ANTi win iff it divides ANTj's strength;;;;ant in this range survives if it gets points
equal to (R-L)
Print in each query how many ants survive

eg---- 	5
		1 3 2 4 2
		4
		1 5
		2 5
		3 5
		4 5

ans---- 4
		4
		1
		1
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AntsFightInRangeLToR {
    public static void main(String ag[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i, j, k;
        int N = Integer.parseInt(br.readLine());
        long A[] = new long[N + 1];
        String str[] = br.readLine().split(" ");
        for (i = 1; i <= N; i++) {
            A[i] = Long.parseLong(str[i - 1]);
        }
        segmentTree s = new segmentTree(N, A);

        int Q = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        while (Q-- > 0) {
            String arr[] = br.readLine().split(" ");
            int L = Integer.parseInt(arr[0]);
            int R = Integer.parseInt(arr[1]);
            ans.append(R - L + 1 - s.query(1, N, 1, L, R).freq + "\n");
        }
        System.out.println(ans);
    }
}

class segmentTree {
    Pair ST[];
    int N;

    segmentTree(int N, long A[]) {
        this.N = N;
        ST = new Pair[4 * (N)];
        for (int i = 0; i < 4 * N; i++) {
            ST[i] = new Pair(0, 0);
        }
        build(1, N, A, 1);
    }

    public void build(int l, int r, long A[], int pos) {
        if (l == r) {
            ST[pos].gcd = A[l];
            ST[pos].freq = 1;
        } else {
            int mid = (l + r) >> 1;
            build(l, mid, A, 2 * pos);
            build(mid + 1, r, A, 2 * pos + 1);
            long X = GCD(ST[2 * pos].gcd, ST[2 * pos + 1].gcd);
            ST[pos].gcd = X;

            if (ST[2 * pos].gcd == X)
                ST[pos].freq += ST[2 * pos].freq;

            if (ST[2 * pos + 1].gcd == X)
                ST[pos].freq += ST[2 * pos + 1].freq;
        }
    }

    public Pair query(int l, int r, int pos, int qs, int qe) {
        if (l > r || r < qs || l > qe)
            return new Pair(0, 0);

        if (l >= qs && r <= qe)
            return ST[pos];

        int mid = (l + r) >> 1;
        Pair P1 = query(l, mid, 2 * pos, qs, qe);
        Pair P2 = query(mid + 1, r, 2 * pos + 1, qs, qe);

        long X = GCD(P1.gcd, P2.gcd);
        int count = 0;
        if (X == P1.gcd)
            count += P1.freq;
        if (X == P2.gcd)
            count += P2.freq;

        return new Pair(X, count);
    }

    public long GCD(long a, long b) {
        if (b == 0)
            return a;
        return GCD(b, a % b);
    }
}

class Pair {
    long gcd;
    int freq;

    Pair(long g, int f) {
        gcd = g;
        freq = f;
    }
}

