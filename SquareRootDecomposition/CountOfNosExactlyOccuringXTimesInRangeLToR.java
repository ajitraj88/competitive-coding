package SquareRootDecomposition;

/*
The Little Elephant loves playing with arrays. He has array a, consisting of n positive integers, indexed from 1 to n. Let's denote the number with index i as a i.

Additionally the Little Elephant has m queries to the array, each query is characterised by a pair of integers lj and rj (1 ≤ l j ≤ r j ≤ n). For each query L j, Rj the Little Elephant has to count, how many numbers x exist, such that number x occurs exactly x times among numbers a Lj, a Lj+1, ..., a Rj.

Help the Little Elephant to count the answers to all queries.

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class CountOfNosExactlyOccuringXTimesInRangeLToR {
    static int ans = 0;
    static int BLOCK;
    static int freq[] = new int[(int) 1e5 + 1];
    static int A[];

    public static void main(String ag[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i, j, k;
        String str[] = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        BLOCK = (int) Math.sqrt(N);
        A = new int[N + 1];
        String arr[] = br.readLine().split(" ");
        for (i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(arr[i - 1]);
        }

        Query q[] = new Query[M];
        for (i = 0; i < M; i++) {
            String G[] = br.readLine().split(" ");
            int L = Integer.parseInt(G[0]);
            int R = Integer.parseInt(G[1]);
            q[i] = new Query(L, R, i + 1);
        }
        Arrays.sort(q, new Tcomp());

        int ANS[] = new int[M + 1];
        int mL = 1;
        int mR = 0;
        for (i = 0; i < M; i++) {
            int l = q[i].L;
            int r = q[i].R;
            int id = q[i].id;

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

            ANS[id] = ans;
        }

        for (i = 1; i <= M; i++) {
            System.out.println(ANS[i]);
        }
    }

    public static void add(int pos) {
        if (A[pos] >= (int) 1e5 + 1)
            return;
        if (freq[A[pos]] == A[pos])
            ans--;
        freq[A[pos]]++;
        if (freq[A[pos]] == A[pos])
            ans++;
    }

    public static void remove(int pos) {
        if (A[pos] >= (int) 1e5 + 1)
            return;
        if (freq[A[pos]] == A[pos])
            ans--;
        freq[A[pos]]--;
        if (freq[A[pos]] == A[pos])
            ans++;
    }

    static class Query {
        int L, R, id;

        Query(int l, int r, int i) {
            L = l;
            R = r;
            id = i;
        }

    }

    static class Tcomp implements Comparator<Query> {
        public int compare(Query q1, Query q2) {
            if (q1.L / BLOCK != q2.L / BLOCK)
                return q1.L - q2.L;
            return q1.R - q2.R;
        }
    }
}
