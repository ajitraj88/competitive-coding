package TernarySearch;

/*
You have to restore the wall. The wall consists of N pillars of bricks, the height of the i-th pillar is initially equal to hi, the height is measured in number of bricks. After the restoration all the N pillars should have equal heights.

You are allowed the following operations:

put a brick on top of one pillar, the cost of this operation is A;
remove a brick from the top of one non-empty pillar, the cost of this operation is R;
move a brick from the top of one non-empty pillar to the top of another pillar, the cost of this operation is M.
You cannot create additional pillars or ignore some of pre-existing pillars even if their height becomes 0.

What is the minimal total cost of restoration, in other words, what is the minimal total cost to make all the pillars of equal height?

*/

import java.util.Scanner;

public class MakeAllPillarsHeightEqualWithMinCost {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long add = sc.nextLong();
        long remove = sc.nextLong();
        long move = sc.nextLong();
        long A[] = new long[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextLong();
        }

        long lb = 0;
        long ub = (int) 1e9;
        long ans = Long.MAX_VALUE;

        while (ub - lb >= 3) {
            long mid1 = lb + (ub - lb) / 3;
            long mid2 = ub - (ub - lb) / 3;

            long X = find(mid1, add, remove, move, A);
            long Y = find(mid2, add, remove, move, A);
            //System.out.println(X+" "+Y);
            if (ans > X) {
                ans = X;
            }
            if (ans > Y) {
                ans = Y;
            }
            if (X <= Y) {
                ub = mid2;
            } else {
                lb = mid1;
            }
        }

        for (long i = lb; i <= ub; i++) {
            ans = Math.min(ans, find(i, add, remove, move, A));
        }
        System.out.println(ans);
    }

    public static long find(long mid, long add, long remove, long move, long A[]) {
        long bricks_to_add = 0;
        long bricks_to_remove = 0;
        for (int i = 0; i < A.length; i++) {
            bricks_to_add += Math.max(0, mid - A[i]);
        }
        for (int i = 0; i < A.length; i++) {
            bricks_to_remove += Math.max(0, A[i] - mid);
        }

        long cost1 = (bricks_to_add * add) + (bricks_to_remove * remove);
        long min = Math.min(bricks_to_add, bricks_to_remove);
        long cost2 = (min * move) + ((bricks_to_add - min) * add) + ((bricks_to_remove - min) * remove);

        return Math.min(cost1, cost2);
    }
}
