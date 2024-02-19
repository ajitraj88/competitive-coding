package Trie;

/*
Author has gone out of the stories about Vasiliy, so here is just a formal task description.

You are given q queries and a multiset A, initially containing only integer 0. There are three types of queries:

1."+ x" — add integer x to multiset A.
2."- x" — erase one occurrence of integer x from multiset A. It's guaranteed that at least one x is present in the multiset A before this query.
3."? x" — you are given integer x and need to compute the value , i.e. the maximum value of bitwise exclusive OR (also know as XOR) of integer x and some integer y from the multiset A.
Multiset is a set, where equal elements are allowed.

eg---- 	10
		+ 8
		+ 9
		+ 11
		+ 6
		+ 1
		? 3
		- 8
		? 3
		? 8
		? 11

ans---- 11
		10
		14
		13
*/

import java.util.Scanner;

public class TrieToFindXorQueries {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int T = sc.nextInt();
        Trie trie = new Trie();
        trie.insert(0);
        while (T-- > 0) {
            String str = sc.next();
            int value = sc.nextInt();
            if (str.equals("+")) {
                trie.insert(value);
            } else if (str.equals("-")) {
                trie.delete(value);
            } else {
                System.out.println(trie.query(value));
            }
        }
    }
}

class Trie {
    Node root;

    Trie() {
        root = new Node();
    }

    public void insert(int value) {
        Node curr = root;
        for (int i = 31; i >= 0; i--) {
            Node parent = curr;
            int X = ((1 << i) & value);
            if (X > 0) {
                if (curr.right == null)
                    curr.right = new Node();
                curr = curr.right;
            } else {
                if (curr.left == null)
                    curr.left = new Node();
                curr = curr.left;
            }
            curr.parent = parent;
        }
        curr.freq++;
    }

    public void delete(int value) {
        Node curr = root;
        for (int i = 31; i >= 0; i--) {
            int X = (1 << i) & value;
            if (X > 0)
                curr = curr.right;
            else
                curr = curr.left;
        }
        curr.freq--;
        //System.out.println(curr.freq+" "+value);
        if (curr.freq == 0) {
            if (curr.left == null && curr.right == null) {
                while (curr.left == null && curr.right == null && curr.parent != null && curr.freq == 0) {
                    Node parent = curr.parent;
                    if (parent.left == curr)
                        parent.left = null;
                    else parent.right = null;
                    curr = parent;
                }
            }
        }
    }

    public int query(int value) {
        int ans = 0;
        Node curr = root;

        for (int i = 31; i >= 0; i--) {
            int X = (1 << i) & value;
            if (X > 0) {
                if (curr.left != null) {
                    ans |= (1 << i);
                    curr = curr.left;
                } else
                    curr = curr.right;
            } else {
                if (curr.right != null) {
                    ans |= (1 << i);
                    curr = curr.right;
                } else
                    curr = curr.left;
            }
        }
        return ans;
    }
}

class Node {
    Node left, right, parent;
    int freq;

    Node() {
    }
}