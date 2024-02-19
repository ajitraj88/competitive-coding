package KMPHashingZFnManachar;

/*
Polar bears Menshykov and Uslada from the zoo of St. Petersburg and elephant Horace from the zoo of Kiev got hold of lots of wooden cubes somewhere. They started making cube towers by placing the cubes one on top of the other. They defined multiple towers standing in a line as a wall. A wall can consist of towers of different heights.

Horace was the first to finish making his wall. He called his wall an elephant. The wall consists of w towers. The bears also finished making their wall but they didn't give it a name. Their wall consists of n towers. Horace looked at the bears' tower and wondered: in how many parts of the wall can he "see an elephant"? He can "see an elephant" on a segment of w contiguous towers if the heights of the towers on the segment match as a sequence the heights of the towers in Horace's wall. In order to see as many elephants as possible, Horace can raise and lower his wall. He even can lower the wall below the ground level (see the pictures to the samples for clarification).

Your task is to count the number of segments where Horace can "see an elephant".

eg----	13 5
		2 4 5 5 4 3 2 2 2 3 3 2 1
		3 4 4 3 2
o/p---- 2

*/

import java.util.Scanner;

public class WallsRaiseOrLowersUsingKMP {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int W = sc.nextInt();
        long B[] = new long[N];
        long E[] = new long[W];
        if (W == 1) {
            System.out.println(N);
            return;
        }
        for (i = 0; i < N; i++)
            B[i] = sc.nextLong();
        for (i = 0; i < W; i++)
            E[i] = sc.nextLong();

        long diffB[] = new long[N - 1];
        long diffE[] = new long[W - 1];

        for (i = 1; i < N; i++)
            diffB[i - 1] = B[i] - B[i - 1];

        for (i = 1; i < W; i++)
            diffE[i - 1] = E[i] - E[i - 1];

        int LPS[] = findLPS(diffE);
        System.out.println(KMP(LPS, diffB, diffE));
    }

    public static int[] findLPS(long A[]) {
        int i = 1;
        int len = 0;
        int N = A.length;
        int lps[] = new int[N];
        while (i < N) {
            if (A[i] == A[len]) {
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

    public static int KMP(int lps[], long str[], long patt[]) {
        int i = 0;
        int j = 0;
        int M = patt.length;
        int N = str.length;
        int cnt = 0;
        while (i < N) {
            if (str[i] == patt[j]) {
                i++;
                j++;
            }
            if (j == M) {
                cnt++;
                j = lps[j - 1];
            } else if (i < N && str[i] != patt[j]) {
                if (j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
        }
        return cnt;
    }
}
