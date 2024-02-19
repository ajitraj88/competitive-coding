package TernarySearch;

/*
Dark is going to attend Motarack's birthday. Dark decided that the gift he is going to give to Motarack is an array a of n non-negative integers.

Dark created that array 1000 years ago, so some elements in that array disappeared. Dark knows that Motarack hates to see an array that has two adjacent elements with a high absolute difference between them. He doesn't have much time so he wants to choose an integer k (0≤k≤109) and replaces all missing elements in the array a with k.

Let m be the maximum absolute difference between all adjacent elements (i.e. the maximum value of |a(i)−a(i+1)| for all 1≤i≤n−1) in the array a after Dark replaces all missing elements with k.

Dark should choose an integer k so that m is minimized. Can you help him?

*/

import java.util.Scanner;

public class MinimizeAdjacentAbsoluteDifference {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            long A[] = new long[N + 1];
            for (i = 1; i <= N; i++) {
                A[i] = sc.nextLong();
            }

            long lb = 0;
            long ub = (int) 1e9;
            long num = 0;
            long diff = 0;
            while (lb <= ub) {
                long mid = (lb + ub) / 2;
                long X = find(mid, A);
                long Y = find(mid + 1, A);
                if (X <= Y) {
                    ub = mid - 1;
                    num = mid;
                    diff = X;
                } else {
                    lb = mid + 1;
                }
            }
            System.out.println(diff + " " + num);
        }
    }

    public static long find(long mid, long A[]) {
        int N = A.length;
        long max = Integer.MIN_VALUE;
        long a, b;
        for (int i = 2; i < N; i++) {
            a = A[i - 1];
            b = A[i];
            if (A[i - 1] == -1)
                a = mid;
            if (A[i] == -1)
                b = mid;
            max = Math.max(max, Math.abs(a - b));
        }
        return max;
    }
}
