package DynamicProgramming;

/*
You are given array consisting of n integers. Your task is to find the maximum length of an increasing subarray of the given array.

A subarray is the sequence of consecutive elements of the array. Subarray is called increasing if each element of this subarray strictly greater than previous.
*/

import java.util.Scanner;

public class MaximumIncreasingSubarray {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int A[] = new int[N];
        for (i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        int dp[] = new int[N];
        dp[0] = 1;
        int ans = 1;
        ;
        for (i = 1; i < N; i++) {
            if (A[i] > A[i - 1])
                dp[i] = dp[i - 1] + 1;
            else
                dp[i] = 1;
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
