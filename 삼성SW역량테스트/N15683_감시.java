package 삼성SW역량테스트;

import java.io.*;
import java.util.*;

public class N15683_감시 {
    static int N, M, ans;
    static List<int[]> cctv;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        cctv = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (1 <= map[i][j] && map[i][j] <= 5) {
                    cctv.add(new int[]{i, j});
                }
            }
        }

        ans = Integer.MAX_VALUE;

        // 1. cctv 방향 선택
        // 2. 사각지대 계산

        dfs(0, map); // 설정한 cctv 개수, map

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }

    private static void dfs(int setting, int[][] map) {
        if (setting == cctv.size()) {
            int area = getBlindSpot(map);
            ans = Math.min(ans, area);
            return;
        }

        int now_i = cctv.get(setting)[0];
        int now_j = cctv.get(setting)[1];
        int now_number = map[now_i][now_j];

        if (now_number == 1) {
            for (int k = 0; k < 4; k++) {
                int[][] tmp_map = cloneMap(map); // 맵 복사 (깊은 복사 필요)
                watch(now_i, now_j, k, tmp_map);
                dfs(setting + 1, tmp_map);
            }
        } else if (now_number == 2) {
            for (int k = 0; k < 2; k++) {
                int[][] tmp_map = cloneMap(map);
                watch(now_i, now_j, k, tmp_map);
                watch(now_i, now_j, (k + 2) % 4, tmp_map);
                dfs(setting + 1, tmp_map);
            }
        } else if (now_number == 3) {
            for (int k = 0; k < 4; k++) {
                int[][] tmp_map = cloneMap(map);
                watch(now_i, now_j, k, tmp_map);
                watch(now_i, now_j, (k + 1) % 4, tmp_map);
                dfs(setting + 1, tmp_map);
            }

        } else if (now_number == 4) {
            for (int k = 0; k < 4; k++) {
                int[][] tmp_map = cloneMap(map);
                watch(now_i, now_j, k, tmp_map);
                watch(now_i, now_j, (k + 1) % 4, tmp_map);
                watch(now_i, now_j, (k + 2) % 4, tmp_map);
                dfs(setting + 1, tmp_map);
            }
        } else {
            int[][] tmp_map = cloneMap(map);
            for (int k = 0; k < 4; k++) {
                watch(now_i, now_j, k, tmp_map);
            }
            dfs(setting + 1, tmp_map);
        }
    }

    private static void watch(int now_i, int now_j, int dir, int[][] tmp_map) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        int y = now_i + dy[dir];
        int x = now_j + dx[dir];
        while (0 <= y && y < N && 0 <= x && x < M) {
            if (tmp_map[y][x] != 6) {
                if (tmp_map[y][x] == 0) tmp_map[y][x] = -1;
                y += dy[dir];
                x += dx[dir];
            } else break;
        }
    }

    private static int[][] cloneMap(int[][] map) {
        int[][] tmp = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        return tmp;
    }

    private static int getBlindSpot(int[][] map) {
        int area = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) area++;
            }
        }
        return area;
    }
}
