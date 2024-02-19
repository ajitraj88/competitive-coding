package DisjointSets;

/*
Berland Government decided to improve relations with neighboring countries.
First of all, it was decided to build new roads so that from each city of Berland and
neighboring countries it became possible to reach all the others. There are n cities
in Berland and neighboring countries in total and exactly n - 1 two-way roads.
Because of the recent financial crisis, the Berland Government is strongly pressed
for money, so to build a new road it has to close some of the existing ones.
Every day it is possible to close one existing road and immediately build a new one.
Your task is to determine how many days would be needed to rebuild roads so that from
each city it became possible to reach all the others, and to draw a plan of closure of
old roads and building of new ones.

eg----  7
		1 2
		2 3
		3 1
		4 5
		5 6
		6 7

ans---- 1   ie one day is required
		3 1 3 7  ie remove road 3to1 and add road 3to7

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MakeAllPathsConnectedByRemovingAddingSomeRoadsUsingDsu {
    public static void main(String ag[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i, j, k;
        int N = Integer.parseInt(br.readLine());

        DSUNode arr[] = new DSUNode[N + 1];
        for (i = 0; i <= N; i++) {
            arr[i] = new DSUNode(i, 1);
        }
        ArrayList<Pair> close = new ArrayList<>();
        ArrayList<Pair> build = new ArrayList<>();
        for (i = 1; i < N; i++) {
            String S[] = br.readLine().split(" ");
            int a = Integer.parseInt(S[0]);
            int b = Integer.parseInt(S[1]);
            int X = find(a, arr);
            int Y = find(b, arr);
            if (X != Y) {
                if (arr[X].rank < arr[Y].rank) {
                    int temp = X;
                    X = Y;
                    Y = temp;
                }
                arr[Y].parent = X;
                if (arr[Y].rank == arr[X].rank)
                    arr[X].rank++;
            } else {
                close.add(new Pair(a, b));
            }
        }

        for (i = 2; i <= N; i++) {
            int X = find(1, arr);
            int Y = find(i, arr);
            if (X != Y) {
                if (arr[X].rank < arr[Y].rank) {
                    int temp = X;
                    X = Y;
                    Y = temp;
                }
                arr[Y].parent = X;
                if (arr[Y].rank == arr[X].rank)
                    arr[X].rank++;
                build.add(new Pair(1, i));
            }
        }
        System.out.println(build.size());
        for (i = 0; i < build.size(); i++) {
            System.out.println(close.get(i).a + " " + close.get(i).b + " " + build.get(i).a + " " + build.get(i).b);
        }
    }

    public static int find(int a, DSUNode arr[]) {

        if (arr[a].parent == a)
            return a;
        return arr[a].parent = find(arr[a].parent, arr);
    }


}

class DSUNode
{
    int parent;
    int rank;
    DSUNode(int p,int r)
    {
        parent=p;
        rank=r;
    }
}
class Pair
{
    int a,b;
    Pair(int p,int q)
    {
        a=p;
        b=q;
    }
}