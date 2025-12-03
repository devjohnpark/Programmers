// 이 문제의 경우에 그림을 그리면서 알고리즘을 찾을수있다.
// 핵심 알고리즘은 특정 정점에 대해서 이긴 정점의 개수와 진 정점의 개수를 합해서 자기 자신을 제외한 n -1이 되면 순위가 매겨지는 것이다.
// 따라서 win 그래프와 lose 그래프를 방향 그래프로 표현하고나서, 특정 정점의 이기고 진 정점의 개수를 더한 값이 n - 1이면 순위가 매겨진다. // 이 문제는 탐색 시작점이 없고 각 정점을 시작점으로 인접 정점을 모두 DFS로 탐색해야한다. 그러므로 각 정점의 탐색 시작전마다 방문 배열을 초기화해야지 인접 정점의 카운팅이 가능하다. 

// 1. 그림 그려서 로직 파악
// 2. 핵심 알고리즘 로직 설계
// 3. 구현

/*
win directed graph
1 2
2 5
3 2
4 3 2
5

-> 정점 2의 win cnt = 1
-> 정점 5의 win cnt = 0 
*/

/*
lose directed graph
1 
2 4 3 1
3 4
4 
5 2

-> 정점 2의 lose cnt = 3
-> 정점 5의 lose cnt = 4 (정점 5 인접 정점 dfs 탐색전 방문 배열 초기화 필요, 5->2->4->3->1)
*/

/*
최종
정점 2의 win cnt + lose cnt = 4이어서 n-1이므로 순위가 매겨짐
정점 5의 win cnt + lose cnt = 4이어서 n-1이므로 순위가 매겨짐
나머지는 win cnt + lose cnt가 n-1 미달이므로 순위를 매길수없음
*/

// 1. win/lose 인접 리스트 생성 및 초기화
// 2. 주어진 2차원 배열로 win/lose 인접 리스트 값 저장
// 3. 각 정점을 시작점으로 win/lose DFS 탐색 (탐색 시작전 방문 배열 초기화)
// 4. DFS 탐색할때 마다 카운팅하고, 재귀 메서드로 구현할 것이므로 메서드 반환값을 카운터에 더한다.

import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        // win/lose 그래프를 인접 리스트로 생성
        List<Integer>[] winGraph = new ArrayList[n+1];
        List<Integer>[] loseGraph = new ArrayList[n+1];
        
        // 인접 리스트 초기화
        for (int i = 1; i <= n; i++) {
            winGraph[i] = new ArrayList<>();
            loseGraph[i] = new ArrayList<>();
        }
        
        // 주어진 배열로부터 win/lose 그래프 값 저장
        for (int[] result: results) {
            int a = result[0];
            int b = result[1];
            winGraph[a].add(b);
            loseGraph[b].add(a);
        }
        
        // 순위가 매겨진 정점 카운팅
        int rankFixCnt = 0;
        
        // 각 정점을 시작점으로부터 탐색
        // 탐색 시작전 방문 배열 초기화
        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n+1];
            int winCnt = dfs(winGraph, i, visited);
            
            visited = new boolean[n+1];
            int loseCnt = dfs(loseGraph, i, visited);
            
            // 자기 자신을 제외한 정점의 총 정점의 개수 - 1과 동일하면 순위 매겨짐
            if (winCnt + loseCnt == n - 1) rankFixCnt++;
        }
        
        return rankFixCnt;
          
    }
    
    private int dfs(List<Integer>[] graph, int start, boolean[] visited) {
        int count = 0;
        visited[start] = true; 
        // 인접 정점 탐색
        // ex) 5->2->4->3->1
        for (int next : graph[start]) {
            if (!visited[next]) {
                count++; 
                count += dfs(graph, next, visited);
            }
        }
        return count;
    }
}
