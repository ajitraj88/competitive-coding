package SparseTable;

/*
An army of n droids is lined up in one row. Each droid is described by m integers a 1, a 2, ..., a m, where a i is the number of details of the i-th type in this droid's mechanism. R2-D2 wants to destroy the sequence of consecutive droids of maximum length. He has m weapons, the i-th weapon can affect all the droids in the army by destroying one detail of the i-th type (if the droid doesn't have details of this type, nothing happens to it).

A droid is considered to be destroyed when all of its details are destroyed. R2-D2 can make at most k shots. How many shots from the weapon of what type should R2-D2 make to destroy the sequence of consecutive droids of maximum length?

Print m space-separated integers, where the i-th number is the number of shots from the weapon of the i-th type that the robot should make to destroy the subsequence of consecutive droids of the maximum length.

If there are multiple optimal solutions, print any of them.

It is not necessary to make exactly k shots, the number of shots can be less.

eg---- 5 2 4
		4 0
		1 2
		2 1
		0 2
		1 3

ans--- 2 2
*/

import java.util.ArrayList;
import java.util.Scanner;

public class MaximumConsecutiveKill {
    public static void main(String ag[]) {
        int i, j, k;
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        sparseTable arr[] = new sparseTable[M];
        long A[][] = new long[M][N];

        for (i = 0; i < N; i++) {
            for (j = 0; j < M; j++) {
                A[j][i] = sc.nextLong();
            }
        }
        for (i = 0; i < M; i++) {
            arr[i] = new sparseTable(N, A[i]);
        }

        ArrayList<Long> ans = new ArrayList<>();
        int l = 0;
        int r = 0;
        while (l <= r && r < N) {
            long total = 0;
            for (i = 0; i < M; i++)
                total += arr[i].query(l, r);

            if (total <= K) {
                ans = new ArrayList<>(M);
                for (i = 0; i < M; i++) {
                    ans.add(arr[i].query(l, r));
                }
                r++;
            } else {
                l++;
                r++;
            }
        }

        if (ans.size() == 0) {
            for (i = 0; i < M; i++)
                System.out.print(0 + " ");
        } else {
            for (i = 0; i < M; i++)
                System.out.print(ans.get(i) + " ");
        }
    }
}

class sparseTable {
    int LOG;
    long ST[][];
    int log[];

    sparseTable(int N, long A[]) {
        LOG = (int) Math.floor(Math.log(N) / Math.log(2)) + 1;
        ST = new long[N][LOG];
        log = new int[N + 1];
        createTable(A, N);
        findLOG(N);
    }

    public void createTable(long A[], int N) {
        int i, j, k;
        for (i = 0; i < N; i++)
            ST[i][0] = A[i];

        for (j = 1; j <= LOG; j++) {
            for (i = 0; i + (1 << j) <= N; i++)
                ST[i][j] = Math.max(ST[i][j - 1], ST[i + (1 << (j - 1))][j - 1]);
        }
    }

    public long query(int l, int r) {
        int X = log[r - l + 1];
        return Math.max(ST[l][X], ST[r - (1 << X) + 1][X]);
    }

    public void findLOG(int N) {
        log[1] = 0;
        for (int i = 2; i <= N; i++)
            log[i] = log[i / 2] + 1;
    }
}
