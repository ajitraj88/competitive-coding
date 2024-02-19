package DynamicProgramming;

/*
Ivan is playing a strange game.

He has a matrix a with n rows and m columns. Each element of the matrix is equal to either 0 or 1. Rows and columns are 1-indexed. Ivan can replace any number of ones in this matrix with zeroes. After that, his score in the game will be calculated as follows:

Initially Ivan's score is 0;
In each column, Ivan will find the topmost 1 (that is, if the current column is j, then he will find minimum i such that a i, j = 1). If there are no 1's in the column, this column is skipped;
Ivan will look at the next min(k, n - i + 1) elements in this column (starting from the element he found) and count the number of 1's among these elements. This number will be added to his score.
Of course, Ivan wants to maximize his score in this strange game. Also he doesn't want to change many elements, so he will replace the minimum possible number of ones with zeroes. Help him to determine the maximum possible score he can get and the minimum possible number of replacements required to achieve that score.

Print two numbers: the maximum possible score Ivan can get and the minimum number of replacements required to get this score.

eg---- 4 3 2
		0 1 0
		1 0 1
		0 1 0
		1 1 1
ans----4 1
*/

import java.util.Scanner;

public class GameOnMatrixToReplace1sTo0s {
    public static void main(String ag[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        int A[][] = new int[N + 1][M + 1];
        for (i = 1; i <= N; i++) {
            for (j = 1; j <= M; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        int dp[][] = new int[N + 1][M + 1];
        for (i = 1; i <= M; i++) {
            for (j = 1; j <= N; j++) {
                dp[j][i] = dp[j - 1][i] + A[j][i];
            }
        }

        int changes = 0;
        int score = 0;
        for (i = 1; i <= M; i++) {
            int curr = 0;
            int flips = 0;
            int currChanges = 0;
            for (j = 1; j <= N; j++) {
                int freq = 0;
                if (A[j][i] == 1) {
                    int X = Math.min(j + K - 1, N);
                    freq = dp[X][i] - dp[j - 1][i];
                    //System.out.println(X+" "+i+" "+j+" "+i+" "+freq);
                    if (freq > curr) {
                        curr = freq;
                        currChanges = flips;
                    }
                    flips++;
                }
            }

            changes += currChanges;
            score += curr;
        }
        System.out.println(score + " " + changes);
    }
}
