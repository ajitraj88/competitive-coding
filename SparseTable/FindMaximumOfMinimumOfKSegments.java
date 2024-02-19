package SparseTable;

/*
You are given an array a 1, a 2, ..., a n consisting of n integers, and an integer k. You have to split the array into exactly k non-empty subsegments. You'll then compute the minimum integer on each subsegment, and take the maximum integer over the k obtained minimums. What is the maximum possible integer you can get?

eg---- 5 2
	   1 2 3 4 5
ans---- 5
*/

import java.util.Scanner;

public class FindMaximumOfMinimumOfKSegments {
    static int LOG;
    static int sparseTable[][];
    static int log[];

    public static void main(String ag[]) {
        int i, j, k;
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int A[] = new int[N];

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (i = 0; i < N; i++) {
            A[i] = sc.nextInt();
            max = Math.max(max, A[i]);
            min = Math.min(min, A[i]);
        }

        int ans = Integer.MIN_VALUE;
        if (K == 1) {
            ans = min;
        } else if (K == 2) {
            LOG = (int) Math.floor(Math.log(N) / Math.log(2)) + 1;
            sparseTable = new int[N][LOG];
            log = new int[N + 1];
            createTable(A, N);
            findLOG(N);

            for (i = 0; i < N - 1; i++) {
                int find = Math.max(query(0, i), query(i + 1, N - 1));
                ans = Math.max(ans, find);
            }
        } else {
            ans = max;
        }
        System.out.println(ans);
    }

    public static void createTable(int A[], int N) {
        int i, j, k;
        for (i = 0; i < N; i++)
            sparseTable[i][0] = A[i];

        for (j = 1; j <= LOG; j++) {
            for (i = 0; i + (1 << j) - 1 < N; i++)
                sparseTable[i][j] = Math.min(sparseTable[i][j - 1], sparseTable[i + (1 << (j - 1))][j - 1]);
        }
    }

    public static void findLOG(int N) {
        log[1] = 0;
        for (int i = 2; i <= N; i++)
            log[i] = log[i / 2] + 1;

    }

    public static int query(int l, int r) {
        int X = log[r - l + 1];
        return Math.min(sparseTable[l][X], sparseTable[r - (1 << X) + 1][X]);
    }
}
