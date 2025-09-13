import java.util.*;

class Solution {
    // 최단 경로 찾기
    // 맵에서 값이 1인곳으로만 상하좌우 이동 (가중치 없음)
    // 가중치 없는 최단 경로 -> BFS가 적합
    // 시작점 (0, 0)
    // 도착지 (maps.length, maps[0].length)
    public int solution(int[][] maps) {
        // 2차원 배열의 모든 시작으로 탐색을 진행 
        // 이미 탐색한 지점은 bfs 메서드에서 0으로 변경
        int n = maps.length; // 2차원 배열의 row 
        int m = maps[0].length; // 2차원 배열의 column
        
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1});
        maps[0][0] = 0; // 방문
        
        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            int x = cur[0], y = cur[1];
            
            int cnt = cur[2];
            
            if(x == n-1 && y == m-1){
                return cnt; // 최단 거리 반환 (bfs: 가장 처음 return 되는게 최단 거리)
            }
            
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                // 1을 발견하면 인접한 정점이므로 큐에 추가
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maps[nx][ny] == 1) {
                    maps[nx][ny] = 0; // 방문 처리
                    queue.add(new int[]{nx, ny, cnt+1});
                }
            }
        }
        return -1; // 도달 불가
    }
}