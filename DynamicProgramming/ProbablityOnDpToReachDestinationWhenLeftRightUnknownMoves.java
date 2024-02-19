package DynamicProgramming;

/*
Dreamoon is standing at the position 0 on a number line. Drazil is sending a list of commands through Wi-Fi to Dreamoon's smartphone and Dreamoon follows them.

Each command is one of the following two types:

Go 1 unit towards the positive direction, denoted as '+'
Go 1 unit towards the negative direction, denoted as '-'
But the Wi-Fi condition is so poor that Dreamoon's smartphone reports some of the commands can't be recognized and Dreamoon knows that some of them might even be wrong though successfully recognized. Dreamoon decides to follow every recognized command and toss a fair coin to decide those unrecognized ones (that means, he moves to the 1 unit to the negative or positive direction with the same probability 0.5).

You are given an original list of commands sent by Drazil and list received by Dreamoon. What is the probability that Dreamoon ends in the position originally supposed to be final by Drazil's commands?
*/

import java.util.Scanner;

public class ProbablityOnDpToReachDestinationWhenLeftRightUnknownMoves {
    public static void main(String ag[]) {
        int i, j, k;
        Scanner sc = new Scanner(System.in);
        String sent = sc.next();
        String received = sc.next();
        int N = sent.length();
        int dest = 0;
        for (i = 0; i < N; i++) {
            if (sent.charAt(i) == '+')
                dest++;
            else
                dest--;
        }
        double denominator = 1;
        for (i = 0; i < N; i++) {
            if (received.charAt(i) == '?')
                denominator *= 2;
        }

        int numerator = find(received, 0, N, dest, 0);
        double NR = (double) numerator;

        if (numerator == 0)
            System.out.println(String.format("%.12f", 0.0));
        else
            System.out.println(String.format("%.12f", (NR / denominator)));

    }

    public static int find(String received, int id, int N, int dest, int curr) {
        if (id == N) {
            if (curr == dest)
                return 1;
            else
                return 0;
        }

        int total = 0;

        if (received.charAt(id) == '+')
            total += find(received, id + 1, N, dest, curr + 1);

        else if (received.charAt(id) == '-')
            total += find(received, id + 1, N, dest, curr - 1);

        else
            total += find(received, id + 1, N, dest, curr + 1) + find(received, id + 1, N, dest, curr - 1);

        return total;
    }
}
