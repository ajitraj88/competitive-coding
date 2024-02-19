package DynamicProgramming;

/*
Santa Claus has n candies, he dreams to give them as gifts to children.

What is the maximal number of children for whose he can give candies if Santa Claus want each kid should get distinct positive integer number of candies. Santa Class wants to give all n candies he has.


Print to the first line integer number k — maximal number of kids which can get candies.

Print to the second line k distinct integer numbers: number of candies for each of k kid. The sum of k printed numbers should be exactly n.

If there are many solutions, print any of them.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MaximumNoOfChildrenToGiveNCandiesSoThatNoCandyIsLeftTracingDpToo {
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        long dp[][] = new long[1005][1005];
        for (i = 0; i <= 1004; i++)
            Arrays.fill(dp[i], -1);
        System.out.println(find(dp, 1, N));
        StringBuilder ans = new StringBuilder();
        i = 1;

        while (i <= N) {
            if (dp[i][N] == dp[i + 1][N - i] + 1) {
                ans.append(i + " ");
                N -= i;
                i++;
            } else {
                i++;
            }
        }
        System.out.println(ans);
    }

    public static long find(long dp[][], int num, int sum) {
        if (sum == 0)
            return dp[num][sum] = 0;

        if (sum < num)
            return -Integer.MIN_VALUE;

        if (dp[num][sum] != -1)
            return dp[num][sum];

        return dp[num][sum] = Math.max(1 + find(dp, num + 1, sum - num), find(dp, num + 1, sum));

    }
}
