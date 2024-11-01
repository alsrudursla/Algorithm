package 삼성SW역량테스트;

import java.io.*;
import java.util.*;

public class N19237_어른상어 {
    static int N, M, K;
    static int[] dx = {0, 0, 0, -1, 1}; // 상하좌우 순서
    static int[] dy = {0, -1, 1, 0, 0};
    static int[][][] map; // [x][y][0]: 상어 번호, [x][y][1]: 냄새 남은 시간
    static Shark[] sharkList;
    static int time = 0;
    static int[][][] sharksDir; // 상어 방향별 우선 순위
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 격자 크기
        M = Integer.parseInt(st.nextToken()); // 상어 수
        K = Integer.parseInt(st.nextToken()); // 냄새 유지 기간

        map = new int[N][N][2];
        sharkList = new Shark[M+1];
        sharksDir = new int[M+1][5][5]; // 각 상어마다 4방향에 대한 우선순위 저장

        // 주어진 맵
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input != 0) {
                    map[i][j][0] = input;
                    map[i][j][1] = K;
                    sharkList[input] = new Shark(input, 0, i, j);
                }
            }
        }

        // 초기 방향
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharkList[i].direction = Integer.parseInt(st.nextToken());
        }

        // 상어별 방향 우선 순위
        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= 4; j++) {
                // 1 위 2 아래 3 왼쪽 4 오른쪽
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= 4; k++) {
                    sharksDir[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        bfs();

        bw.write(String.valueOf(time));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void bfs() {
        while (time <= 1000) {
            time++;
            for (int i = 1; i <= M; i++) { // 각자 움직일 방향 정하기
                if (sharkList[i] == null) continue;
                int shark_number = sharkList[i].number;
                int shark_dir = sharkList[i].direction;
                int shark_i = sharkList[i].i;
                int shark_j = sharkList[i].j;

                boolean movChk = false;

                // 1. 인접한 칸 중 아무 냄새가 없는 칸 선택 (우선 순위 반영)
                for (int k = 1; k <= 4; k++) {
                    int next_dir = sharksDir[shark_number][shark_dir][k];
                    int next_i = shark_i + dy[next_dir];
                    int next_j = shark_j + dx[next_dir];

                    if (next_i >= 0 && next_i < N && next_j >= 0 && next_j < N) {
                        if (map[next_i][next_j][1] == 0) {
                            sharkList[shark_number].direction = next_dir;
                            sharkList[shark_number].i = next_i;
                            sharkList[shark_number].j = next_j;
                            movChk = true;
                            break;
                        }
                    }
                }

                // 2. 자신의 냄새가 있는 칸 (우선 순위 반영)
                if (!movChk) {
                    for (int k = 1; k <= 4; k++) {
                        int next_dir = sharksDir[shark_number][shark_dir][k];
                        int next_i = shark_i + dy[next_dir];
                        int next_j = shark_j + dx[next_dir];

                        if (next_i >= 0 && next_i < N && next_j >= 0 && next_j < N) {
                            if (map[next_i][next_j][0] == shark_number) {
                                sharkList[shark_number].direction = next_dir;
                                sharkList[shark_number].i = next_i;
                                sharkList[shark_number].j = next_j;
                                break;
                            }
                        }
                    }
                }
            }

            // 시간 경과
            for (int l = 0; l < N; l++) {
                for (int m = 0; m < N; m++) {
                    if (map[l][m][1] != 0) {
                        map[l][m][1] -= 1;

                        if (map[l][m][1] == 0) {
                            map[l][m][0] = 0;
                        }
                    }
                }
            }

            // 다같이 움직이기
            for (int i = 1; i <= M; i++) {
                Shark shark = sharkList[i];

                if (shark == null) continue;

                // 이동한 칸에 다른 상어랑 겹치면 번호가 가장 작은 상어만 남음
                if (map[shark.i][shark.j][0] != 0) {
                    int ori_shark = map[shark.i][shark.j][0];
                    int new_shark = shark.number;
                    if (new_shark < ori_shark) { // 새로 들어온 상어가 더 강함
                        map[shark.i][shark.j][0] = new_shark;
                        map[shark.i][shark.j][1] = K;
                        sharkList[ori_shark] = null;
                    } else if (new_shark > ori_shark){
                        sharkList[new_shark] = null;
                    } else { // 내 냄새의 칸으로 이동한 경우
                        map[shark.i][shark.j][1] = K;
                    }
                } else { // 빈 공간으로 이동
                    map[shark.i][shark.j][0] = shark.number;
                    map[shark.i][shark.j][1] = K;
                }
            }

            // 한 마리 남을 때까지 진행
            int chk = 0;
            for (int s = 0; s < sharkList.length; s++) {
                if (sharkList[s] == null) continue;
                chk++;
            }
            if (chk == 1) {
                return;
            }
        }
        time = -1;
    }

    static class Shark {
        int number;
        int direction;
        int i, j;
        Shark(int number, int direction, int i, int j) {
            this.number = number;
            this.direction = direction;
            this.i = i;
            this.j = j;
        }
    }
}
