package KMPHashingZFnManachar;

/*
You are given a string s, consisting of lowercase English letters. Find the longest string, t, which satisfies the following conditions:

The length of t does not exceed the length of s.
t is a palindrome.
There exists two strings a and b (possibly empty), such that t=a+b ( "+" represents concatenation), and a is prefix of s while b is suffix of s.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Longest_prefixPlusSuffixWhichIsPalindromeUsingManachar {
    public static void main(String ag[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i, j, k;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String str = br.readLine();
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
            String neww = Manacheralgo(remain.toCharArray());
            //System.out.println(neww);

            sb.append(str.substring(0, i) + neww + str.substring(j + 1) + "\n");

        }
        System.out.println(sb);
    }

    public static String Manacheralgo(char input[]) {
        int index = 0;
        char newInput[] = new char[2 * input.length + 1];
        for (int i = 0; i < newInput.length; i++) {
            if (i % 2 != 0) {
                newInput[i] = input[index++];
            } else {
                newInput[i] = '$';
            }
        }

        int T[] = new int[newInput.length];
        int start = 0;
        int end = 0;

        for (int i = 0; i < newInput.length; ) {
            while (start > 0 && end < newInput.length - 1 && newInput[start - 1] == newInput[end + 1]) {
                start--;
                end++;
            }
            T[i] = end - start + 1;

            if (end == T.length - 1) {
                break;
            }
            int newCenter = end + (i % 2 == 0 ? 1 : 0);

            for (int j = i + 1; j <= end; j++) {
                T[j] = Math.min(T[i - (j - i)], 2 * (end - j) + 1);
                if (j + T[i - (j - i)] / 2 == end) {
                    newCenter = j;
                    break;
                }
            }
            i = newCenter;
            end = i + T[i] / 2;
            start = i - T[i] / 2;
        }

        int max = Integer.MIN_VALUE;
        int L = 0;
        int R = 0;
        for (int i = 0; i < T.length; i++) {
            int val = T[i];
            if (max < val && (i - val / 2 == 0 || i + val / 2 == T.length - 1)) {
                max = val;
                if (i - val / 2 == 0) {
                    L = 0;
                    R = i + val / 2;
                    ;
                } else {
                    L = i - val / 2;
                    R = T.length - 1;
                }
            }
        }
        //System.out.println(L+" "+R);
        String ans = "";
        for (int i = L; i <= R; i++)
            if (newInput[i] != '$')
                ans += newInput[i];                 // $c$b$b$
        return ans;
    }
}
