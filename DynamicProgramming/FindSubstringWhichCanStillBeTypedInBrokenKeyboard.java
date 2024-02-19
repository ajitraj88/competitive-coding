package DynamicProgramming;

/*
Find how many substrings of a string can still be typed when some keys are down
*/

import java.util.HashSet;
import java.util.Scanner;

public class FindSubstringWhichCanStillBeTypedInBrokenKeyboard {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int K = sc.nextInt();
        char s[] = sc.next().toCharArray();

        HashSet<Character> hs = new HashSet<>();

        for (i = 0; i < K; i++)
            hs.add(sc.next().charAt(0));

        long cnt = 0;
        long ans = 0;
        for (i = 0; i < s.length; i++) {
            if (hs.contains(s[i]))
                cnt++;
            else {
                ans += ((cnt) * (cnt + 1)) / 2;
                cnt = 0;
            }
        }
        ans += ((cnt) * (cnt + 1)) / 2;
        System.out.println(ans);
    }
}
