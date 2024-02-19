package SquareRootDecomposition;

/*
You are given the following:

An integer m
An array a1, a2.... an
Integers from 1 to m.
For every number c in range [1:m] , you know its happiness is Hc. A subarray is called happy, if for every number c in this subarray,
the occurrence of c is equal to Hc.

You are given Q queries. The  query consists of two numbers l & r. Your task is to determine if subarray a(l),a(l+1),....a(r) is happy or not.
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MOsAlgoToFindHappySegments {
    public static void main(String ag[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        //br.readLine();

        int i, j, k;
        String str[] = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int freq[] = new int[5 * ((int) 1e5 + 1)];

        int A[] = new int[N + 1];
        String arr[] = br.readLine().split(" ");
        for (i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(arr[i - 1]);
        }

        int hs[] = new int[M + 1];
        String brr[] = br.readLine().split(" ");
        for (i = 1; i <= M; i++) {
            hs[i] = Integer.parseInt(brr[i - 1]);
        }

        int query = Integer.parseInt(br.readLine());
        int BLOCK = (int) Math.sqrt(query);
        ArrayList<Query> q = new ArrayList<>();
        for (i = 0; i < query; i++) {
            String G[] = br.readLine().split(" ");
            int L = Integer.parseInt(G[0]);
            int R = Integer.parseInt(G[1]);
            q.add(new Query(L, R, i + 1));
        }
        Collections.sort(q, new Tcomp(BLOCK));

        int ANS[] = new int[query + 1];
        int mL = 1;
        int mR = 0;
        for (i = 0; i < query; i++) {
            int l = q.get(i).L;
            int r = q.get(i).R;
            int id = q.get(i).id;

            while (mL > l) {
                mL--;
                freq[A[mL]]++;
            }
            while (mR < r) {
                mR++;
                freq[A[mR]]++;
            }
            while (mL < l) {
                freq[A[mL]]--;
                mL++;
            }
            while (mR > r) {
                freq[A[mR]]--;
                mR--;
            }
            boolean flag = true;
            for (j = l; j <= r; j++) {
                if (freq[A[j]] != hs[A[j]]) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                ANS[id] = 1;
        }

        for (i = 1; i <= query; i++) {
            bw.write(ANS[i] + "\n");
        }
        bw.flush();
    }
}

class Query {
    int L, R, id;

    Query(int l, int r, int i) {
        L = l;
        R = r;
        id = i;
    }

}

class Tcomp implements Comparator<Query> {
    int BLOCK;

    Tcomp(int b) {
        BLOCK = b;
    }

    public int compare(Query o1, Query o2) {
        if (o1.L / BLOCK != o2.L / BLOCK)
            return (o1.L < o2.L ? -1 : 1);
        return (o1.R < o2.R ? -1 : 1);
    }
}