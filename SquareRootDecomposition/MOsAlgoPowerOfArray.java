package SquareRootDecomposition;

/* given an array calculate power of each subarray, where power is give as -- if Ks is the occurances of s in this subarray, then power for
   this subarray with Ks is given as  (Ks*Ks*s)
 */

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class MOsAlgoPowerOfArray {
    static int BLOCK;
    static long freq[] = new long[2000000 + 1];
    static long power = 0;

    public static void main(String ag[]) throws Exception {
        Reader sc = new Reader();
        int i, j, k;
        int N = sc.nextInt();
        int Q = sc.nextInt();
        BLOCK = 1000;
        int A[] = new int[N + 1];
        for (i = 1; i <= N; i++)
            A[i] = sc.nextInt();

        Query arr[] = new Query[Q];
        for (i = 1; i <= Q; i++) {
            int L = sc.nextInt();
            int R = sc.nextInt();
            arr[i - 1] = new Query(L, R, i);
        }
        StringBuilder sb = new StringBuilder();
        Arrays.sort(arr, new Tcomp());
        long ans[] = new long[Q + 1];

        int mL = 1;
        int mR = 0;
        for (i = 0; i < Q; i++) {
            int L = arr[i].l;
            int R = arr[i].r;
            int id = arr[i].i;

            while (mL > L) {
                mL--;
                long cnt = freq[A[mL]];
                freq[A[mL]]++;
                power -= (cnt * cnt) * A[mL];
                cnt++;
                power += (cnt * cnt) * A[mL];
            }
            while (mR < R) {
                mR++;
                long cnt = freq[A[mR]];
                freq[A[mR]]++;
                power -= (cnt * cnt) * A[mR];
                cnt++;
                power += (cnt * cnt) * A[mR];
            }
            while (mL < L) {
                long cnt = freq[A[mL]];
                freq[A[mL]]--;
                power -= (cnt * cnt) * A[mL];
                cnt--;
                power += (cnt * cnt) * A[mL];
                mL++;
            }
            while (mR > R) {
                long cnt = freq[A[mR]];
                freq[A[mR]]--;
                power -= (cnt * cnt) * A[mR];
                cnt--;
                power += (cnt * cnt) * A[mR];
                mR--;
            }
            ans[id] = power;
        }
        PrintWriter printr
                = new PrintWriter(System.out);
        for (i = 1; i <= Q; i++)
            sb.append(ans[i] + "\n");
        printr.print(sb);
        printr.flush();

    }

    public static void remove(int pos, int A[]) {

    }

    static class Query {
        int l, r, i;

        Query(int L, int R, int I) {
            l = L;
            r = R;
            i = I;
        }
    }

    static class Tcomp implements Comparator<Query> {
        public int compare(Query a, Query b) {
            if (a.l / BLOCK != b.l / BLOCK)
                return a.l - b.l;
            return a.r - b.r;
        }
    }


    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }
}
