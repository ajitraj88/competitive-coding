package Greedy;

/*
Write a sentence given into k lines such that each line contains min a characters and max b characters
Find the final o/p
*/

import java.util.Scanner;

public class WriteSentenceIntoKLinesMinCharsAMaxCharsBInOneLine {
    public static void main(String ag[]) throws Exception {
        int i, j, k, kk;
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int A = sc.nextInt();
        int B = sc.nextInt();
        String str = sc.next();
        int N = str.length();

        int lb = A * K;
        int ub = B * K;
        if (N < lb || N > ub)
            System.out.println("No solution");
        else {
            int X = N / K;
            int rem = N % K;
            StringBuffer ans = new StringBuffer();
            for (i = 0; i < N; ) {
                if (rem > 0) {
                    ans.append(str.substring(i, Math.min(N, i + X + 1)) + "\n");
                    i += X + 1;
                    rem--;
                } else {
                    ans.append(str.substring(i, Math.min(N, i + X)) + "\n");
                    i += X;
                }

            }
            System.out.println(ans);
        }
    }
}
