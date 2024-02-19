package DynamicProgramming;

/*
Find max sum that can be get if you select ak then you delete a(k-1) & a(k+1).
*/

import java.util.HashMap;
import java.util.Scanner;

public class FindMaxSumObtainedIfYouSelectAkThenCantSelectAkMinus1AndAkPlus1 {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int A[] = new int[N];
        for (i = 0; i < N; i++)
            A[i] = sc.nextInt();

        HashMap<Integer, Long> map = new HashMap<>();
        for (i = 0; i < N; i++) {
            map.put(A[i], map.getOrDefault(A[i], 0L) + 1);
        }

        long dp[] = new long[(int) 1e5 + 1];
        if (map.containsKey(1))
            dp[1] = map.get(1);

        for (i = 2; i <= (int) 1e5; i++) {
            if (map.containsKey(i))
                dp[i] = Math.max(dp[i - 1], map.get(i) * i + dp[i - 2]);
            else
                dp[i] = dp[i - 1];
        }
        System.out.println(dp[(int) 1e5]);
    }
}
