package Greedy;

/*
You are given integers n, k, A and B. There is a number x, which is initially equal to n. You are allowed to perform two types of operations:

Subtract 1 from x. This operation costs you A coins.
Divide x by k. Can be performed only if x is divisible by k. This operation costs you B coins.
What is the minimum amount of coins you have to pay to make x equal to 1?

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MinNoOfCoinsToMakeNoEqualsTo1 {
    public static void main(String ag[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i, j, k;
        long N = Long.parseLong(br.readLine());
        long K = Long.parseLong(br.readLine());
        long A = Long.parseLong(br.readLine());
        long B = Long.parseLong(br.readLine());
        long cost = 0;
        long n = N;

        if (K == 1)
            cost = (N - 1) * A;

        else
            while (N > 1) {
                if (K > N) {
                    cost += A * (N - 1);
                    N = 1;
                } else if (N % K != 0) {
                    cost += A * (N % K);
                    N = N - (N % K);
                } else {
                    long prev = N;
                    N = N / K;
                    cost += Math.min(B, (prev - N) * A);
                }
            }

        System.out.println(cost);
    }
}
