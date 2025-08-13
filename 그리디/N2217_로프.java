package 그리디;

import java.io.*;
import java.util.*;
public class N2217_로프 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int rope_cnt = Integer.parseInt(br.readLine()); // 1~10^5

        /*
        최대 -> 규칙 있음
        - rope 1개일 때 : i 값
        - rope 2개일 때 : 2개 골라서(제일 큰 거) 중 작은 값 *2
        - rope 3개일 때 : 3개 골라서(제일 큰 거) 중 작은 값 *3
        .... n개
         */

        List<Integer> rope_weight = new ArrayList<>();
        for (int i = 0; i < rope_cnt; i++) rope_weight.add(Integer.parseInt(br.readLine()));
        rope_weight.sort((o1, o2) -> o2 - o1);

        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= rope_cnt; i++) {
            ans = Math.max(ans, rope_weight.get(i-1)*i);
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }
}
