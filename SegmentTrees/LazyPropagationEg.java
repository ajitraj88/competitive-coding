package SegmentTrees;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LazyPropagationEg {
    public static void main(String ag[])throws Exception
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int i,j,k;
        int N=Integer.parseInt(br.readLine());

        String H[]=br.readLine().split(" ");
        long A[]=new long[N+1];
        for(i=1;i<=N;i++)
        {
            A[i]=Long.parseLong(H[i-1]);
        }
        SegmentTreeClass s=new SegmentTreeClass(N,A);
        int Q=Integer.parseInt(br.readLine());
        StringBuffer sb=new StringBuffer();
        while(Q-- >0)
        {
            String str[]=br.readLine().split(" ");
            if(str.length==3)
            {
                int L=Integer.parseInt(str[0])+1;
                int R=Integer.parseInt(str[1])+1;
                long V=Long.parseLong(str[2]);
                if(L<=R)
                {
                    s.update(1,N,L,R,1,V);
                }
                else
                {
                    s.update(1,N,L,N,1,V);
                    s.update(1,N,1,R,1,V);
                }
            }
            else
            {
                int L=Integer.parseInt(str[0])+1;
                int R=Integer.parseInt(str[1])+1;
                long res;
                if(L<=R)
                {
                    res=s.query(1,N,L,R,1);
                }
                else
                {
                    res=s.query(1,N,L,N,1);
                    res=Math.min(s.query(1,N,1,R,1),res);
                }
                sb.append(res+"\n");
            }
        }
        System.out.println(sb);
    }
}

class SegmentTreeClass
{
    long ST[];
    int N;
    long lazy[];
    SegmentTreeClass(int N,long A[])
    {
        this.N=N;
        ST=new long[4*(N+1)+2];
        lazy=new long[4*(N+1)+2];
        build(A,1,N,1);
    }
    public void build(long A[],int l,int r,int pos)
    {
        if(l>r)
            return;

        if(l==r)
        {
            ST[pos]=A[l];
            return;
        }

        int mid=(l+r)>>1;
        build(A,l,mid,2*pos);
        build(A,mid+1,r,2*pos+1);
        ST[pos]=Math.min(ST[2*pos],ST[2*pos+1]);
    }
    public void update(int l,int r,int qs,int qe,int pos,long value)
    {
        if(lazy[pos]!=0)
        {
            ST[pos]+=lazy[pos];
            if(l!=r)
            {
                lazy[2*pos]+=lazy[pos];
                lazy[2*pos+1]+=lazy[pos];
            }
            lazy[pos]=0;
        }

        if(l>r||qs>r||qe<l)
            return;

        if(l>=qs&&r<=qe)
        {
            ST[pos]+=value;
            if(l!=r)
            {
                lazy[2*pos]+=value;
                lazy[2*pos+1]+=value;
            }
            return;
        }

        int mid=(l+r)/2;
        update(l,mid,qs,qe,2*pos,value);
        update(mid+1,r,qs,qe,2*pos+1,value);
        ST[pos]=Math.min(ST[2*pos],ST[2*pos+1]);
    }
    public long query(int l,int r,int qs,int qe,int pos)
    {
        if(l>r||l>qe||r<qs)
            return Long.MAX_VALUE;

        if(lazy[pos]!=0)
        {
            ST[pos]+=lazy[pos];
            if(l!=r)
            {
                lazy[2*pos]+=lazy[pos];
                lazy[2*pos+1]+=lazy[pos];
            }
            lazy[pos]=0;
        }

        if(qs<=l&&r<=qe)
            return ST[pos];

        int mid=(l+r)/2;
        return Math.min(query(l,mid,qs,qe,2*pos),query(mid+1,r,qs,qe,2*pos+1));
    }
}