package Bits;
/*
Petr has just bought a new car. He's just arrived at the most known Petersburg's petrol station to refuel it when he suddenly discovered that the petrol tank is secured with a combination lock! The lock has a scale of 360 degrees and a pointer which initially points at zero:

Petr called his car dealer, who instructed him to rotate the lock's wheel exactly n times. The i-th rotation should be ai degrees, either clockwise or counterclockwise, and after all n rotations the pointer should again point at zero.

This confused Petr a little bit as he isn't sure which rotations should be done clockwise and which should be done counterclockwise. As there are many possible ways of rotating the lock, help him and find out whether there exists at least one, such that after all n rotations the pointer will point at zero again.

*/

import java.util.Scanner;

public class CarSpeed360DegreeClockAnticlock {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int A[] = new int[N];
        for (i = 0; i < N; i++)
            A[i] = sc.nextInt();

        if (find(0, 0, N, A) == 1)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    public static int find(int sum, int id, int N, int A[]) {
        if (id == N) {
            if (Math.abs(sum) % 360 == 0)
                return 1;
            return 0;
        }

        int X = find(sum - A[id], id + 1, N, A);
        int Y = find(sum + A[id], id + 1, N, A);
        return Math.max(X, Y);
    }
}
