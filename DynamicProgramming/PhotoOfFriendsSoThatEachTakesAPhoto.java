package DynamicProgramming;

/*
One day n friends met at a party, they hadn't seen each other for a long time and so they decided to make a group photo together.

Simply speaking, the process of taking photos can be described as follows. On the photo, each photographed friend occupies a rectangle of pixels: the i-th of them occupies the rectangle of width wi pixels and height hi pixels. On the group photo everybody stands in a line, thus the minimum pixel size of the photo including all the photographed friends, is W × H, where W is the total sum of all widths and H is the maximum height of all the photographed friends.

As is usually the case, the friends made n photos — the j-th (1 ≤ j ≤ n) photo had everybody except for the j-th friend as he was the photographer.

Print the minimum size of each made photo in pixels.
*/

import java.util.ArrayList;
import java.util.Scanner;

public class PhotoOfFriendsSoThatEachTakesAPhoto {
    static ArrayList<Integer> ans = new ArrayList<>();

    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int w[] = new int[N + 1];
        int h[] = new int[N + 1];
        for (i = 1; i <= N; i++) {
            w[i] = sc.nextInt();
            h[i] = sc.nextInt();
        }

        int preSum[] = new int[N + 2];
        int suffSum[] = new int[N + 2];
        int preMax[] = new int[N + 2];
        int suffMax[] = new int[N + 2];

        preSum[1] = w[1];
        for (i = 2; i <= N; i++)
            preSum[i] = preSum[i - 1] + w[i];

        suffSum[N] = w[N];
        for (i = N - 1; i >= 1; i--)
            suffSum[i] = suffSum[i + 1] + w[i];

        preMax[1] = h[1];
        for (i = 2; i <= N; i++)
            preMax[i] = Math.max(preMax[i - 1], h[i]);

        suffMax[N] = h[N];
        for (i = N - 1; i >= 1; i--)
            suffMax[i] = Math.max(suffMax[i + 1], h[i]);

        int ans[] = new int[N + 1];
        for (i = 1; i <= N; i++) {
            ans[i] = (preSum[i - 1] + suffSum[i + 1]) * Math.max(preMax[i - 1], suffMax[i + 1]);
        }

        for (i = 1; i <= N; i++)
            System.out.print(ans[i] + " ");
    }
}
