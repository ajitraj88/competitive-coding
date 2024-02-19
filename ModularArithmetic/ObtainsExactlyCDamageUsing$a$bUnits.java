package ModularArithmetic;

/*
Dante is engaged in a fight with "The Savior". Before he can fight it with his sword, he needs to break its shields. He has two guns, Ebony and Ivory, each of them is able to perform any non-negative number of shots.

For every bullet that hits the shield, Ebony deals a units of damage while Ivory deals b units of damage. In order to break the shield Dante has to deal exactly c units of damage. Find out if this is possible.
*/

import java.util.Scanner;

public class ObtainsExactlyCDamageUsing$a$bUnits {
    static long gcd;
    static long x, y;

    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);

        long A = sc.nextLong();
        long B = sc.nextLong();
        long C = sc.nextLong();
        gcd(A, B);

        if (C % gcd != 0)
            System.out.println("No");

        else {
            x *= C / gcd;
            y *= C / gcd;
            if (x >= 0 && y >= 0)
                System.out.println("Yes");
            else if (x < 0) {
                while (x < 0) {
                    x += B / gcd;
                    y -= A / gcd;
                }
                if (x >= 0 && y >= 0)
                    System.out.println("Yes");
                else
                    System.out.println("No");
            } else {
                while (y < 0) {
                    x -= B / gcd;
                    y += A / gcd;
                }
                if (x >= 0 && y >= 0)
                    System.out.println("Yes");
                else
                    System.out.println("No");
            }

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
