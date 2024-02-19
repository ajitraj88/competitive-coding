package SegmentTrees;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BitwiseORXORSegmentTree {
    public static void main(String ag[])throws Exception
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int i,j,k;
        String H[]=br.readLine().split(" ");
        int N=Integer.parseInt(H[0]);
        int M=Integer.parseInt(H[1]);

        int level=N;
        N=(int)Math.pow(2,N);
        long A[]=new long[N+1];

        String str[]=br.readLine().split(" ");
        for(i=1;i<=N;i++)
        {
            A[i]=Integer.parseInt(str[i-1]);
        }
        SegmentTree s=new SegmentTree(N,A,level);
        StringBuilder ans=new StringBuilder();
        for(i=1;i<=M;i++)
        {
            String u[]=br.readLine().split(" ");
            int id=Integer.parseInt(u[0]);
            long value=Long.parseLong(u[1]);
            s.update(1,N,id,value,1,level);
            // System.out.println(Arrays.toString(s.ST));
            ans.append(s.ST[1]+"\n");
        }
        System.out.println(ans);
    }
}

class SegmentTree
{
    long ST[];
    int N;
    SegmentTree(int N,long A[],int l)
    {
        this.N=N;
        int level=l;
        ST=new long[4*(N+1)+2];
        build(1,N,A,1,level);
    }
    public void build(int l,int r,long A[],int pos,int level)
    {
        if(l==r)
        {
            ST[pos]=A[l];
            return;
        }
        else
        {
            int mid=(l+r)>>1;
            build(l,mid,A,2*pos,level-1);
            build(mid+1,r,A,2*pos+1,level-1);

            if((level&1)==0)
                ST[pos]=ST[2*pos]^ST[2*pos+1];
            else
                ST[pos]=ST[2*pos]|ST[2*pos+1];
        }
    }
    public void update(int l,int r,int id,long val,int pos,int level)
    {
        if(l>r||l>id||id>r)
            return;

        if(l==r)
        {
            ST[pos]=val;
            return;
        }

        int mid=(l+r)/2;
        update(l,mid,id,val,2*pos,level-1);
        update(mid+1,r,id,val,2*pos+1,level-1);

        if((level&1)==0)
            ST[pos]=ST[2*pos]^ST[2*pos+1];
        else
            ST[pos]=ST[2*pos]|ST[2*pos+1];
    }
}