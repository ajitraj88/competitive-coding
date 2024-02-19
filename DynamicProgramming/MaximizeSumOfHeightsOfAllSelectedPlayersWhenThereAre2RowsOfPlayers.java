package DynamicProgramming;

/*
Finally, a basketball court has been opened in SIS, so Demid has decided to hold a basketball exercise session. 2⋅n students have come to Demid's exercise session, and he lined up them into two rows of the same size (there are exactly n people in each row). Students are numbered from 1 to n in each row in order from left to right.
Now Demid wants to choose a team to play basketball. He will choose players from left to right, and the index of each chosen player (excluding the first one taken) will be strictly greater than the index of the previously chosen player. To avoid giving preference to one of the rows, Demid chooses students in such a way that no consecutive chosen students belong to the same row. The first student can be chosen among all 2n students (there are no additional constraints), and a team can consist of any number of students.

Demid thinks, that in order to compose a perfect team, he should choose students in such a way, that the total height of all chosen students is maximum possible. Help Demid to find the maximum possible total height of players in a team he can choose.
*/

import java.util.Arrays;
import java.util.Scanner;

public class MaximizeSumOfHeightsOfAllSelectedPlayersWhenThereAre2RowsOfPlayers {
    static int N;
    static long A[];
    static long B[];
    static long dp[][];

    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        N = sc.nextInt();
        A = new long[N];
        B = new long[N];
        dp = new long[N][3];
        for (i = 0; i < N; i++) {
            A[i] = sc.nextLong();
        }
        for (i = 0; i < N; i++) {
            B[i] = sc.nextLong();
        }
        for (i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(find(0, 0));
    }

    public static long find(int id, int prev) {
        if (id == N)
            return 0;

        if (dp[id][prev] != -1)
            return dp[id][prev];

        long total = 0;
        if (prev != 2) {
            total = Math.max(total, B[id] + find(id + 1, 2));
            total = Math.max(total, find(id + 1, prev));
        }
        if (prev != 1) {
            total = Math.max(total, A[id] + find(id + 1, 1));
            total = Math.max(total, find(id + 1, prev));
        }
        return dp[id][prev] = total;
    }
}
