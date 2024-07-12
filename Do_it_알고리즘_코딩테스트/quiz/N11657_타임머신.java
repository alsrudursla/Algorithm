package Do_it_알고리즘_코딩테스트.quiz;

import java.io.*;
import java.util.*;
// 벨만 포드
// 음수 가중치가 존재할 때 최단 거리 구하기
// (1) 에지 리스트로 그래프 표현
public class N11657_타임머신 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 에지 리스트로 그래프 표현
        List<Edge> graph = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start_node = Integer.parseInt(st.nextToken());
            int end_node = Integer.parseInt(st.nextToken());
            int weight_node = Integer.parseInt(st.nextToken());

            graph.add(new Edge(start_node, end_node, weight_node));
        }

        long[] shortest = new long[N+1]; // 최단 거리 배열 : 500*6000*10000 이 int 범위를 넘어감
        Arrays.fill(shortest, Long.MAX_VALUE);
        shortest[1] = 0; // 1번 도시에서 출발

        boolean update = false;
        for (int i = 0; i < N; i++) { // N-1 번 반복 + 음수 사이클 판별
            for (Edge edge : graph) {
                if (shortest[edge.start] != Long.MAX_VALUE) {
                    if (shortest[edge.start] + edge.weight < shortest[edge.end]) {
                        if (i == N-1) {
                            update = true;
                        } else {
                            shortest[edge.end] = shortest[edge.start] + edge.weight;
                        }
                    }
                }
            }
        }

        if (update) { // 최단 거리 구할 수 없음
            bw.write("-1\n");
        } else {
            for (int i = 2; i <= N; i++) {
                if (shortest[i] == Long.MAX_VALUE) { // 해당 노드로의 길이 없음
                    bw.write("-1\n");
                } else { // 정상 출력
                    bw.write(String.valueOf(shortest[i]) + '\n');
                }
            }
        }

        bw.flush();
        bw.close();
    }
}

class Edge {
    int start, end, weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

/* (2) 배열로 그래프 표현
public class N11657_타임머신 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 배열로 그래프 표현
        int[] start = new int[M+1];
        int[] end = new int[M+1];
        int[] weight = new int[M+1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int start_node = Integer.parseInt(st.nextToken());
            int end_node = Integer.parseInt(st.nextToken());
            int weight_node = Integer.parseInt(st.nextToken());

            start[i] = start_node;
            end[i] = end_node;
            weight[i] = weight_node;
        }

        long[] shortest = new long[N+1]; // 최단 거리 배열
        for (int i = 1; i <= N; i++) {
            if (i == 1) {
                shortest[i] = 0; // 1번 도시에서 출발
            } else {
                shortest[i] = Long.MAX_VALUE;
            }
        }

        // N-1 번 만큼 업데이트
        for (int i = 0; i < N-1; i++) {
            for (int j = 1; j <= M; j++) {
                if (shortest[start[j]] != Long.MAX_VALUE) {
                    if (shortest[start[j]] + weight[j] < shortest[end[j]]) {
                        shortest[end[j]] = shortest[start[j]] + weight[j];
                    }
                }
            }
        }

        // 음수 사이클 존재 확인
        boolean update = false;
        for (int i = 1; i <= M; i++) {
            if (shortest[start[i]] != Long.MAX_VALUE) {
                if (shortest[start[i]] + weight[i] < shortest[end[i]]) {
                    update = true;
                }
            }
        }

        if (update) { // 최단 거리 구할 수 없음
            bw.write(String.valueOf(-1));
            bw.newLine();
        } else {
            for (int i = 2; i <= N; i++) {
                if (shortest[i] == Long.MAX_VALUE) { // 해당 노드로의 길이 없음
                    bw.write(String.valueOf(-1));
                    bw.newLine();
                } else { // 정상 출력
                    bw.write(String.valueOf(shortest[i]));
                    bw.newLine();
                }
            }
        }

        bw.flush();
        bw.close();
    }
}
*/
