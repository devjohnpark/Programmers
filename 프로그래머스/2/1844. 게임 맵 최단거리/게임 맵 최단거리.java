import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length; // 2차원 배열의 row 
        int m = maps[0].length; // 2차원 배열의 column
        
        int[] dx = {1, -1, 0, 0}; // x가 움직을 좌표
        int[] dy = {0, 0, 1, -1}; // y가 움직을 좌표
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1}); // 시작 x좌표 y좌표, 시작 칸을 포함해서 거리 1로 시작
        maps[0][0] = 0; // 방문 
        
        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            int x = cur[0], y = cur[1];
            int cnt = cur[2];
            
            if(x == n - 1 && y == m - 1){
                return cnt; // 최단 거리 반환 (bfs에서 가장 처음 도달하는 것이 최단 거리)
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 1을 발견하면 인접한 정점이므로 큐에 추가
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maps[nx][ny] == 1) {
                    maps[nx][ny] = 0; // 방문 처리
                    queue.add(new int[]{nx, ny, cnt + 1}); // 시작점으로부터 현재 정점까지의 거리 저장
                }
            }
        }
        return -1; // 도달 불가
    }
}