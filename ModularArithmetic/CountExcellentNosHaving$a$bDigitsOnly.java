package ModularArithmetic;

/*
Vitaly is a very weird man. He's got two favorite digits a and b. Vitaly calls a positive integer good, if the decimal representation of this integer only contains digits a and b. Vitaly calls a good number excellent, if the sum of its digits is a good number.

For example, let's say that Vitaly's favourite digits are 1 and 3, then number 12 isn't good and numbers 13 or 311 are. Also, number 111 is excellent and number 11 isn't.

Now Vitaly is wondering, how many excellent numbers of length exactly n are there. As this number can be rather large, he asks you to count the remainder after dividing it by 1000000007 (109 + 7).

A number's length is the number of digits in its decimal representation without leading zeroes

*/

import java.util.Scanner;

public class CountExcellentNosHaving$a$bDigitsOnly {
    static int MOD = (int) 1e9 + 7;
    static long fact[] = new long[(int) 1e6 + 1];

    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong();
        long B = sc.nextLong();
        int N = sc.nextInt();

        fact[0] = 1;
        for (int i = 1; i <= 1e6; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        long ans = 0;
        for (int i = 0; i <= N; i++) {
            long sum = (((long) A * i) + ((long) B * (N - i))) % MOD;  //sum of no formed using i As and (N-i) Bs
            if (check(sum, A, B)) {
                ans += nCr(N, i) % MOD;
                ans %= MOD;
            }
        }
        System.out.println(ans);
    }

    public static boolean check(long num, long A, long B) {
        long sum = 0;
        while (num > 0) {
            long rem = num % 10;
            if (rem != A && rem != B)
                return false;
            sum += rem;
            num /= 10;
        }
        return true;
    }

    public static long nCr(int p, int q) {
        return (fact[p] % MOD * factInverse(((long) fact[q] * fact[(p - q)]) % MOD, MOD - 2)) % MOD;
    }


    public static long factInverse(long a, long b) {
        long res = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                res = (res * a) % MOD;
                b--;
            } else {
                a = (a * a) % MOD;
                b /= 2;
            }
        }
        return res;
    }
}
