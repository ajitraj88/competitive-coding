package DynamicProgramming;

/*
Find the no formed as a result of removing some digits which in the end is divisible by 8, print any of the solution
eg-
3454
ans-
YES, 344
*/

import java.util.Arrays;
import java.util.Scanner;

public class FindNoWhichIsDivisibleBy8ByRemovingAnyNoOfDigits {
    static int A[];
    static Boolean dp[][];
    static int N;
    public static void main(String ag[])
    {
        Scanner sc=new Scanner(System.in);
        int i,j,k;
        char arr[]=sc.next().toCharArray();

        N=arr.length;
        A=new int[N];
        for(i=0;i<N;i++)
            A[i]=arr[i]-'0';

        dp=new Boolean[101][10];
        System.out.println(find(0,0,0)?"YES\n"+trace(0,0,0):"NO");
    }
    public static Boolean find(int id,int mod,int taken)
    {
        if(id==N)
            return (mod==0&&taken==1)?true:false;

        if(dp[id][mod]!=null)
            return dp[id][mod];

        return dp[id][mod]=find(id+1,(mod*10+A[id])%8,1)||find(id+1,mod,taken);
    }
    public static String trace(int id,int mod,int taken)
    {
        if(id==N)
            return "";

        Boolean curr=find(id,mod,taken);
        if(curr==find(id+1,(mod*10+A[id])%8,1))
            return A[id]+trace(id+1,(mod*10+A[id])%8,1);
        else
            return trace(id+1,mod,taken);
    }
}

class BottomUpSolution
{
    public static void main(String ag[])
    {
        Scanner sc=new Scanner(System.in);
        int i,j,k;
        char arr[]=sc.next().toCharArray();
        int N=arr.length;

        boolean dp[][]=new boolean[101][10];
        String path[][]=new String[101][10];
        for(i=0;i<=100;i++)
            Arrays.fill(path[i],"");
        dp[0][0]=true;
        dp[0][(arr[0]-'0')%8]=true;
        path[0][(arr[0]-'0')%8]=""+arr[0];

        for(i=1;i<N;i++)
        {
            dp[i][arr[i]-'0']=true;
            for(j=0;j<8;j++)
            {
                if(dp[i-1][j])
                {
                    dp[i][j]=true;
                    dp[i][(j*10+arr[i]-'0')%8]=true;
                    path[i][j]=path[i-1][j];
                    path[i][(j*10+arr[i]-'0')%8]=path[i-1][j]+arr[i];
                }
            }
        }

        for(i=0;i<N;i++)
        {
            if(dp[i][0]&&path[i][0].length()!=0)
            {
                System.out.println("YES");
                System.out.println(path[i][0]);
                return;
            }
        }
        System.out.println("NO");
    }

}