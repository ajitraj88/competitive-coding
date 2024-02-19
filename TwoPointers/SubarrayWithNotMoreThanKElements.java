package TwoPointers;

/* find a subarray in an array having not more than K distinct charaters ,it need not be minimal */

import java.util.Scanner;

public class SubarrayWithNotMoreThanKElements {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int K = sc.nextInt();
        int A[] = new int[N];
        for (i = 0; i < N; i++)
            A[i] = sc.nextInt();

        int freq[] = new int[100000 + 1];
        int cnt = 0;
        for (i = 0; i < N; i++) {
            if (freq[A[i]] == 0)
                cnt++;
            freq[A[i]]++;
        }
        i = 0;
        j = N - 1;
        boolean flag = false;
        if (cnt >= K)
            while (i < j) {
                if (cnt >= K && !flag) {
                    freq[A[j]]--;
                    if (freq[A[j]] == 0)
                        cnt--;
                    j--;
                    if (cnt < K) {
                        flag = true;
                        j++;
                        cnt++;
                        freq[A[j]]++;
                    }
                } else {
                    //System.out.println(i+" "+j);
                    freq[A[i]]--;
                    if (freq[A[i]] == 0)
                        cnt--;
                    i++;
                    if (cnt < K) {
                        i--;
                        cnt++;
                        break;
                    }
                }
            }
        i++;
        j++;
        if (cnt == K)
            System.out.println(i + " " + j);
        else
            System.out.println((-1) + " " + (-1));
    }
}
