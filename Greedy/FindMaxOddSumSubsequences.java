package Greedy;

/*
Find max odd sum subsequence.
eg-
4
-2 2 -3 1
ans-
3
*/


/*
Hint- dp or greedy
The answer to this problem can be constructed this way:

Sum up all positive numbers
Find maximum maxodd of negative odd numbers
Find minimum minodd of positive odd numbers
If sum was even then subtract min(minodd,  - maxodd)
Overall complexity: O(n).

or DP as done below
*/

import java.util.Arrays;
import java.util.Scanner;

public class FindMaxOddSumSubsequences {
    static int N;
    static int A[];
    static long dp[][];

    public static void main(String ag[]) throws Exception {
        int i, j, k, kk;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        A = new int[N];
        dp = new long[N][2];
        for (i = 0; i < N; i++)
            A[i] = sc.nextInt();

        for (i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);

        System.out.println(find(0, 1));
    }

    public static long find(int id, int parity) {
        if (id == N && parity == 0)
            return 0;

        if (id == N)
            return Integer.MIN_VALUE;

        if (dp[id][parity] != -1)
            return dp[id][parity];

        long a = A[id] + find(id + 1, parity ^ (A[id] & 1));
        long b = find(id + 1, parity);

        return dp[id][parity] = Math.max(a, b);
    }
}
