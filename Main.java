import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        int k = 3;
        int w = 0;
        int[] profit = {1, 2, 3};
        int[] capital = {0, 1, 2};
        int N = profit.length;

        int availableCapital = w;
        PriorityQueue<Integer> minCapital = new PriorityQueue<>((a, b) -> capital[a] - capital[b]);
        PriorityQueue<Integer> maxProfit = new PriorityQueue<>((a, b) -> profit[b] - profit[a]);

        for (int i = 0; i < N; i++) {
            minCapital.add(i);
        }

        for (int i = 0; i < k; i++) {
            while (!minCapital.isEmpty() && availableCapital >= capital[minCapital.peek()]) {
                maxProfit.add(minCapital.poll());
            }

            if (maxProfit.isEmpty()) {
                break;
            }

            availableCapital += profit[maxProfit.poll()];
        }
        System.out.println(availableCapital);
    }
}