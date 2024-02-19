package ModularArithmetic;
/*
It is Professor R's last class of his teaching career. Every time Professor R taught a class, he gave a special problem for the students to solve. You being his favourite student, put your heart into solving it one last time.

You are given two polynomials f(x)=a0+a1*x+⋯+a(n−1)*x^(n−1) and g(x)=b0+b1*x+⋯+b(m−1)*x^(m−1), with positive integral coefficients. It is guaranteed that the cumulative GCD of the coefficients is equal to 1 for both the given polynomials. In other words, gcd(a0,a1,…,an−1)=gcd(b0,b1,…,bm−1)=1. Let h(x)=f(x)⋅g(x). Suppose that h(x)=c0+c1*x+⋯+c(n+m−2)*x^(n+m−2).

You are also given a prime number p. Professor R challenges you to find any t such that ct isn't divisible by p. He guarantees you that under these conditions such t always exists. If there are several such t, output any of them.

 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FindCoefficientOf3rdPolynomialSuchThatCtIsNotDivisibleByPrimeP {
    public static void main(String ag[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i, j, k;
        String ip[] = br.readLine().trim().split(" ");
        int n = Integer.parseInt(ip[0]);
        int m = Integer.parseInt(ip[1]);
        int p = Integer.parseInt(ip[2]);
        int A[] = new int[n];
        int B[] = new int[m];

        String arr[] = br.readLine().trim().split(" ");
        for (i = 0; i < n; i++)
            A[i] = Integer.parseInt(arr[i]);

        String brr[] = br.readLine().trim().split(" ");
        for (i = 0; i < m; i++)
            B[i] = Integer.parseInt(brr[i]);

        for (i = 0; i < n; i++) {
            if (A[i] % p != 0)  //finding first coefficient in A which is not divisible by P
                break;
        }

        j = 0;
        for (j = 0; j < m; j++) {
            if (B[j] % p != 0)    //finding first coefficient in B which is not divisible by P
                break;
        }

        System.out.println(i + j);
    }

    public static long GCD(long a, long b) {
        if (b == 0)
            return a;
        return GCD(b, a % b);
    }
}
