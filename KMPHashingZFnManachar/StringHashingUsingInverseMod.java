package KMPHashingZFnManachar;

/* check whether a given string S can be written as S=T1+T1+T2+T2. Find no of ways to do so
eg. ababcdccdc --ANS=1  ie ab+ab+cdc+cdc
*/

import java.util.Scanner;

public class StringHashingUsingInverseMod {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;

        int MAX = (int) 1e5 + 1;
        long MOD = (long) 1e9 + 7;
        long power[] = new long[MAX];
        long inverse[] = new long[MAX];
        long p = 31;

        power[0] = 1;
        for (i = 1; i < MAX; i++)
            power[i] = (power[i - 1] % MOD * p) % MOD;

        for (i = 0; i < MAX; i++)
            inverse[i] = power(power[i], MOD - 2, MOD) % MOD;

        int T = sc.nextInt();
        while (T-- > 0) {
            char A[] = sc.next().toCharArray();
            int N = A.length;
            long hash[] = new long[N + 1];
            for (i = 1; i <= N; i++) {
                hash[i] = (hash[i - 1] % MOD + ((A[i - 1] - 'a' + 1) * power[i - 1]) % MOD) % MOD;
            }

            long ans = 0;
            for (i = 2; i <= N - 2; i += 2) {
                int s1 = i / 2;
                int s2 = i;
                int s3 = (N - i) / 2 + i;
                int s4 = N;

                long h1 = ((hash[s1] - hash[0]) % MOD * inverse[0] % MOD + 5 * MOD) % MOD;
                long h2 = ((hash[s2] - hash[s1]) % MOD * inverse[s1] % MOD + 5 * MOD) % MOD;
                long h3 = ((hash[s3] - hash[s2]) % MOD * inverse[s2] % MOD + 5 * MOD) % MOD;
                long h4 = ((hash[s4] - hash[s3]) % MOD * inverse[s3] % MOD + 5 * MOD) % MOD;
                if (h1 == h2 && h3 == h4)
                    ans++;

            }
            System.out.println(ans);
        }
    }

    public static long power(long a, long b, long mod) {
        long res = 1;
        while (b > 0) {
            if (b % 2 == 1)
                res = (res % mod * a % mod) % mod;
            a = (a % mod * a % mod) % mod;
            b = b >> 1;
        }
        return res;
    }
}
