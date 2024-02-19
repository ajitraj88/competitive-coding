package Graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BipartiteChecking {
    static ArrayList<Integer> adj[];
    static boolean visited[];
    static int N;
    static int color[];

    public static void main(String ag[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i, j, k;

        int N = Integer.parseInt(br.readLine());
        char A[] = br.readLine().toCharArray();

        visited = new boolean[N];
        color = new int[N];
        adj = new ArrayList[N];
        for (i = 0; i < N; i++)
            adj[i] = new ArrayList<>();

        for (i = 0; i < N; i++) {
            for (j = 0; j < i; j++) {
                if (A[j] > A[i]) {
                    adj[j].add(i);
                    adj[i].add(j);
                }
            }
        }

        boolean flag = true;
        Arrays.fill(color, -1);
        for (i = 0; i < N; i++) {
            if (color[i] == -1) {
                flag = BFS(i);
                if (!flag)
                    break;
            }
        }
        if (!flag)
            System.out.println("NO");
        else {
            StringBuilder sb = new StringBuilder();
            System.out.println("YES");
            for (i = 0; i < N; i++)
                sb.append(color[i]);
            System.out.println(sb);
        }
    }

    public static boolean BFS(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        color[node] = 0;
        while (!q.isEmpty()) {
            int curr = q.poll();
            visited[curr] = true;
            Iterator<Integer> itr = adj[curr].iterator();
            while (itr.hasNext()) {
                int next = itr.next();
                if (color[next] == -1) {
                    color[next] = 1 - color[curr];
                    q.add(next);
                } else {
                    if (color[next] == color[curr])
                        return false;
                }
            }
        }
        return true;
    }
}
