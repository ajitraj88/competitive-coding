package DynamicProgramming;

/*
Vasya has n days of vacations! So he decided to improve his IT skills and do sport. Vasya knows the following information about each of this n days: whether that gym opened and whether a contest was carried out in the Internet on that day. For the i-th day there are four options:

1.on this day the gym is closed and the contest is not carried out;
2.on this day the gym is closed and the contest is carried out;
3.on this day the gym is open and the contest is not carried out;
4.on this day the gym is open and the contest is carried out.
On each of days Vasya can either have a rest or write the contest (if it is carried out on this day), or do sport (if the gym is open on this day).

Find the minimum number of days on which Vasya will have a rest (it means, he will not do sport and write the contest at the same time). The only limitation that Vasya has — he does not want to do the same activity on two consecutive days: it means, he will not do sport on two consecutive days, and write the contest on two consecutive days.

The first line contains a positive integer n (1 ≤ n ≤ 100) — the number of days of Vasya's vacations.

The second line contains the sequence of integers a1, a2, ..., an (0 ≤ ai ≤ 3) separated by space, where:

ai equals 0, if on the i-th day of vacations the gym is closed and the contest is not carried out;
ai equals 1, if on the i-th day of vacations the gym is closed, but the contest is carried out;
ai equals 2, if on the i-th day of vacations the gym is open and the contest is not carried out;
ai equals 3, if on the i-th day of vacations the gym is open and the contest is carried out.

eg---- 	4
		1 3 2 0
 ans---- 2
*/

import java.util.Scanner;

public class FindMinDaysInWhichVasyaWillBeIdle {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int A[] = new int[N];
        for (i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        int dp[][][] = new int[N + 1][5][3];

        for (i = 0; i < N; i++)
            for (j = 0; j < 5; j++)
                for (k = 0; k < 3; k++)
                    dp[i][j][k] = -1;

        System.out.println(N - find(dp, A, 0, 4, 0, N));
    }

    public static int find(int dp[][][], int A[], int id, int prev, int state, int N) {
        if (id == N)
            return 0;

        if (dp[id][prev][state] != -1)
            return dp[id][prev][state];

        // state-->  1 >> gym   2 >> contest
        if (A[id] == 0)
            dp[id][prev][state] = Math.max(dp[id][prev][state], find(dp, A, id + 1, 4, 0, N));

        if (A[id] == 1 && prev != A[id] && state != 2)
            dp[id][prev][state] = Math.max(dp[id][prev][state], 1 + find(dp, A, id + 1, 1, 2, N));
        if (A[id] == 1)
            dp[id][prev][state] = Math.max(dp[id][prev][state], find(dp, A, id + 1, 4, 0, N));

        if (A[id] == 2 && prev != A[id] && state != 1)
            dp[id][prev][state] = Math.max(dp[id][prev][state], 1 + find(dp, A, id + 1, 2, 1, N));
        if (A[id] == 2)
            dp[id][prev][state] = Math.max(dp[id][prev][state], find(dp, A, id + 1, 4, 0, N));

        if (A[id] == 3 && state != 1)
            dp[id][prev][state] = Math.max(dp[id][prev][state], 1 + find(dp, A, id + 1, 3, 1, N));
        if (A[id] == 3 && state != 2)
            dp[id][prev][state] = Math.max(dp[id][prev][state], 1 + find(dp, A, id + 1, 3, 2, N));

        return dp[id][prev][state];
    }
}
