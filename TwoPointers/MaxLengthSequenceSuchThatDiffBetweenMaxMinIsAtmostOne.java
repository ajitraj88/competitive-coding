package TwoPointers;

/*
You're given a sequence of n data points a1, ..., an. There aren't any big jumps between consecutive data points — for each 1 ≤ i < n, it's guaranteed that |ai + 1 - ai| ≤ 1.

A range [l, r] of data points is said to be almost constant if the difference between the largest and the smallest value in that range is at most 1. Formally, let M be the maximum and m the minimum value of ai for l ≤ i ≤ r; the range [l, r] is almost constant if M - m ≤ 1.

Find the length of the longest almost constant range.
*/

import java.util.Scanner;

public class MaxLengthSequenceSuchThatDiffBetweenMaxMinIsAtmostOne {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int A[] = new int[N];
        for (i = 0; i < N; i++)
            A[i] = sc.nextInt();

        int freq[] = new int[(int) 1e5 + 1];
        int l = 0;
        int r = 0;
        int max = A[0];
        int min = A[0];
        int diff = 0;
        int ans = 1;
        freq[A[0]]++;
        while (r < N) {
            if (diff <= 1) {
                if (r == N - 1)
                    break;

                r++;
                freq[A[r]]++;
                max = Math.max(max, A[r]);
                min = Math.min(min, A[r]);
                diff = Math.abs(max - min);

                if (diff <= 1)
                    ans = Math.max(r - l + 1, ans);
            } else {
                l++;
                freq[A[l - 1]]--;
                if (freq[A[l - 1]] == 0 && min == A[l - 1])
                    min = Integer.MAX_VALUE;
                if (freq[A[l - 1]] == 0 && max == A[l - 1])
                    max = Integer.MIN_VALUE;

                max = Math.max(max, A[l]);
                min = Math.min(min, A[l]);
                diff = Math.abs(max - min);
                if (diff <= 1)
                    ans = Math.max(ans, r - l + 1);
            }
            // System.out.println(l+" "+r+" "+diff+" "+ans);
        }
        System.out.println(ans);
    }
}
