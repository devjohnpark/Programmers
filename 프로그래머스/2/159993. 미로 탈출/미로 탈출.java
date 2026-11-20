// 레버를 통해서만 탈출 가능
// 시작점 -> 레버 -> 출구 최단거리 탐색
// 시작점에서 레버까지 BFS + 레버에서 출구까지 BFS  
import java.util.*;

class Solution {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int solution(String[] maps) {

        int n = maps.length;
        int m = maps[0].length();

        int sx = 0, sy = 0; // 시작 지점
        int lx = 0, ly = 0; // 레버 지점 

        // 시작점과 레버 위치 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maps[i].charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                }

                if (maps[i].charAt(j) == 'L') {
                    lx = i;
                    ly = j;
                }
            }
        }

        // 시작점 -> 레버
        int toLever = bfs(maps, sx, sy, 'L');

        if (toLever == -1) {
            return -1;
        }

        // 레버 -> 출구
        int toExit = bfs(maps, lx, ly, 'E');

        if (toExit == -1) {
            return -1;
        }

        return toLever + toExit;
    }

    static int bfs(String[] maps, int sx, int sy, char to) {

        int n = maps.length;
        int m = maps[0].length();

        boolean[][] visited = new boolean[n][m];

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int x = current[0];
            int y = current[1];
            int cnt = current[2];

            // 목적지 도달
            if (maps[x].charAt(y) == to) {
                return cnt;
            }

            // 상하좌우 탐색
            for (int d = 0; d < 4; d++) {

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }

                // 벽 혹은 방문했던 지점이면 탐색 패스
                if (maps[nx].charAt(ny) == 'X' || visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;

                queue.offer(new int[]{nx, ny, cnt + 1});
            }
        }

        return -1;
    }
}
