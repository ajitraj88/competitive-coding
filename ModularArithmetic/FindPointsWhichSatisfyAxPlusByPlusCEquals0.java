package ModularArithmetic;

/*
A line on the plane is described by an equation Ax + By + C = 0. You are to find any point on this line, whose coordinates are integer numbers from  - 5·10^18 to 5·10^18 inclusive, or to find out that such points do not exist.

eg---- 2 5 3
ans---- 6 -3
*/

import java.util.Scanner;

public class FindPointsWhichSatisfyAxPlusByPlusCEquals0 {
    static long gcd;
    static long x, y;

    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);

        long A = sc.nextLong();
        long B = sc.nextLong();
        long C = sc.nextLong();
        gcd(A, B);
        if (C % gcd != 0)
            System.out.println(-1);
        else {
            C *= -1;
            x *= C / gcd;
            y *= C / gcd;
            System.out.println(x + " " + y);
        }

    }

    public static void gcd(long A, long B) {
        if (B == 0) {
            x = 1;
            y = 0;
            gcd = A;
        } else {
            gcd(B, A % B);
            long temp = x;
            x = y;
            y = temp - (A / B) * y;
        }
    }
}
