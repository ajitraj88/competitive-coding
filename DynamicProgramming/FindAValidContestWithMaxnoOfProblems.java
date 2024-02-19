package DynamicProgramming;

/*
You are given a problemset consisting of n problems. The difficulty of the i-th problem is ai. It is guaranteed that all difficulties are distinct and are given in the increasing order.

You have to assemble the contest which consists of some problems of the given problemset. In other words, the contest you have to assemble should be a subset of problems (not necessary consecutive) of the given problemset. There is only one condition that should be satisfied: for each problem but the hardest one (the problem with the maximum difficulty) there should be a problem with the difficulty greater than the difficulty of this problem but not greater than twice the difficulty of this problem. In other words, let ai1,ai2,…,aip be the difficulties of the selected problems in increasing order. Then for each j from 1 to p−1 aij+1≤aij⋅2 should hold. It means that the contest consisting of only one problem is always valid.

Among all contests satisfying the condition above you have to assemble one with the maximum number of problems. Your task is to find this number of problems.
*/

import java.util.Scanner;

public class FindAValidContestWithMaxnoOfProblems {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int A[] = new int[N];
        for (i = 0; i < N; i++)
            A[i] = sc.nextInt();

        int dp[] = new int[N];
        dp[0] = 1;
        int max = 1;
        for (i = 1; i < N; i++) {
            if (A[i - 1] * 2 >= A[i])
                dp[i] = dp[i - 1] + 1;
            else
                dp[i] = 1;
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}
