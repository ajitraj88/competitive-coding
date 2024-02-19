package DynamicProgramming;

/*
Patrick believes that a message is fancy if any uppercase letter stands to the left of any lowercase one. In other words,
this rule describes the strings where first go zero or more uppercase letters, and then — zero or more lowercase letters.

To make the message fancy, Patrick can erase some letter and add the same letter in the same place in the opposite case
(that is, he can replace an uppercase letter with the lowercase one and vice versa). Patrick got interested in the following
question: what minimum number of actions do we need to make a message fancy? Changing a letter's case in the message counts
as one action. Patrick cannot perform any other actions.

*/

import java.util.Arrays;
import java.util.Scanner;

public class FancyMessageFirstUppercaseThenLowercase {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        String str = sc.next();
        int N = str.length();
        int dp[][] = new int[N][3];
        for (i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);

        System.out.println(find(dp, 0, 2, str));
    }

    public static int find(int dp[][], int id, int prevParity, String str) {
        if (id == str.length())
            return 0;

        if (dp[id][prevParity] != -1)
            return dp[id][prevParity];

        int total = Integer.MAX_VALUE;
        int currParity = checkParity(str.charAt(id));
        if (currParity == 1 && prevParity == 0)
            total = Math.min(total, 1 + find(dp, id + 1, 1 ^ currParity, str));

        else {
            total = Math.min(total, find(dp, id + 1, currParity, str));
            if (!(currParity == 0 && prevParity == 0))
                total = Math.min(total, 1 + find(dp, id + 1, 1 ^ currParity, str));

        }
        return dp[id][prevParity] = total;
    }

    public static int checkParity(char ch) {
        if (ch >= 'A' && ch <= 'Z')
            return 1;
        return 0;
    }
}
