package Bits;

/* A no is called P binary if it can be written as 2^x+P,
 eg . For example, some −9-binary ("minus nine" binary) numbers are: −8 (minus eight), 7 and 1015 (−8=2^0−9, 7=2^4−9, 1015=2^10−9).
 If it is impossible to represent n as the sum of any number of p-binary numbers, print a single integer −1. Otherwise, print the
 smallest possible number of summands. */


/*Hint :

 say N=(summation i=(1,K))[2^(xi)-P], then N-Kp=(summation i=(1,K))[2^(xi)]
 In particular, n−kp has to be at least k.

Consider the binary representation of n−kp. If it has more than k bits equal to 1, there is no way we can split it into k powers of two. Otherwise,
 we can start by taking the binary representation, and if it contains less than k powers, we can always split larger powers into two smaller ones.

We can now check all values of k starting from the smallest. If n≥31p+31, then the answer will not exceed 31 since n−31p is less than 231,
hence is always representable with 31 powers.
Otherwise, we have n−31(p+1)<0. Since n>0, it means that p+1<0, and n−kp<k for all k>31, thus the answer does not exist.
*/

import java.util.Scanner;

public class PBinary {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        long N = sc.nextLong();
        long P = sc.nextLong();

        int ans = -1;
        for (i = 1; i <= 32; i++) {
            long rem = N - i * P;
            int count = countSetBits(rem);
            if (count <= i && rem >= i) {
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }

    public static int countSetBits(long N) {
        int cnt = 0;
        while (N > 0) {
            if ((N & 1) > 0)
                cnt++;
            N = N >> 1;
        }
        return cnt;
    }
}
