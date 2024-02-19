package Greedy;

/*
Remove min. no. of character from a string so that it doesn't have any one or two in it
eg-twone ans remove 2nd index character
*/

import java.util.ArrayList;
import java.util.Scanner;

public class RemoveMinNoOfCharactersSoThatThereAreNo1Or2InString {
    public static void main(String ag[]) {
        Scanner sc = new Scanner(System.in);
        int i, j, k;
        int T = sc.nextInt();
        while (T-- > 0) {
            char str[] = sc.next().toCharArray();
            ;
            int N = str.length;
            int cnt = 0;
            ArrayList<Integer> ans = new ArrayList<>();
            for (i = 0; i < N; i++) {
                if (str[i] == 'o') {
                    if (i + 2 < N && str[i + 1] == 'n' && str[i + 2] == 'e') {
                        ans.add(i + 2);
                        i += 2;
                        cnt++;
                    }
                } else if (str[i] == 't') {
                    if (i + 4 < N && str[i + 1] == 'w' && str[i + 2] == 'o' && str[i + 3] == 'n' && str[i + 4] == 'e') {
                        ans.add(i + 3);
                        i += 4;
                        cnt++;
                    } else if (i + 2 < N && str[i + 1] == 'w' && str[i + 2] == 'o') {
                        ans.add(i + 2);
                        i += 2;
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);
            for (i = 0; i < ans.size(); i++)
                System.out.print(ans.get(i) + " ");
            System.out.println();
        }
    }
}
