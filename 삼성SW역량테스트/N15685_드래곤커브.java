package 삼성SW역량테스트;
import java.io.*;
import java.util.*;
public class N15685_드래곤커브 {
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        visited = new boolean[101][101];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()); // 시작 방향
            int g = Integer.parseInt(st.nextToken()); // 세대
            drawMap(x, y, d, g);
        }

        // 1. 드래곤 커브 표시
        // 2. 정사각형 개수 세기

        int ans = countSquare();
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
    }

    private static int countSquare() {
        int tmp = 0;
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                if (visited[i][j] && visited[i][j+1] && visited[i+1][j] && visited[i+1][j+1]) tmp++;
            }
        }
        return tmp;
    }

    private static void drawMap(int j, int i, int start_dir, int iter) {
        // 1. 초기 자료 표시 (0세대)
        // 2. 주어진 세대까지 계속 표시 - 전 세대 방향을 역순으로 반시계 방향으로 돈다

        List<int[]> direction = new ArrayList<>(); // 방향 저장
        direction.add(new int[]{start_dir}); // 0세대 방향

        visited[i][j] = true;
        if (start_dir == 0) { // x좌표가 증가하는 방향 (→)
            j++;
        } else if (start_dir == 1) { // y좌표가 감소하는 방향 (↑)
            i--;
        } else if (start_dir == 2) { // x좌표가 감소하는 방향 (←)
            j--;
        } else { // y좌표가 증가하는 방향 (↓)
            i++;
        }
        visited[i][j] = true;
        int endX = j;
        int endY = i;
        //System.out.println(endY + " " + endX);

        for (int t = 1; t <= iter; t++) {
            // 끝점을 기준으로 시작
            // 전 세대 방향을 역순으로
            int[] before_direction = direction.get(direction.size()-1);
            int[] tmp = new int[before_direction.length * 2];
            int tmp_idx = 0;
            for (int dir : before_direction) { // 원래 방향 저장
                tmp[tmp_idx++] = dir;
            }
            for (int d = before_direction.length-1; d >= 0; d--) {
                int now_dir = before_direction[d];
                int rotateX = endX + dx[(now_dir+1)%4];
                int rotateY = endY + dy[(now_dir+1)%4];
                visited[rotateY][rotateX] = true;

                endX = rotateX;
                endY = rotateY;
                tmp[tmp_idx++] = (now_dir+1)%4; // 추가로 그리는 방향 저장
                //System.out.println(endY + " " + endX);
            }
            direction.add(tmp);
        }
    }
}