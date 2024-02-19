package KMPHashingZFnManachar;

/*
Asterix, Obelix and their temporary buddies Suffix and Prefix has finally found the Harmony temple. However, its doors were firmly locked and even Obelix had no luck opening them.

A little later they found a string s, carved on a rock below the temple's gates. Asterix supposed that that's the password that opens the temple and read the string aloud. However, nothing happened. Then Asterix supposed that a password is some substring t of the string s.

Prefix supposed that the substring t is the beginning of the string s; Suffix supposed that the substring t should be the end of the string s; and Obelix supposed that t should be located somewhere inside the string s, that is, t is neither its beginning, nor its end.

Asterix chose the substring t so as to please all his companions. Besides, from all acceptable variants Asterix chose the longest one (as Asterix loves long strings). When Asterix read the substring t aloud, the temple doors opened.

You know the string s. Find the substring t or determine that such substring does not exist and all that's been written above is just a nice legend.

*/

import java.util.Scanner;

public class StringOccuringAsPrefixSuffixAlsoInBetweenSomewhere {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        String s = sc.next();
        int N = s.length();
        boolean flag = true;
        String ans = "";
        if (N <= 2)
            flag = false;
        else {
            int LPS[] = new int[N];
            LPS = findLPS(s);
            if (LPS[N - 1] == 0)
                flag = false;
            else {
                for (i = 0; i < N - 1; i++) {
                    if (LPS[i] == LPS[N - 1]) {
                        ans = s.substring(0, LPS[i]);
                        break;
                    }
                }

                if (i == N - 1) {
                    int max = LPS[LPS[N - 1] - 1];
                    if (max > 0)
                        ans = s.substring(0, max);
                    else
                        flag = false;
                }
            }

        }

        if (flag)
            System.out.println(ans);
        else
            System.out.println("Just a legend");
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
