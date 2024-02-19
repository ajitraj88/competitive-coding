package DynamicProgramming;

/*
Your friend Mishka and you attend a calculus lecture. Lecture lasts n minutes. Lecturer tells ai theorems during the i-th minute.

Mishka is really interested in calculus, though it is so hard to stay awake for all the time of lecture. You are given an array t
 of Mishka's behavior. If Mishka is asleep during the i-th minute of the lecture then ti will be equal to 0, otherwise it will be
  equal to 1. When Mishka is awake he writes down all the theorems he is being told — ai during the i-th minute. Otherwise he writes nothing.

You know some secret technique to keep Mishka awake for k minutes straight. However you can use it only once. You can start using it at
 the beginning of any minute between 1 and n - k + 1. If you use it on some minute i then Mishka will be awake during minutes j such
 that  and will write down all the theorems lecturer tells.

You task is to calculate the maximum number of theorems Mishka will be able to write down if you use your technique only once to wake him up.

eg----  6 3
		1 3 5 2 5 4
		1 1 0 1 0 0
ans---- 16
In the sample case the better way is to use the secret technique at the beginning of the third minute. Then the number of theorems
 Mishka will be able to write down will be equal to 16.
*/

import java.util.Scanner;

public class MaxLecturesThatCanBeSeenByUsingASpellToSeeKConsecutiveLecturesDuringSleep {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int K = sc.nextInt();

        int A[] = new int[N + 1];
        for (i = 1; i <= N; i++)
            A[i] = sc.nextInt();

        int state[] = new int[N + 1];
        for (i = 1; i <= N; i++)
            state[i] = sc.nextInt();

        int pre[] = new int[N + 1];
        long ans = 0;
        for (i = 1; i <= N; i++) {
            if (state[i] == 1) {
                ans += A[i];
                pre[i] = pre[i - 1];
            } else {
                pre[i] = pre[i - 1] + A[i];
            }
        }
        int max = 0;
        for (i = 1; i <= N - K + 1; i++)
            max = Math.max(max, pre[i + K - 1] - pre[i - 1]);

        System.out.println(max + ans);


    }
}
