package Do_it_알고리즘_코딩테스트;

import java.io.*;
import java.util.*;
public class N11659_구간합구하기4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        int[] sumArr = new int[N + 1];
        sumArr[0] = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            sumArr[i + 1] = sumArr[i] + num;
        }

        for (int j = 0; j < M; j++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            bw.write(String.valueOf(sumArr[end] - sumArr[start - 1]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
