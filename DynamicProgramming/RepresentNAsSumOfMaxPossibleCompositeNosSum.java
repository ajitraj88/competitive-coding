package DynamicProgramming;

/*
You are given several queries. In the i-th query you are given a single positive integer ni. You are to represent ni as a sum of maximum possible number of composite summands and print this maximum number, or print -1, if there are no such splittings.

An integer greater than 1 is composite, if it is not prime, i.e. if it has positive divisors not equal to 1 and the integer itself

*/

import java.util.Arrays;
import java.util.Scanner;

public class RepresentNAsSumOfMaxPossibleCompositeNosSum {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int Q = sc.nextInt();

        int A[] = {4, 6, 9};
        int dp[] = new int[17];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i <= 16; i++) {
            for (int j = 0; j < 3; j++) {
                if (i - A[j] >= 0 && dp[i - A[j]] != -1)
                    dp[i] = Math.max(dp[i], dp[i - A[j]] + 1);
            }
        }

        while (Q-- > 0) {
            int N = sc.nextInt();
            if (N <= 16) {
                System.out.println(dp[N]);
                continue;
            }

            int ans = 0;
            int X = (N - 16) / 4 + 1;
            ans += X;
            ans += dp[N - 4 * (X)];
            System.out.println(ans);
        }
    }
}

class sparseTable {
    int LOG;
    long ST[][];
    int log[];

    sparseTable(long A[], int N) {
        LOG = (int) Math.floor(Math.log(N) / Math.log(2)) + 1;
        ST = new long[N][LOG];
        log = new int[N + 1];
        createTable(A, N);
        findLOG(N);
    }

    public void createTable(long A[], int N) {
        int i, j, k;
        for (i = 0; i < N; i++)
            ST[i][0] = A[i];

        for (j = 1; j <= LOG; j++) {
            for (i = 0; i + (1 << j) <= N; i++)
                ST[i][j] = gcd(ST[i][j - 1], ST[i + (1 << (j - 1))][j - 1]);
        }
    }

    public long query(int l, int r) {
        int X = log[r - l + 1];
        return gcd(ST[l][X], ST[r - (1 << X) + 1][X]);
    }

    public void findLOG(int N) {
        log[1] = 0;
        for (int i = 2; i <= N; i++)
            log[i] = log[i / 2] + 1;
    }

    public long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}
