package Do_it_알고리즘_코딩테스트.quiz;

import java.io.*;
import java.util.*;
public class N2750_수정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            int M = Integer.parseInt(br.readLine());
            arr[i] = M;
        }

        Arrays.sort(arr);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int j = 0; j < arr.length; j++) {
            bw.write(String.valueOf(arr[j]));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
