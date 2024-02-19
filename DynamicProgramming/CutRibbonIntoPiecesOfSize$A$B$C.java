package DynamicProgramming;

/*
Polycarpus has a ribbon, its length is n. He wants to cut the ribbon in a way that fulfils the following two conditions:

After the cutting each ribbon piece should have length a, b or c.
After the cutting the number of ribbon pieces should be maximum.
Help Polycarpus and find the number of ribbon pieces after the required cutting.

eg---- 5 5 3 2
ans---- 2

*/

import java.util.Arrays;
import java.util.Scanner;

public class CutRibbonIntoPiecesOfSize$A$B$C {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int dp[][] = new int[N + 1][4];
        for (i = 0; i <= N; i++)
            Arrays.fill(dp[i], -1);

        int X = find(N, a, b, c, dp, 0);
        System.out.println(X > 0 ? X : 0);
    }

    public static int find(int len, int a, int b, int c, int dp[][], int prev) {
        if (len == 0)
            return 0;

        if (len < 0)
            return -99999;

        if (dp[len][prev] != -1)
            return dp[len][prev];

        return dp[len][prev] = 1 + max(find(len - a, a, b, c, dp, 1), find(len - b, a, b, c, dp, 2), find(len - c, a, b, c, dp, 3));
    }

    public static int max(int x, int y, int z) {
        if (x >= y && x >= z)
            return x;
        else if (y >= x && y >= z)
            return y;
        else
            return z;
    }
}
