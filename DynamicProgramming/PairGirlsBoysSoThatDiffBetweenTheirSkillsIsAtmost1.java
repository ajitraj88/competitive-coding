package DynamicProgramming;

/*
We know that several boy&girl pairs are going to be invited to the ball. However, the partners' dancing skill in each pair must differ by at most one.

For each boy, we know his dancing skills. Similarly, for each girl we know her dancing skills. Write a code that can determine the largest possible number of pairs that can be formed from n boys and m girls.
*/

import java.util.Arrays;
import java.util.Scanner;

public class PairGirlsBoysSoThatDiffBetweenTheirSkillsIsAtmost1 {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;

        int N = sc.nextInt();
        int B[] = new int[N];
        for (i = 0; i < N; i++)
            B[i] = sc.nextInt();

        int M = sc.nextInt();
        int G[] = new int[M];
        for (i = 0; i < M; i++)
            G[i] = sc.nextInt();

        Arrays.sort(G);
        Arrays.sort(B);
        int dp[][] = new int[N][M];
        for (i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);

        System.out.println(find(dp, 0, 0, B, G, N, M));
    }

    public static int find(int dp[][], int i, int j, int B[], int G[], int N, int M) {
        if (i == N || j == M)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        if (Math.abs(B[i] - G[j]) <= 1)
            dp[i][j] = Math.max(dp[i][j], 1 + find(dp, i + 1, j + 1, B, G, N, M));

        dp[i][j] = Math.max(dp[i][j], find(dp, i, j + 1, B, G, N, M));
        dp[i][j] = Math.max(dp[i][j], find(dp, i + 1, j, B, G, N, M));
        return dp[i][j];
    }
}

//bottom-up
class Solution {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;

        int N = sc.nextInt();
        int B[] = new int[N];
        for (i = 0; i < N; i++)
            B[i] = sc.nextInt();

        int M = sc.nextInt();
        int G[] = new int[M];
        for (i = 0; i < M; i++)
            G[i] = sc.nextInt();

        Arrays.sort(G);
        Arrays.sort(B);
        int dp[][] = new int[N + 1][M + 1];

        for (i = 1; i <= N; i++) {
            for (j = 1; j <= M; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (Math.abs(B[i - 1] - G[j - 1]) <= 1)
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
            }
        }
        System.out.println(dp[N][M]);
    }

}