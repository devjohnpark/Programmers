import java.util.*;

// 문제: 인접 행렬이 주어짐 -> 인접한 노드는 1로 그렇지 않은 0으로 그래프 표현
// 문제 정의: (1,1)에서 (n,m)까지의 최단거리
// 사용할 알고리즘: 가중치가 없으므로 BFS 사용
// 핵심 로직
// 상하좌우로 탐색
// 방문한 곳은 0으로 변경
// 탐색 지점의 값이 0/인덱스 x가 0보다 작거나 maps.length와 같거나 큼/인덱스 y가 0보다 작거나 maps[0].length와 같거나 크면 탐색 종료 -> -1 반환
// 종료 조건 통과하면 거리 계산 변수에 +1 추가
// 인덱스 x와 인덱스 y가 maps.length -1과 maps[1].length에 도달하면 최단 거리 변수에 기존 값보다 작으면 갱신

// BFS 로직
// 탐색한 노드를 큐에 적재
// 큐를 빼내서 인접 큐를 모두 적재

class Solution {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        int[] dx = { 1, -1, 0, 0}; // x의 움직임 좌표
        int[] dy = { 0, 0, 1, -1}; // y의 움직임 좌표
        
        // x, y, 거리의 합 저장
        Queue<int[]> q = new LinkedList<>();
        
        q.add(new int[] { 0, 0, 1}); // 초기값 1로 설정해야 n,m이 모두 1일때 1 반환
        
        maps[0][0] = 0; // 방문 마킹
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int d = cur[2];
            
            // 도착지 도착
            if (x == maps.length-1 && y == maps[0].length-1) return d;
            
            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maps[nx][ny] == 1) {
                    maps[nx][ny] = 0; // 방문 처리
                    q.add(new int[]{nx, ny, d + 1}); // 탐색한 거리 누적 저장
                }
            }
            
        }    
        return -1;
    }
}