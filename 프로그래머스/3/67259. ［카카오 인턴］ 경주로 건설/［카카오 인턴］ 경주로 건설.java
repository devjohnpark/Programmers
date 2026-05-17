// 직선 도로: 이동할때마다 추가
// 코너: 다른 방향을 이동할때마다 추가
// 직선/코너에 의해 간선 크기가 다르므로 다익스트라가 더 적합하지만, BFS + 간선비용으로 풀수있다.
// 나의 경우에는 BFS 풀이가 더 익숙하므로 그렇게푼다.

// 큐에 x,y,dist,direction 저장 
// direction: 저장을 안하면 이전에 이동했던 방향이 어딘지 모름

// 같은 칸이라도 직선과 코너의 비용이 달라서 경로에 따라 비용의 크기가 달라진다. 
// 1. 방향 4개 분리: "어느 방향으로 왔는지"를 구분하기 위함
// 2. 비용 저장: "같은 칸 + 같은 방향"라도 더 싼 경로가 나중에 올 수 있어서 (경로는 다른데 이전방향만 같을수있음)
// 3. 따라서 boolean[][][]이 아닌 int[][][] visited 비용으로 두고 더 나은 비용일 때마다 갱신한다.
import java.util.*;

class Solution {
    int[] dx = {1,-1,0,0};
    int[] dy = {0,0,1,-1};
    
    public int solution(int[][] board) {
        Queue<int[]> q = new LinkedList<>();
        int n = board.length;
        int[][][] visited = new int[n][n][4];   
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }
        q.add(new int[] {0, 0, 0, -1});

        int answer = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            int direction = cur[3];
            
            if (x == n-1 && y == n-1) {
                answer = Math.min(answer, dist);
            }
            
            for (int nd=0; nd<4; nd++) {
                int nx = x + dx[nd];
                int ny = y + dy[nd];
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (board[nx][ny] == 1) continue;
                

                int nextDist = dist + 100;

                if (direction != -1 && direction != nd) {
                    nextDist += 500;
                }
                
                if (visited[nx][ny][nd] > nextDist) {
                    visited[nx][ny][nd] = nextDist;
                    q.add(new int[] {nx, ny, nextDist, nd});
                }
            }
        }
        return answer;
    }
}