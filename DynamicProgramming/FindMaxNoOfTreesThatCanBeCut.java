package DynamicProgramming;

/*
Find max trees that can be cut-
There are n trees located along the road at points with coordinates x1, x2, ..., xn. Each tree has its height hi. Woodcutters can cut down a tree and fell it to the left or to the right. After that it occupies one of the segments [xi - hi, xi] or [xi;xi + hi]. The tree that is not cut down occupies a single point with coordinate xi. Woodcutters can fell a tree if the segment to be occupied by the fallen tree doesn't contain any occupied point. The woodcutters want to process as many trees as possible, so Susie wonders, what is the maximum number of trees to fell.

eg-
5
1 2 (given are X coordinates and height)
2 1
5 10
10 9
19 1
ans-
3
*/

import java.util.Arrays;
import java.util.Scanner;

public class FindMaxNoOfTreesThatCanBeCut {
    static int H[];
    static int X[];
    static int N;
    static long dp[][];

    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        N = sc.nextInt();
        X = new int[N + 1];
        H = new int[N + 1];
        dp = new long[N + 1][4];
        for (i = 1; i <= N; i++) {
            X[i] = sc.nextInt();
            H[i] = sc.nextInt();
        }

        for (i = 0; i <= N; i++)
            Arrays.fill(dp[i], -1);

        System.out.println(find(1, 0, 0));
    }

    public static long find(int id, int state, int dist) {
        //  state--> 1>> left , 2>> stand still , 3>> right

        if (id == N + 1)
            return 0;

        if (dp[id][state] != -1)
            return dp[id][state];

        long A, B, C;
        A = B = C = 0;

        if (id == 1 || (X[id] - H[id] > X[id - 1] && X[id] - H[id] > dist))
            A = 1 + find(id + 1, 1, X[id]);
        else
            A = find(id + 1, 2, X[id]);

        B = find(id + 1, 2, X[id]);

        if (id == N || (X[id] + H[id] < X[id + 1]))
            C = 1 + find(id + 1, 3, X[id] + H[id]);
        else
            C = find(id + 1, 2, X[id]);

        return dp[id][state] = Math.max(A, Math.max(B, C));
    }
}
