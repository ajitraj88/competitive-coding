package KMPHashingZFnManachar;

/*
You are given a string s, consisting of lowercase English letters. Find the longest string, t, which satisfies the following conditions:

The length of t does not exceed the length of s.
t is a palindrome.
There exists two strings a and b (possibly empty), such that t=a+b ( "+" represents concatenation), and a is prefix of s while b is suffix of s.
*/

import java.util.Scanner;

public class LongestPrefixPlusSuffixWhichIsPalindrome {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int T = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String str = sc.next();
            int N = str.length();
            i = 0;
            j = N - 1;
            for (; i < j; i++, j--) {
                if (str.charAt(i) != str.charAt(j))
                    break;
            }
            if (i == j) {
                sb.append(str + "\n");
                continue;
            }

            String remain = str.substring(i, j + 1);
            String reverseRemain = new StringBuffer(remain).reverse().toString();

            int prefix = KMP(remain, reverseRemain);
            int suffix = KMP(reverseRemain, remain);

            if (prefix >= suffix)
                sb.append(str.substring(0, prefix + i) + str.substring(j + 1) + "\n");
            else
                sb.append(str.substring(0, i) + str.substring(j - suffix + 1) + "\n");
        }
        System.out.println(sb);
    }

    public static int KMP(String str, String patt) {
        String S = str + "$" + patt;
        int N = S.length();
        int lps[] = new int[N];

        for (int i = 1; i < N; i++) {
            int len = lps[i - 1];
            while (len > 0 && S.charAt(i) != S.charAt(len))
                len = lps[len - 1];

            if (S.charAt(i) == S.charAt(len))
                len++;

            lps[i] = len;
        }
        return lps[N - 1];
    }
}
