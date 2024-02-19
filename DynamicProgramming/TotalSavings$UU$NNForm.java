package DynamicProgramming;

/* Givem a string where uu is treated as w or uu and nn is treated as m or nn. Find total no Strings that are possible to be formed. If a string contains m or w the ans=
 */

import java.util.Scanner;

public class TotalSavings$UU$NNForm {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        char arr[] = sc.next().toCharArray();
        int N = arr.length;
        int mod = (int) 1e9 + 7;

        int dp[] = new int[(int) 1e5 + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (i = 3; i <= (int) 1e5; i++) {
            dp[i] = (dp[i - 1] % mod + dp[i - 2] % mod) % mod;
        }

        int pre[] = new int[N];
        boolean flag = false;

        pre[0] = (arr[0] == 'u' || arr[0] == 'n') ? 1 : 0;
        flag = (arr[0] == 'w' || arr[0] == 'm') ? true : false;

        for (i = 1; i < N; i++) {
            if (arr[i] == 'u' && arr[i - 1] == 'u') {
                pre[i] = pre[i - 1] + 1;
                pre[i - 1] = 0;
            } else if (arr[i] == 'n' && arr[i - 1] == 'n') {
                pre[i] = pre[i - 1] + 1;
                pre[i - 1] = 0;
            } else if (arr[i] == 'u' || arr[i] == 'n') {
                pre[i] = 1;
            } else if (arr[i] == 'm' || arr[i] == 'w') {
                flag = true;
                break;
            }
        }

        long ans = 1;
        if (flag)
            System.out.println(0);
        else {
            for (i = 0; i < N; i++) {
                if (pre[i] != 0)
                    ans = (ans % mod * dp[pre[i]] % mod) % mod;
            }
            System.out.println(ans % mod);
        }
    }
}
