package DynamicProgramming;

/*
Find max distance that car can travel given the initial and final speed of car. The car moves for T seconds and b/w d sec the diff of speeds shouldn't exceed d
*/

import java.util.Arrays;
import java.util.Scanner;

public class FindMaxDistanceThatCanTravelGivenInitialAndFinalSpeed {
    static int v1, v2, d, t;
    static long dp[][];

    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        v1 = sc.nextInt();
        v2 = sc.nextInt();
        t = sc.nextInt();
        d = sc.nextInt();
        dp = new long[Math.max(v1, v2) + t * d + 1][t + 1];
        for (i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], -1);
        System.out.println(find(v1, 1));
    }

    public static long find(int curr, int time) {
        if (time == t && curr == v2)
            return curr;

        if (time == t || curr <= 0)
            return Integer.MIN_VALUE;

        if (dp[curr][time] != -1)
            return dp[curr][time];

        long total = Integer.MIN_VALUE;
        for (int i = d; i >= 0; i--) {
            long a = curr + find(curr + i, time + 1);
            long b = 0;
            if (curr - i > 0)
                b = curr + find(curr - i, time + 1);
            total = Math.max(total, Math.max(a, b));
        }

        return dp[curr][time] = total;
    }
}
