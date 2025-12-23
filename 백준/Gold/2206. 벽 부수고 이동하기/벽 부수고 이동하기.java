import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[][] graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }
        
        // 3차원 방문 배열: [y][x][벽을 부순 여부]
        // visited[y][x][0]: 벽을 부수지 않고 도달
        // visited[y][x][1]: 벽을 1개 부수고 도달
        boolean[][][] visited = new boolean[n][m][2];
        
        // BFS: int[] = {y, x, broken, dist}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0, 1});
        visited[0][0][0] = true;
        
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            int broken = cur[2];
            int dist = cur[3];
            
            // 목적지 도착
            if (y == n - 1 && x == m - 1) {
                System.out.println(dist);
                return;
            }
            
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                
                // 범위 체크
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                    continue;
                }
                
                // 이동 가능한 칸인 경우
                if (graph[ny][nx] == 0) {
                    if (!visited[ny][nx][broken]) {
                        visited[ny][nx][broken] = true;
                        queue.offer(new int[]{ny, nx, broken, dist + 1});
                    }
                }
                // 벽인 경우 - 아직 벽을 부수지 않았다면 부수고 이동
                else if (graph[ny][nx] == 1 && broken == 0) {
                    if (!visited[ny][nx][1]) {
                        visited[ny][nx][1] = true;
                        queue.offer(new int[]{ny, nx, 1, dist + 1});
                    }
                }
            }
        }
        
        // 목적지에 도달할 수 없는 경우
        System.out.println(-1);
    }
}