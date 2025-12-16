import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// bfs: 동일 깊이 순으로 탐색하므로 최초 도착지점이 최단거리이다. 그러므로 시간복잡도는 최단 경로 길이인 O(N+M)이다.
// dfs는 최단 거리 탐색을 못함: 완전 탐색이 불가
// backtracking은 완전 탐색이 가능해서 최단 거리 탐색을 할수있음: 탐색한 정점의 개수 누적 안됨. 허나 깊이가 최대 N×M까지 갈 수 있으므로 4방향으로 탐색한다면 시간복잡도는 4^(NxM)이다.
// 정점 개수 카운팅 할때, 왜 ++cnt이 아닌 cnt + 1로 넘겨야하는가? 그 이유는 cnt가 변경되어서 백트래킹시, 업데이트 된 cnt값으로 카운트하게된다.
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
                grid[i][j] = line.charAt(j) - '0'; // 문자 단위로 끊기
            }
        }
        
        bfs(0, 0, 1);

        //        visited[0][0] = true;
//        backtracking(0, 0, 1);

        System.out.println(minDist);
    }

    private static void bfs(int x, int y, int dist) {
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;

        q.add(new int[]{x, y, dist});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];

            if (curX == N - 1 && curY == M - 1) {
                minDist = curDist;
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
    
    private static void backtracking(int x, int y, int dist) {

        // 도착지점 순회시, 현재까지의 거리가 최소거리보다 작을경우에 저장
        if (x == N - 1 && y == M - 1) {
            minDist = Math.min(minDist, dist);
            return;
        }

        if (dist >= minDist) {
            return;
        }

        // 4방향 탐색
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && grid[nx][ny] == 1) {
                visited[nx][ny] = true;
                backtracking(nx, ny, dist + 1);
                visited[nx][ny] = false;
            }
        }
    }

    // 모든 경로 탐색 불가
    private static void dfs(int x, int y, int dist) {
        // 도착지점 순회시, 현재까지의 거리가 최소거리보다 작을경우에 저장
        if (x == N - 1 && y == M - 1) {
            minDist = Math.min(minDist, dist);
            return;
        }

        visited[x][y] = true;

        // 4방향 탐색
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && grid[nx][ny] == 1) {
                dfs(nx, ny, dist + 1);
            }
        }
    }
}
