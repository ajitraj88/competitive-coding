package BinarySearch;

/*
find all sequence from s1,s2,s3,s4,....sn formed as a result of concat of 2 sequences taken at a time and they satisfy a property i.e. in sx+sy concat ther is
atleast one pair such that i<j and ai<aj
*/

//hint total sequences = N^2- (total decreasing sequence formed as cocat)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ConcatAllPairWiseSequencesFindAiLessThanAjInHowMany {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> last = new ArrayList<>();
        boolean strictlyDecreasing[] = new boolean[N + 1];
        Arrays.fill(strictlyDecreasing, true);

        for (i = 1; i <= N; i++) {
            int len = sc.nextInt();
            int prev = sc.nextInt();
            int max = prev;
            int min = prev;
            for (j = 1; j < len; j++) {
                int curr = sc.nextInt();
                if (curr > prev) {
                    strictlyDecreasing[i] = false;
                }
                prev = curr;
                min = Math.min(min, curr);
            }
            if (strictlyDecreasing[i] == true) {
                first.add(max);
                last.add(min);
            }
        }

        Collections.sort(first);
        long ans = 0;
        for (i = 0; i < last.size(); i++) {
            int l = 0;
            int r = first.size() - 1;
            int value = last.get(i);
            while (l <= r) {
                int mid = (l + r) >> 1;
                if (first.get(mid) <= value)
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            ans += l;
        }
        ans = ((long) N * N) - ans;
        System.out.println(ans);
    }
}
