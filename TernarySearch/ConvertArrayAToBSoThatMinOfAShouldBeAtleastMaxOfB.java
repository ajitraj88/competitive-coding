package TernarySearch;

/*

Devu and his brother love each other a lot. As they are super geeks, they only like to play with arrays. They are given two arrays a and b by their father. The array a is given to Devu and b to his brother.

As Devu is really a naughty kid, he wants the minimum value of his array a should be at least as much as the maximum value of his brother's array b.

Now you have to help Devu in achieving this condition. You can perform multiple operations on the arrays. In a single operation, you are allowed to decrease or increase any element of any of the arrays by 1. Note that you are allowed to apply the operation on any index of the array multiple times.

You need to find minimum number of operations required to satisfy Devu's condition so that the brothers can play peacefully without fighting.

eg----  3 2
		1 2 3
		3 4

ans---- 4
*/

import java.util.Scanner;

public class ConvertArrayAToBSoThatMinOfAShouldBeAtleastMaxOfB {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int n = sc.nextInt();
        int m = sc.nextInt();
        long A[] = new long[n];
        long B[] = new long[m];

        for (i = 0; i < n; i++)
            A[i] = sc.nextLong();

        for (i = 0; i < m; i++)
            B[i] = sc.nextLong();

        long lb = 0;
        long ub = (long) 1e9;

        long ans = Long.MAX_VALUE;
        int cnt = 0;
        while (lb <= ub && cnt <= 100) {
            long mid1 = lb + (ub - lb) / 3;
            long mid2 = ub - (ub - lb) / 3;

            long X = find(mid1, A, B);
            long Y = find(mid2, A, B);

            if (X < ans) {
                ans = X;
            }
            if (Y < ans) {
                ans = Y;
            }

            if (X <= Y) {
                ub = mid2;
            } else {
                lb = mid1;
            }
            cnt++;
        }
        System.out.println(ans);
    }

    public static long find(long mid, long A[], long B[]) {
        long count = 0;
        for (int i = 0; i < A.length; i++)
            if (A[i] < mid)
                count += Math.abs(mid - A[i]);

        for (int i = 0; i < B.length; i++)
            if (B[i] > mid)
                count += Math.abs(mid - B[i]);

        return count;
    }
}
