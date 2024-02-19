package DynamicProgramming;

/*

Little penguin Polo has an n × m matrix, consisting of integers. Let's index the matrix rows from 1 to n from top to bottom and let's index the columns from 1 to m from left to right. Let's represent the matrix element on the intersection of row i and column j as a ij.

In one move the penguin can add or subtract number d from some matrix element. Find the minimum number of moves needed to make all matrix elements equal. If the described plan is impossible to carry out, say so.

*/

import java.util.Arrays;
import java.util.Scanner;

public class MakeAllMatrixEqualInMinimalMoves {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int M = sc.nextInt();
        int D = sc.nextInt();
        int A[] = new int[N * M];

        boolean flag = true;
        for (i = 0; i < N * M; i++) {
            A[i] = sc.nextInt();
        }
        for (i = 1; i < N * M; i++) {
            if (A[i] % D != A[i - 1] % D) {
                flag = false;
                break;
            }
        }

        if (!flag) {
            System.out.println(-1);
            return;
        }

        Arrays.sort(A);
        int ans = Integer.MAX_VALUE;
        if ((N * M) % 2 == 0)
            ans = Math.min(find(A, (N * M) / 2, D), find(A, ((N * M) / 2) - 1, D));
        else
            ans = Math.min(ans, find(A, (N * M) / 2, D));

        System.out.println(ans);

    }

    public static int find(int A[], int median, int D) {
        int N = A.length;
        int count = 0;
        for (int i = 0; i < median; i++) {
            count += (A[median] - A[i]) / D;
        }
        for (int i = median + 1; i < N; i++) {
            count += (A[i] - A[median]) / D;
        }
        return count;
    }
}
