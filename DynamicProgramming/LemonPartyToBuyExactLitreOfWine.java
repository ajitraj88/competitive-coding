package DynamicProgramming;

/*
Your favorite store sells lemonade in bottles of n different volumes at different costs. A single bottle of type i has volume 2 ^(i - 1) liters and costs Ci roubles. The number of bottles of each type in the store can be considered infinite.

You want to buy at least L liters of lemonade. How many roubles do you have to spend?
the smallest number of roubles you have to pay in order to buy at least L liters of lemonade.

eg---- 4 12
	   20 30 70 90

ans---- 150  ie buy one 8-liter bottle for 90 roubles and two 2-liter bottles for 30 roubles each. In total you'll get 12 liters of lemonade for just roubles.
*/

import java.util.Scanner;

public class LemonPartyToBuyExactLitreOfWine {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        long L = sc.nextLong();
        long arr[] = new long[N];
        for (i = 0; i < N; i++) {
            arr[i] = sc.nextLong();
        }
        for (i = 1; i < N; i++) {
            arr[i] = Math.min(2 * arr[i - 1], arr[i]);
        }

        long total = 0;
        long ans = Long.MAX_VALUE;
        for (i = N - 1; i >= 0; i--) {
            long available = L / (1 << i);
            total += available * arr[i];
            L = L - (available) * (1 << i);
            ans = Math.min(ans, total + (L > 0 ? 1 : 0) * arr[i]);
        }
        System.out.println(ans);
    }
}
