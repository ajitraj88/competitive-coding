package DynamicProgramming;

/* A no. is lucky if it does not have any digit other than 0<=x,y<=9 find all lucky nos.till N*/

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class LuckyNoUsingQueue {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        Queue<Long> q = new LinkedList<>();
        for (long i = 1; i <= Math.min(9, N); i++) {
            q.add(i);
        }
        int cnt = 0;
        while (!q.isEmpty()) {
            Long t = q.poll();
            cnt++;
            for (long i = 0; i <= 9; i++) {
                long X = t * 10 + i;
                if (X <= N && isValid(X))
                    q.add(X);
            }
        }
        System.out.println(cnt);
    }

    public static boolean isValid(long N) {
        HashSet<Long> hs = new HashSet<>();
        while (N > 0) {
            hs.add(N % 10);
            N /= 10;
        }
        if (hs.size() > 2)
            return false;
        return true;
    }
}
