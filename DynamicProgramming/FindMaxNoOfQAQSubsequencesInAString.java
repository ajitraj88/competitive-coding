package DynamicProgramming;

/*
Bort wants to know how many subsequences "QAQ" are in the string Diamond has given. Note that the letters "QAQ" don't have to be consecutive, but the order of letters should be exact.


eg---- QAQAQYSYIOIWIN
ans---- 4
*/

import java.util.Arrays;
import java.util.Scanner;

public class FindMaxNoOfQAQSubsequencesInAString {
    public static void main(String ag[])
    {
        Scanner sc=new Scanner(System.in);
        int i,j,k;
        String str=sc.next();
        int N=str.length();
        int dp[][]=new int[N][3];
        for(i=0;i<N;i++)
            Arrays.fill(dp[i],-1);
        System.out.println(find(dp,0,0,"QAQ",str));
    }
    public static int find(int dp[][],int i,int j,String req,String str)
    {

        if(j==3)
            return 1;
        if(i>=str.length())
            return 0;

        int total=0;
        if(str.charAt(i)==req.charAt(j))
            total+=find(dp,i+1,j+1,req,str)+find(dp,i+1,j,req,str);
        else
            total+=find(dp,i+1,j,req,str);

        return dp[i][j]=total;
    }
}

class SolutionBottomUp
{
    public static void main(String ag[])
    {
        Scanner sc=new Scanner(System.in);
        int i,j,k;
        char a[]=sc.next().toCharArray();
        int N=a.length;
        char b[]={'Q','A','Q'};
        int dp[][]=new int[N+1][4];

        for(i=0;i<=N;i++)
            dp[i][0]=1;

        for(i=1;i<=N;i++)
        {
            for(j=1;j<=3;j++)
            {
                if(a[i-1]==b[j-1])
                    dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
                else
                    dp[i][j]=dp[i-1][j];
            }
        }

        System.out.println(dp[N][3]);
    }
}
