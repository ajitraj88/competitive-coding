package TwoPointers;

/*
The array a with n integers is given. Let's call the sequence of one or more consecutive elements in a segment. Also let's call the segment k-good if it contains no more than k different values.

Find any longest k-good segment

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LongestKGoodSegment {
    public static void main(String ag[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i, j, k;
        String str[] = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);

        String arr[] = br.readLine().split(" ");
        int A[] = new int[N];
        for (i = 0; i < N; i++) {
            A[i] = Integer.parseInt(arr[i]);
        }

        int freq[] = new int[(int) 1e6 + 1];
        freq[A[0]]++;
        int ans = 1;
        int max = 1;
        int L = 1;
        int R = 1;
        i = 0;
        j = 0;

        while (j < N) {
            if (j < i) {
                j++;
                continue;
            }
            if (ans <= K) {
                if (j == N - 1)
                    break;

                j++;
                freq[A[j]]++;
                if (freq[A[j]] == 1)
                    ans++;
            } else {
                i++;
                freq[A[i - 1]]--;
                if (freq[A[i - 1]] == 0)
                    ans--;
            }
            if (j - i + 1 > max && ans <= K) {
                max = j - i + 1;
                L = i + 1;
                R = j + 1;
            }
        }
        System.out.println(L + " " + R);
    }
}
