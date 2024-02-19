package DynamicProgramming;

/*
A frog lives on the axis Ox and needs to reach home which is in the point n. She starts from the point 1. The frog can jump to the right at a distance not more than d. So, after she jumped from the point x she can reach the point x + a, where a is an integer from 1 to d.

For each point from 1 to n is known if there is a lily flower in it. The frog can jump only in points with a lilies. Guaranteed that there are lilies in the points 1 and n.

Determine the minimal number of jumps that the frog needs to reach home which is in the point n from the point 1. Consider that initially the frog is in the point 1. If the frog can not reach home, print -1.

*/

import java.util.Arrays;
import java.util.Scanner;

public class MinimumJumpsToReachNNotJumpingMoreThanDInAnyJump {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int D = sc.nextInt();
        char str[] = sc.next().toCharArray();
        int dp[] = new int[N];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (i = 1; i < N; i++) {
            if (str[i] == '1') {
                int X = 9999;
                for (j = i - 1; j >= Math.max(0, i - D); j--) {
                    if (str[j] == '1' && dp[j] != -1)
                        X = Math.min(X, dp[j] + 1);
                }
                if (X != 9999)
                    dp[i] = X;
            }
        }
        System.out.println(dp[N - 1]);
    }
}
