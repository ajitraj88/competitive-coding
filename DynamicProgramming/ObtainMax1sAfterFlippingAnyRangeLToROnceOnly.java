package DynamicProgramming;

/*
Iahub got bored, so he invented a game to be played on paper.

He writes n integers a1, a2, ..., an. Each of those integers can be either 0 or 1. He's allowed to do exactly one move: he chooses two indices i and j (1 ≤ i ≤ j ≤ n) and flips all values ak for which their positions are in range [i, j] (that is i ≤ k ≤ j). Flip the value of x means to apply operation x = 1 - x.

The goal of the game is that after exactly one move to obtain the maximum number of ones. Write a program to solve the little game of Iahub.

*/

import java.util.ArrayList;
import java.util.Scanner;

public class ObtainMax1sAfterFlippingAnyRangeLToROnceOnly {
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int A[] = new int[N];
        int cnt = 0;
        for (i = 0; i < N; i++) {
            int X = sc.nextInt();
            if (X == 1) {
                cnt++;
                A[i] = -1;
            } else {
                A[i] = 1;
            }
        }
        int dp[] = new int[N];
        dp[0] = A[0];
        int max = A[0];

        for (i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i - 1] + A[i], A[i]);
            max = Math.max(dp[i], max);
        }
        System.out.println(cnt + max);

    }
}
