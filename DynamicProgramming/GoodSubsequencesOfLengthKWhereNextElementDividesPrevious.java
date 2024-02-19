package DynamicProgramming;

/*
A sequence of l integers b1, b2, ..., bl (1 ≤ b1 ≤ b2 ≤ ... ≤ bl ≤ n) is called good if each number divides (without a remainder) by the next number in the sequence. More formally b(i)|b(i+1) for all i (1 ≤ i ≤ l - 1).

Given n and k find the number of good sequences of length k. As the answer can be rather large print it modulo 1000000007 (109 + 7)

eg---- 3 2
ans---- 5 as : [1, 1], [2, 2], [3, 3], [1, 2], [1, 3]

*/

import java.util.Arrays;
import java.util.Scanner;

public class GoodSubsequencesOfLengthKWhereNextElementDividesPrevious {
    static int MOD = (int) 1e9 + 7;
    static long dp[][];
    static int N;

    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        N = sc.nextInt();
        int K = sc.nextInt();
        dp = new long[N + 1][K + 1];
        for (i = 0; i <= N; i++)
            Arrays.fill(dp[i], -1);

        System.out.println(find(1, K));

    }

    public static long find(int prev, int count) {
        if (count == 0)
            return 1;

        if (dp[prev][count] != -1)
            return dp[prev][count];

        long total = 0;
        for (int i = prev; i <= N; i += prev) {
            total += find(i, count - 1) % MOD;
            if (total >= MOD)
                total %= MOD;
        }
        return dp[prev][count] = total;
    }
}
