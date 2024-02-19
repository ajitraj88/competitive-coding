package DynamicProgramming;

/*
You are given an array a consisting of n integers. Each ai is one of the six following numbers: 4,8,15,16,23,42.

Your task is to remove the minimum number of elements to make this array good.

An array of length k is called good if k is divisible by 6 and it is possible to split it into k6 subsequences 4,8,15,16,23,42.

Examples of good arrays:

[4,8,15,16,23,42] (the whole array is a required sequence);
[4,8,4,15,16,8,23,15,16,42,23,42] (the first sequence is formed from first, second, fourth, fifth, seventh and tenth elements and the second one is formed from remaining elements);
[] (the empty array is good).
Examples of bad arrays:

[4,8,15,16,42,23] (the order of elements should be exactly 4,8,15,16,23,42);
[4,8,15,16,23,42,4] (the length of the array is not divisible by 6);
[4,8,15,16,23,42,4,8,15,16,23,23] (the first sequence can be formed from first six elements but the remaining array cannot form the required sequence).

eg---- 	5
		4 8 15 16 23
ans---- 5

eg---- 	15
		4 8 4 8 15 16 8 16 23 15 16 4 42 23 42
ans---- 3

*/

import java.util.Scanner;

public class SplitArrayIntoGoodSubsequencesOf6Sizes {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int A[] = new int[N];
        for (i = 0; i < N; i++)
            A[i] = sc.nextInt();

        int freq[] = new int[7];
        for (i = 0; i < N; i++) {
            if (A[i] == 4)
                freq[1]++;

            else if (A[i] == 8 && freq[2] < freq[1])
                freq[2]++;

            else if (A[i] == 15 && freq[3] < freq[2])
                freq[3]++;

            else if (A[i] == 16 && freq[4] < freq[3])
                freq[4]++;

            else if (A[i] == 23 && freq[5] < freq[4])
                freq[5]++;

            else if (A[i] == 42 && freq[6] < freq[5])
                freq[6]++;
        }

        System.out.println(N - freq[6] * 6);

    }
}
