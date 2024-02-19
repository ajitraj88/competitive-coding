package DynamicProgramming;

/*
Ilya the Lion wants to help all his friends with passing exams. They need to solve the following problem to pass the IT exam.

You've got string s = s1s2... sn (n is the length of the string), consisting only of characters "." and "#" and m queries. Each query is described by a pair of integers li, ri (1 ≤ li < ri ≤ n). The answer to the query li, ri is the number of such integers i (li ≤ i < ri), that si = si + 1.

Ilya the Lion wants to help his friends but is there anyone to help him? Help Ilya, solve the problem.

eg---- 	#..###
		5
		1 3
		5 6
		1 5
		3 6
		3 4

ans---- 1
		1
		2
		2
		0
*/

import java.util.ArrayList;
import java.util.Scanner;

public class QueryToFindNoOfConsecutiveSameNos {
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        char arr[] = sc.next().toCharArray();
        int N = arr.length;
        int dp[] = new int[N + 1];
        for (i = 1; i < N; i++) {
            if (arr[i] == arr[i - 1])
                dp[i] = dp[i - 1] + 1;
            else
                dp[i] = dp[i - 1];
        }
        int Q = sc.nextInt();
        while (Q-- > 0) {
            int L = sc.nextInt();
            int R = sc.nextInt();
            System.out.println(dp[R - 1] - dp[L - 1]);
        }
    }
}
