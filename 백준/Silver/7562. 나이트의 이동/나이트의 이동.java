import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 모든 방향으로 최단거리 로직
// 무가중치이므로 BFS 사용

public class Main {
//    static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
//    static int[] dy = { 1, 1, 1, 0, 0, -1, -1, -1};

    static int[] dx = { -2, -2, -1, -1,  1,  1,  2,  2 };
    static int[] dy = { -1,  1, -2,  2, -2,  2, -1,  1 };


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int targetX = Integer.parseInt(st.nextToken());
            int targetY = Integer.parseInt(st.nextToken());

            boolean[][] visited = new boolean[n][n];

            Queue<int[]> queue = new LinkedList<>();
            visited[startX][startY] = true;
            queue.add(new int [] {startX, startY, 0});
            int minDist = 0;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curX = cur[0];
                int curY = cur[1];
                int curDist = cur[2];

                if (curX == targetX && curY == targetY) {
                    minDist = curDist;
                    break;
                }

                for (int i = 0; i < 8; i++) {
                    int nx = curX + dx[i];
                    int ny = curY + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new int [] {nx, ny, curDist + 1});
                    }

                }
            }
            System.out.println(minDist);

        }


    }
}
