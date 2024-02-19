package DisjointSets;

/*
Given 3 strings A,B,C . Characters in the same indexes of string A & B are equivalent.
If a in A is equivalent to b in B and b and character b & c are equivalent then character a & c are equivalent too.
You are given string C and are required to construct lexicographically minimum string using equivalent characters.

eg----  abc
		xyz
		yzp

ans---- bcp

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConstructLexicographicallyMinimalStringUsingDSU {
    public static void main(String ag[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i, j, k;
        String A = br.readLine();
        String B = br.readLine();
        String C = br.readLine();

        int N = A.length();
        DSU arr[] = new DSU[26];
        for (i = 0; i < 26; i++) {
            arr[i] = new DSU(i, 0, i);
        }
        for (i = 0; i < N; i++) {
            int a = A.charAt(i) - 'a';
            int b = B.charAt(i) - 'a';
            int X = find(a, arr);
            int Y = find(b, arr);
            if (X != Y) {
                if (arr[X].rank < arr[Y].rank) {
                    int temp = X;
                    X = Y;
                    Y = temp;
                }
                arr[Y].parent = X;
                arr[X].ch = Math.min(arr[Y].ch, arr[X].ch);
                if (arr[Y].rank == arr[X].rank)
                    arr[X].rank++;
            }
        }
        StringBuilder ans = new StringBuilder();
        for (i = 0; i < C.length(); i++) {
            int X = C.charAt(i) - 'a';
            int Y = find(X, arr);
            char ch = (char) (arr[Y].ch + 97);
            ans.append(ch);
        }
        System.out.println(ans);
    }

    public static int find(int a, DSU arr[]) {
        if (arr[a].parent == a)
            return a;
        return arr[a].parent = find(arr[a].parent, arr);
    }
}

class DSU {
    int parent;
    int rank;
    int ch;

    DSU(int p, int r, int c) {
        parent = p;
        rank = r;
        ch = c;
    }
}
