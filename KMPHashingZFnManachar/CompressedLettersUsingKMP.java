package KMPHashingZFnManachar;

/*
compressed version of letter eg aaaaaaaaaa is of length 3 , since  |a|+|10|=1+2= 3 in length
Output one integer number — the minimum possible length of a compressed version of s
*/

/*
Hint:
Related to finding period of string
*/

import java.util.Arrays;
import java.util.Scanner;

public class CompressedLettersUsingKMP {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        String s = sc.next();
        int N = s.length();
        int LPS[][] = new int[N][N];
        for (i = 0; i < N; i++) {
            LPS[i] = findLPS(s.substring(i));
        }

        int ans[] = new int[N];
        Arrays.fill(ans, Integer.MAX_VALUE);
        for (i = 0; i < N; i++) {
            for (j = i; j < N; j++) {
                int length = j - i + 1;
                int period = length - LPS[i][length - 1];
                if (period > 0 && length % period == 0) {
                    ans[j] = Math.min(ans[j], String.valueOf((length / period)).length() + period + (i > 0 ? ans[i - 1] : 0));
                } else {
                    ans[j] = Math.min(ans[j], 1 + length + (i > 0 ? ans[i - 1] : 0));
                }
            }
        }
        System.out.println(ans[N - 1]);

    }

    public static int[] findLPS(String str) {
        int i = 1;
        int len = 0;
        int N = str.length();
        int lps[] = new int[N];
        while (i < N) {
            if (str.charAt(i) == str.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0)
                    len = lps[len - 1];
                else
                    i++;
            }
        }
        return lps;
    }
}
