import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int grid[][];
    static boolean visited[][];
    static int minDist;
    static int N;
    static int M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()); // 공백 단위로 끊어서 토큰을 만들기위한 객체

        N = Integer.parseInt(st.nextToken()); // 공백 단위로 끊기
        M = Integer.parseInt(st.nextToken()); // 공백 단위로 끊기

        minDist = N * M;

        grid = new int[N][M];
        visited = new boolean[N][M];

        // grid 생성
        for (int i = 0; i < N; i++) {
            String line = br.readLine(); // 문자열
            for (int j = 0; j < M; j++) {
                grid[i][j] = grid[i][j] = line.charAt(j) - '0'; // 문자 단위로 끊기
            }
        }

        Queue<int[]> q = new LinkedList<>();
        visited[0][0] = true;

        q.add(new int[]{0, 0, 1});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];

            if (curX == N - 1 && curY == M - 1) {
                System.out.println(curDist);
            }

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && grid[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, curDist + 1});
                }
            }

        }
    }
}