package KMPHashingZFnManachar;

/* for a given string find all prefixes which occurs as suffixes in a string and in output print length of that prefix followed by no. of times
it occurs in a string
eg-- ABACABA
o/p--	3
		1 4  ie A
		3 2  ie ABA
		7 1  ie ABACABA
*/
import java.util.Scanner;

public class ZAlgo {
    public static void main(String ag[])
    {
        Scanner sc=new Scanner(System.in);
        int i,j,k;
        String str=sc.next();
        int N=str.length();
        int Z[]=new int[N];
        int left=0;
        int right=0;
        for(i=1;i<N;i++)
        {
            if(i>right)
            {
                left=right=i;
                while(right<N&&str.charAt(right)==str.charAt(right-left))
                    right++;

                Z[i]=right-left;
                right--;
            }
            else
            {
                int K1=i-left;
                if(Z[K1]<right-i+1)
                    Z[i]=Z[K1];

                else
                {
                    left=i;
                    while(right<N&&str.charAt(right)==str.charAt(right-left))
                        right++;

                    Z[i]=right-left;
                    right--;
                }
            }
        }

        int freq[]=new int[N+1];
        int cnt=0;
        for(i=0;i<N;i++)
        {
            freq[Z[i]]++;
        }
        for(i=N-1;i>=0;i--)
        {
            freq[i]+=freq[i+1];
            if(Z[i]==N-i)
                cnt++;
        }
        cnt++;
        System.out.println(cnt);
        for(i=N-1;i>=0;i--)
        {
            if(Z[i]==N-i)
                System.out.println(Z[i]+" "+(freq[Z[i]]+1));
        }
        System.out.println(N+" "+1);

    }
}
