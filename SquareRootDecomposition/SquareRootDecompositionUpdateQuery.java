package SquareRootDecomposition;

/*

You have an array of N integers and Q queries on this array. There are two types of queries:

1 L R – count the numbers divisible by K in range [L, R].
2 L R Y – add Y to all numbers in range [L, R].

*/

import java.util.Scanner;

public class SquareRootDecompositionUpdateQuery {
    public static void main(String ag[]) throws Exception {
        int i, j, k;
        //BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        //String input1[]=br.readLine().split(" ");
        int N = sc.nextInt();
        int Q = sc.nextInt();
        int K = sc.nextInt();

        //String input2[]=br.readLine().split(" ");
        long A[] = new long[N];
        for (i = 0; i < N; i++) {
            A[i] = sc.nextLong();
        }

        int BLOCK = (int) Math.ceil(Math.sqrt(N)) + 1;
        long lazy[] = new long[BLOCK + 1];
        int freq[][] = new int[BLOCK + 1][K + 1];

        for (i = 0; i < N; i++) {
            int X = i / BLOCK;
            int Y = (int) (A[i] % K);
            freq[X][Y]++;
        }
        StringBuilder ans = new StringBuilder();
        for (i = 1; i <= Q; i++) {
            //String input3[]=br.readLine().split(" ");
            int type = sc.nextInt();
            int L = sc.nextInt();
            int R = sc.nextInt();

            if (type == 1) {
                int bL = L / BLOCK;
                int bR = R / BLOCK;
                int Ans = 0;

                while (L / BLOCK == bL && L <= R) {
                    if ((A[L] + lazy[L / BLOCK]) % K == 0)
                        Ans++;
                    L++;
                }
                while (L + BLOCK <= R) {
                    long _ = ((long) K - lazy[L / BLOCK]) % K;
                    if (_ < 0)
                        _ += K;
                    Ans += freq[L / BLOCK][(int) _];
                    L = L + BLOCK;
                }
                while (L <= R) {
                    if ((A[L] + lazy[L / BLOCK]) % K == 0)
                        Ans++;
                    L++;
                }
                ans.append(Ans + "\n");
            } else {
                int update = sc.nextInt();

                int bL = L / BLOCK;
                int bR = R / BLOCK;

                while (L / BLOCK == bL && L <= R) {
                    freq[bL][(int) (A[L] % K)]--;
                    A[L] += update;
                    freq[bL][(int) (A[L] % K)]++;
                    L++;
                }
                while (L + BLOCK <= R) {
                    lazy[L / BLOCK] += update;
                    L = L + BLOCK;
                }
                while (L <= R) {
                    freq[L / BLOCK][(int) (A[L] % K)]--;
                    A[L] += update;
                    freq[L / BLOCK][(int) (A[L] % K)]++;
                    L++;
                }
            }
        }

        System.out.println(ans);
    }
}
