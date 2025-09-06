package 삼성SW역량테스트;
import java.util.*;
import java.io.*;
public class N23288_주사위굴리기2 {
    static int[] dy = {0, 1, 0, -1}; // 동 남 서 북
    static int[] dx = {1, 0, -1, 0};
    static int height, width;
    static int[][] map;
    static int[] dice;
    static int diceY, diceX, diceDir;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        int moveCnt = Integer.parseInt(st.nextToken());

        map = new int[height][width];
        for (int h = 0; h < height; h++) {
            st = new StringTokenizer(br.readLine());
            for (int w = 0; w < width; w++) {
                map[h][w] = Integer.parseInt(st.nextToken());
            }
        }

        // 0. 주사위 만들기
        dice = new int[6]; // 천장, 북, 동, 남, 서, 바닥
        dice[0] = 1;
        dice[1] = 2;
        dice[2] = 3;
        dice[3] = 5;
        dice[4] = 4;
        dice[5] = 6;

        diceY = 0;
        diceX = 0;
        diceDir = 0; // 동 남 서 북

        int sum = 0;
        for (int m = 0; m < moveCnt; m++) {
            // 1. 이동 방향으로 굴러가기
            move();
            // 2. 점수 계산
            sum += calculateSum();
            // 3. 방향 계산
            changeDirection();
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
    }

    private static void changeDirection() {
        int A = dice[5];
        int B = map[diceY][diceX];

        if (A > B) diceDir = (diceDir + 1) % 4;
        else if (A < B) diceDir = (diceDir + 3) % 4;

        //System.out.println("방향 변화 : " + diceDir);
    }

    private static int calculateSum() {
        Queue<int[]> myqueue = new LinkedList<>();
        myqueue.add(new int[]{diceY, diceX});

        boolean[][] visited = new boolean[height][width];
        visited[diceY][diceX] = true;

        int cnt = 1;
        int value = map[diceY][diceX];
        while (!myqueue.isEmpty()) {
            int[] now = myqueue.poll();
            int nowY = now[0];
            int nowX = now[1];

            for (int k = 0; k < 4; k++) {
                int nextY = nowY + dy[k];
                int nextX = nowX + dx[k];

                if (0 <= nextY && nextY < height && 0 <= nextX && nextX < width) {
                    if (!visited[nextY][nextX] && map[nextY][nextX] == value) {
                        cnt++;
                        visited[nextY][nextX] = true;
                        myqueue.add(new int[]{nextY, nextX});
                    }
                }
            }
        }
        //System.out.println("더해지는 점수 : " + cnt*value);
        return cnt * value;
    }

    private static void move() {
        // 위치하는 칸 변화
        int nextY = diceY + dy[diceDir];
        int nextX = diceX + dx[diceDir];

        if (0 <= nextY && nextY < height && 0 <= nextX && nextX < width) {
            diceY = nextY;
            diceX = nextX;
        } else {
            diceDir = (diceDir + 2) % 4;
            diceY += dy[diceDir];
            diceX += dx[diceDir];
        }
        //System.out.println("이동 : " + diceY + " " + diceX);

        // 주사위 변화
        int tmp = dice[0];
        if (diceDir == 0) { // 동
            dice[0] = dice[4];
            dice[4] = dice[5];
            dice[5] = dice[2];
            dice[2] = tmp;
        } else if (diceDir == 1) { // 남
            dice[0] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[3];
            dice[3] = tmp;
        } else if (diceDir == 2) { // 서
            dice[0] = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[4];
            dice[4] = tmp;
        } else { // 북
            dice[0] = dice[3];
            dice[3] = dice[5];
            dice[5] = dice[1];
            dice[1] = tmp;
        }
        //System.out.println("주사위 머리 번호 : " + dice[0]);
    }
}
