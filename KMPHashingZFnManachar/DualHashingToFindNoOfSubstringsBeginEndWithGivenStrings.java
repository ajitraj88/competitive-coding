package KMPHashingZFnManachar;

/*

Long ago, when Petya was a schoolboy, he was very much interested in the Petr# language grammar. During one lesson Petya got interested in the following question: how many different continuous substrings starting with the s(begin) and ending with the s(end) (it is possible s(begin) = (send)), the given string t has. Substrings are different if and only if their contents aren't equal, their positions of occurence don't matter. Petya wasn't quite good at math, that's why he couldn't count this number. Help him!

eg---- abababab
	   a
	   b
ans---- 4
*/

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;

public class DualHashingToFindNoOfSubstringsBeginEndWithGivenStrings {
    static HashSet<Long> hs = new HashSet<>();

    public static void main(String ag[]) throws Exception {
        FastReader sc = new FastReader(System.in);
        int i, j, k;

        String str = sc.next();
        String st = sc.next();
        String ed = sc.next();
        char A[] = str.toCharArray();
        char B[] = st.toCharArray();
        char C[] = ed.toCharArray();

        int N = A.length;

        dualHash h1 = new dualHash(A);
        dualHash h2 = new dualHash(B);
        dualHash h3 = new dualHash(C);

        long hashA = h2.getHash(0, B.length - 1);
        long hashB = h3.getHash(0, C.length - 1);

        HashSet<Integer> start = new HashSet<>();
        HashSet<Integer> end = new HashSet<>();

        for (i = 0; i <= A.length - st.length(); i++) {
            long X = h1.getHash(i, i + st.length() - 1);
            if (X == hashA)
                start.add(i);
        }
        for (i = 0; i <= A.length - ed.length(); i++) {
            long X = h1.getHash(i, i + ed.length() - 1);
            if (X == hashB)
                end.add(i + ed.length() - 1);
        }

        long ans[] = new long[start.size() * end.size()];
        i = 0;
        for (Integer x : start) {
            for (Integer y : end) {
                if (y - x + 1 >= Math.max(ed.length(), st.length())) {
                    long HASH = h1.getHash(x, y);
                    ans[i++] = HASH;
                }

            }
        }
        long arr[] = Arrays.copyOf(ans, i);
        Arrays.sort(arr);
        long cnt = 0;
        for (i = 0; i < arr.length; i++) {
            if (i == 0 || (arr[i] != arr[i - 1]))
                cnt++;
        }
        System.out.println(cnt);
    }
}

class dualHash {
    int MAX = (int) 2e3 + 1;
    long hash1[];
    long hash2[];
    long MOD1 = (long) (1000000097L);
    long MOD2 = (long) (1000000009);
    long power1[] = new long[MAX];
    long power2[] = new long[MAX];
    long inverse1[] = new long[MAX];
    long inverse2[] = new long[MAX];
    long p1 = 31;
    long p2 = 43;

    dualHash(char A[]) {
        int N = A.length;
        hash1 = new long[N + 1];
        hash2 = new long[N + 1];
        computePower1();
        computePower2();
        computeInverse1();
        computeInverse2();

        for (int i = 1; i <= N; i++) {
            hash1[i] = (hash1[i - 1] % MOD1 + ((A[i - 1] - 'a' + 1) * power1[i - 1]) % MOD1 + 5 * MOD1) % MOD1;
            hash2[i] = (hash2[i - 1] % MOD2 + ((A[i - 1] - 'a' + 1) * power2[i - 1]) % MOD2 + 5 * MOD2) % MOD2;
        }
    }

    public void computePower1() {
        power1[0] = 1;
        for (int i = 1; i < MAX; i++)
            power1[i] = (power1[i - 1] % MOD1 * p1) % MOD1;
    }

    public void computePower2() {
        power2[0] = 1;
        for (int i = 1; i < MAX; i++)
            power2[i] = (power2[i - 1] % MOD2 * p2) % MOD2;
    }

    public void computeInverse1() {
        for (int i = 0; i < MAX; i++)
            inverse1[i] = power(power1[i], MOD1 - 2, MOD1) % MOD1;
    }

    public void computeInverse2() {
        for (int i = 0; i < MAX; i++)
            inverse2[i] = power(power2[i], MOD2 - 2, MOD2) % MOD2;
    }

    public static long power(long a, long b, long mod) {
        long res = 1;
        while (b > 0) {
            if (b % 2 == 1)
                res = (res % mod * a % mod) % mod;
            a = (a % mod * a % mod) % mod;
            b = b >> 1;
        }
        return res;
    }

    public long getHash(int i, int j) {
        return getHash_2(i, j - i + 1);
    }

    public long getHash_2(int i, int len) {
        return (((hash1[i + len] - hash1[i] + MOD1) * inverse1[i] % MOD1) << 32)
                + (hash2[i + len] - hash2[i] + MOD2) * inverse2[i] % MOD2;
    }
}

class FastReader {

    byte[] buf = new byte[2048];
    int index, total;
    InputStream in;

    FastReader(InputStream is) {
        in = is;
    }

    int scan() throws IOException {
        if (index >= total) {
            index = 0;
            total = in.read(buf);
            if (total <= 0) {
                return -1;
            }
        }
        return buf[index++];
    }

    String next() throws IOException {
        int c;
        for (c = scan(); c <= 32; c = scan()) ;
        StringBuilder sb = new StringBuilder();
        for (; c > 32; c = scan()) {
            sb.append((char) c);
        }
        return sb.toString();
    }

    int nextInt() throws IOException {
        int c, val = 0;
        for (c = scan(); c <= 32; c = scan()) ;
        boolean neg = c == '-';
        if (c == '-' || c == '+') {
            c = scan();
        }
        for (; c >= '0' && c <= '9'; c = scan()) {
            val = (val << 3) + (val << 1) + (c & 15);
        }
        return neg ? -val : val;
    }

    long nextLong() throws IOException {
        int c;
        long val = 0;
        for (c = scan(); c <= 32; c = scan()) ;
        boolean neg = c == '-';
        if (c == '-' || c == '+') {
            c = scan();
        }
        for (; c >= '0' && c <= '9'; c = scan()) {
            val = (val << 3) + (val << 1) + (c & 15);
        }
        return neg ? -val : val;
    }
}