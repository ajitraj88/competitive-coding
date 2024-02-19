package TernarySearch;

/*
You are given a multiset S consisting of positive integers (initially empty). There are two kind of queries:

Add a positive integer to S, the newly added integer is not less than any number in it.
Find a subset s of the set S such that the value Max(s)-Mean(s) is maximum possible. Here max(s) means maximum value of elements in s,  —
the average value of numbers in s. Output this maximum possible value of .


The first line contains a single integer Q (1 ≤ Q ≤ 5·105) — the number of queries.

Each of the next Q lines contains a description of query. For queries of type 1 two integers 1 and x are given, where x (1 ≤ x ≤ 10^9) is a number
that you should add to S. It's guaranteed that x is not less than any number in S. For queries of type 2, a single integer 2 is given.

It's guaranteed that the first query has type 1, i. e. S is not empty when a query of type 2 comes.

eg----  6
		1 3
		2
		1 4
		2
		1
ans---- 0.0000000000
		0.5000000000
		3.0000000000

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MaximumValueOfMaxOfSetMinusMeanOfSet {
    public static void main(String ag[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i, j, k;
        int Q = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        long prefix[] = new long[Q];
        long A[] = new long[Q];
        i = 0;
        while (Q-- > 0) {
            String ip[] = br.readLine().trim().split(" ");
            if (ip.length == 1) {
                System.out.printf("%.6f\n", ternarySearch(prefix, i, A[i]));
            } else {
                long value = Long.parseLong(ip[1]);
                A[++i] = value;
                prefix[i] += prefix[i - 1] + A[i];
            }
        }
        //System.out.println(Arrays.toString(prefix));
    }

    public static double ternarySearch(long pre[], int id, long last) {
        int lb = 1;
        int ub = id;
        double ans = Double.MIN_VALUE;
        while (ub - lb >= 3) {
            int mid1 = lb + (ub - lb) / 3;
            int mid2 = ub - (ub - lb) / 3;

            double X = find(mid1, pre, last);
            double Y = find(mid2, pre, last);

            if (X > ans) {
                ans = X;
            }
            if (Y > ans) {
                ans = Y;
            }
            if (X >= Y) {
                ub = mid2;
            } else {
                lb = mid1;
            }
        }

        for (int i = lb; i <= ub; i++) {
            ans = Math.max(ans, find(i, pre, last));
        }
        return ans;
    }

    public static double find(int mid, long pre[], long last) {
        double ans = (double) (pre[mid] + last) / (double) (1 + mid);
        return ans = last - ans;
    }
}
