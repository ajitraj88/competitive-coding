package Greedy;

/*
Let n be a positive integer. Let a,b,c be nonnegative integers such that a+b+c=n.

Alice and Bob are gonna play rock-paper-scissors n times. Alice knows the sequences of hands that Bob will play. However, Alice has to play rock a times, paper b times, and scissors c times.

Alice wins if she beats Bob in at least ⌈n/2⌉ (n2 rounded up to the nearest integer) hands, otherwise Alice loses.

Note that in rock-paper-scissors:

rock beats scissors;
paper beats rock;
scissors beat paper.
The task is, given the sequence of hands that Bob will play, and the numbers a,b,c, determine whether or not Alice can win. And if so, find any possible sequence of hands that Alice can use to win.

If there are multiple answers, print any of them.

eg---- 	2
		3
		1 1 1
		RPS
		3
		3 0 0
		RPS

ans---- YES(PSR)
	    NO
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class RockPaperScissorGame {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            String str = sc.next();
            HashMap<Character, Integer> hm = new HashMap<>();
            for (i = 0; i < N; i++) {
                hm.put(str.charAt(i), hm.getOrDefault(str.charAt(i), 0) + 1);
            }
            int x = hm.get('R') == null ? 0 : hm.get('R');
            int y = hm.get('P') == null ? 0 : hm.get('P');
            int z = hm.get('S') == null ? 0 : hm.get('S');
            int Ans = Math.min(b, x) + Math.min(c, y) + Math.min(a, z);

            if (Ans * 2 >= N) {
                System.out.println("YES");
                char ans[] = new char[N];
                Arrays.fill(ans, '@');
                for (i = 0; i < N; i++) {
                    if (str.charAt(i) == 'R' && b > 0) {
                        ans[i] = 'P';
                        b--;
                    } else if (str.charAt(i) == 'P' && c > 0) {
                        ans[i] = 'S';
                        c--;
                    } else if (str.charAt(i) == 'S' && a > 0) {
                        ans[i] = 'R';
                        a--;
                    }
                }
                ArrayList<Character> al = new ArrayList<>();
                while (a-- > 0)
                    al.add('R');
                while (b-- > 0)
                    al.add('P');
                while (c-- > 0)
                    al.add('S');
                for (i = 0, j = 0; i < N && j < al.size(); i++)
                    if (ans[i] == '@') {
                        ans[i] = al.get(j);
                        j++;
                    }

                System.out.println(new String(ans));
            } else
                System.out.println("NO");
        }
    }
}
