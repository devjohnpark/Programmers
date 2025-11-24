// 문제 정의: 겹치지 않는 테두리 좌표애서 시작점부터 도착지점까지 최단거리 탐색을 해서 측정해라.

// 문제 핵심 알고리즘
// 0. 모든 좌표 (도형 좌표, 시작점, 도착점)을 2배수를 한다. (ㄷ자의 좌표는 최단거리 탐색으로 테두리 탐색이 안됨)
// 1. 겹치지 않는 도형의 테두리만 남긴다.
    // 1. 모든 도형 면적을 1로 채우고 나머지 좌표는 0으로 채운다.
    // 2. 모든 도형의 테두리를 제외한 내부를 0으로 채운다. 
// 2. 시작점에서 도착지점까지 bfs로 상하좌우 방향으로 좌표를 이동하면서 최단거리를 구한다. (무가중치 그래프이므로 bfs가 적합)
// 3. 최단거리를 2로 나눠서 원래의 크기로 변경해서 반환

import java.util.*;

class Solution {
    private int dx[] = { 1, -1, 0, 0};
    private int dy[] = { 0, 0, 1, -1};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
         // 인접행렬 생성
         // x, y 좌표가 1 ~ 50이고, 2배수면 1 ~ 100까지 인데스를 표현해야하므로 101개로 할당
         int[][] adjMatrix = new int[101][101]; // 초기값 0
         boolean[][] visited = new boolean[101][101];
         
         // 도형을 좌표에 옮기기
         for (int i = 0; i < rectangle.length; i++) {
             int x1 = rectangle[i][0] * 2;
             int x2 = rectangle[i][2] * 2; 
             int y1 = rectangle[i][1] * 2;
             int y2 = rectangle[i][3] * 2;
             
             // 도형 면적을 모두 1로 설정
             for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    adjMatrix[x][y] = 1;    
                }
             }
         }
        
        // 도형 테두리 제외한 내부 삭제
         for (int i = 0; i < rectangle.length; i++) {
             int x1 = rectangle[i][0] * 2;
             int x2 = rectangle[i][2] * 2; 
             int y1 = rectangle[i][1] * 2;
             int y2 = rectangle[i][3] * 2;
             
             // 도형 테두리를 제외한 내부를 0으로 설정
             for (int x = x1 + 1; x <= x2 - 1; x++) {
                for (int y = y1 + 1; y <= y2 - 1; y++) {
                    adjMatrix[x][y] = 0;    
                }
             }
             
         }
         
         Queue<int[]> q = new LinkedList<>();
         int startX = characterX * 2;
         int startY = characterY * 2;
         q.add(new int[] {startX, startY, 0});
         visited[startX][startY] = false; // 방문 
         int targetX = itemX * 2;
         int targetY = itemY * 2;
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            int x = cur[0], y = cur[1];
            int cnt = cur[2];

            if(x == targetX && y == targetY){
                return cnt / 2; // 최단 거리 반환 (bfs에서 가장 처음 도달하는 것이 최단 거리, 거리를 2배수 해서 나누기 2)
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 1을 발견하면 인접한 정점이므로 큐에 추가
                if (nx >= 0 && nx < adjMatrix.length && ny >= 0 && ny < adjMatrix[0].length && adjMatrix[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true; // 방문 처리
                    q.add(new int[]{nx, ny, cnt + 1}); // 시작점으로부터 현재 정점까지의 거리 저장
                }
            }
        }
        return -1; // 도달 불가
         
         
    }
}