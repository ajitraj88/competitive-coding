package Bits;

/*
A little girl loves problems on bitwise operations very much. Here's one of them.

You are given two integers l and r. Let's consider the values of  x^y for all pairs of integers a and b (l ≤ a ≤ b ≤ r). Your task is to find
the maximum value among all considered ones.

Expression  means applying bitwise excluding or operation to integers x and y. The given operation exists in all modern programming languages,
for example, in languages C++ and Java it is represented as "^", in Pascal — as «xor»

*/

import java.util.Scanner;

public class MaxBitwiseXorOfAPairInLToRRange {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        long L = sc.nextLong();
        long R = sc.nextLong();

        int posL = findHighestSetBit(L);
        int posR = findHighestSetBit(R);

        long ans = 0;
        if (posL != posR) {
            ans = (1L << (posR + 1)) - 1;
        } else {
            for (i = 63; i >= 0; i--) {
                if ((R & (1L << i)) >= 1 && (L & (1L << i)) == 0) {
                    ans = (1L << (i + 1)) - 1;
                    break;
                }
            }
        }
        System.out.println(ans);
    }

    public static int findHighestSetBit(long N) {
        for (int i = 63; i >= 0; i--) {
            if ((N & (1L << i)) >= 1)
                return i;
        }
        return 0;
    }
}
