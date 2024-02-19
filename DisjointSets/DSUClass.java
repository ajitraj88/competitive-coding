package DisjointSets;

/*
Bear Limak examines a social network. Its main functionality is that two members can become friends (then they can talk with each other
and share funny pictures).

There are n members, numbered 1 through n. m pairs of members are friends. Of course, a member can't be a friend with themselves.

Let A-B denote that members A and B are friends. Limak thinks that a network is reasonable if and only if the following condition is
satisfied: For every three distinct members (X, Y, Z), if X-Y and Y-Z then also X-Z.

For example: if Alan and Bob are friends, and Bob and Ciri are friends, then Alan and Ciri should be friends as well.

Can you help Limak and check if the network is reasonable? Print "YES" or "NO" accordingly, without the quotes.

hint ---
The main observation is that you should print "YES" if the graph is a set of disjoint cliques (in each connected non-clique there
is a triple of vertices X,Y,Z that X-Y and Y-Z but not X-Z). To check if each connected component is a clique, you can run dfs and
count vertices and edges in the connected component — it's a clique if and only if edges=(vertices)*(vertices-1)/2.

*/

import java.util.Scanner;

public class DSUClass {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int N = sc.nextInt();
        int M = sc.nextInt();
        DSUEntity d = new DSUEntity(N);
        for (i = 1; i <= M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            d.union(a, b);
        }
        for (i = 1; i <= N; i++) {
            int vertices = d.getVertices(i);
            int edges = d.getEdges(i);
            if ((long) edges != ((long) (vertices) * (long) (vertices - 1) / 2)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}

class DSUEntity {
    Node arr[];

    DSUEntity(int N) {
        arr = new Node[N + 1];
        for (int i = 0; i <= N; i++) {
            arr[i] = new Node(i, 1, 0);
        }
    }

    public int find(int node) {
        if (arr[node].parent == node)
            return node;
        return arr[node].parent = find(arr[node].parent);
    }

    public void union(int x, int y) {
        int A = find(x);
        int B = find(y);

        if (arr[A].rank < arr[B].rank) {
            int temp = A;
            A = B;
            A = temp;
        }

        arr[A].edges++;

        if (A == B)
            return;

        arr[B].parent = A;
        arr[A].rank += arr[B].rank;
        arr[A].edges += arr[B].edges;
    }

    public int getVertices(int v) {
        return arr[find(v)].rank;
    }

    public int getEdges(int v) {
        return arr[find(v)].edges;
    }
}

class Node {
    int parent, rank, edges;

    Node(int p, int r, int e) {
        parent = p;
        rank = r;
        edges = e;
    }
}
