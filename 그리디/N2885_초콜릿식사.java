package 그리디;

import java.io.*;
public class N2885_초콜릿식사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int chocoCnt = Integer.parseInt(br.readLine());

        for (int i = 0; i <= 20; i++) {
            int chocoSize = (int) Math.pow(2, i);
            if (chocoSize > chocoCnt) {
                // 이게 가장 작은 전체 초콜릿 크기
                bw.write(chocoSize + " ");

                int restEat = chocoCnt;
                int breakCnt = 0;
                while (restEat > 0) {
                    if (chocoSize == 1) break;
                    breakCnt++;
                    chocoSize /= 2;
                    if (restEat < chocoSize) continue; // 조각이 더 크면 못 먹음
                    restEat -= chocoSize;
                }
                bw.write(String.valueOf(breakCnt));
                break;
            } else if (chocoSize == chocoCnt) {
                bw.write(chocoSize + " " + 0);
                break;
            }
        }
        bw.flush();
        bw.close();
    }
}
