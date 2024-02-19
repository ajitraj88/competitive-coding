package DynamicProgramming;

/*
Find max no of goods that Vyasa can buy if she can buy K goods for the price of one by paying the price of max. good and initially she has P coins.
eg-
8(t)
5(N) 6(P) 2(K)
2 4 3 5 7
5 11 2
2 4 3 5 7
3 2 3
4 2 6
5 2 3
10 1 3 9 2
2 10000 2
10000 10000
2 9999 2
10000 10000
4 6 4
3 2 3 2
5 5 3
1 2 2 1 2

ans-
3
4
1
1
2
0
4
5

*/

import java.util.Arrays;
import java.util.Scanner;

public class KGoodsForThePriceOfOne {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            long P = sc.nextLong();
            int K = sc.nextInt();

            long A[] = new long[N];
            for (i = 0; i < N; i++)
                A[i] = sc.nextLong();

            Arrays.sort(A);
            long dp[] = new long[N];

            dp[0] = A[0];
            for (i = 1; i < K - 1; i++)
                dp[i] = dp[i - 1] + A[i];
            dp[K - 1] = A[K - 1];

            for (i = K; i < N; i++)
                dp[i] = dp[i - K] + A[i];

            long max = 0;
            for (i = 0; i < N; i++) {
                if (P >= dp[i])
                    max = i + 1;
            }
            System.out.println(max);
        }
    }
}
