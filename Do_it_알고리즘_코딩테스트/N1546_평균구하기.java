package Do_it_알고리즘_코딩테스트;

import java.io.*;
import java.util.*;
public class N1546_평균구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());

        double sum = 0;
        double[] grade = new double[N];
        double max_grade = 0;

        for (int i = 0 ; i < N ; i++) {
            grade[i] = Integer.parseInt(st.nextToken());
        }

        for (int k = 0; k < grade.length ; k++) {
            if (grade[k] > max_grade) {
                max_grade = grade[k];
            }
        }

        for (int j = 0 ; j < N ; j++) {
            grade[j] = grade[j] / max_grade * 100;
            sum += grade[j];
        }

        double avg = sum / N;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(avg));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}
