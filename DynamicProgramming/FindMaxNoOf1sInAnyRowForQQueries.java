package DynamicProgramming;

/*
Find max no of ones in any rows for Q queries if state of any cell is changed from 0 to 1 or 1 to 0 in each query
*/

import java.util.Scanner;

public class FindMaxNoOf1sInAnyRowForQQueries {
    public static void main(String ag[]) {
        int i, j, k;
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int Q = sc.nextInt();

        int A[][] = new int[N + 1][M + 1];
        for (i = 1; i <= N; i++) {
            for (j = 1; j <= M; j++) {
                A[i][j] = sc.nextInt();
            }
        }
        int row[] = new int[N + 1];
        for (i = 1; i <= N; i++) {
            int cnt = 0;
            for (j = 1; j <= M; j++) {
                if (A[i][j] == 1)
                    cnt++;
                else {
                    row[i] = Math.max(row[i], cnt);
                    cnt = 0;
                }
            }
            row[i] = Math.max(row[i], cnt);
        }

        StringBuilder ans = new StringBuilder();
        while (Q-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            A[a][b] = 1 - A[a][b];
            int c = 0;
            row[a] = 0;
            for (i = 1; i <= M; i++) {
                if (A[a][i] == 1)
                    c++;
                else {
                    row[a] = Math.max(row[a], c);
                    c = 0;
                }
            }
            row[a] = Math.max(row[a], c);
            int max = 0;
            for (i = 1; i <= N; i++)
                max = Math.max(max, row[i]);
            ans.append(max + "\n");
        }
        System.out.println(ans);
    }
}
