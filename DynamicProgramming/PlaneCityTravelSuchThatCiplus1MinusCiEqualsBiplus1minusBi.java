package DynamicProgramming;

import java.util.HashMap;
import java.util.Scanner;

/*
make journey starting from any city i to any no of citites such that beauty values of citites are in increasing order & you are to maximize this
beauty value

There are some additional constraints on the sequence of cities Tanya visits. Each city i has a beauty value bi associated with it. If there is only one city in Tanya's journey, these beauty values imply no additional constraints. But if there are multiple cities in the sequence, then for any pair of adjacent cities c(i) and c(i+1), the condition c(i+1)−c(i)=b(ci+1)−b(ci) must hold.

eg---- 	7
		8 9 26 11 12 29 14
ans---- 55 (ie 26+29)
*/
public class PlaneCityTravelSuchThatCiplus1MinusCiEqualsBiplus1minusBi {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        HashMap<Long, Long> dp = new HashMap<>();
        long A[] = new long[N];
        for (i = 0; i < N; i++) {
            A[i] = sc.nextLong();
        }
        long ans = 0;
        for (i = 0; i < N; i++) {
            dp.put(A[i] - i, dp.getOrDefault(A[i] - i, 0L) + A[i]);
            ans = Math.max(ans, dp.get(A[i] - i));
        }
        System.out.println(ans);
    }
}
