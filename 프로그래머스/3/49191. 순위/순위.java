// 순위를 매길려면 
import java.util.*;

class Solution {
    
    public int solution(int n, int[][] results) {
        // win/lose 그래프 생성
        List<Integer>[] winGraph = new ArrayList[n+1];
        List<Integer>[] loseGraph = new ArrayList[n+1];
        
        // 인접 리스트 초기화
        for (int i = 1; i <= n; i++) {
            winGraph[i] = new ArrayList<>();
            loseGraph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < results.length; i++) {
            int win = results[i][0];
            int lose = results[i][1];
            winGraph[win].add(lose);
            loseGraph[lose].add(win);
        }
        
        // win graph와 lose graph가 동시에 같은 정점을 시작으로 dfs 순회
        // dfs 순회로 반환한 인접 정점의 개수가 n -1이면 카운팅 
        int rankCnt = 0;
        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n+1];
            int winCnt = dfs(i, winGraph, visited);
            
            visited = new boolean[n+1];
            int loseCnt = dfs(i, loseGraph, visited);
            if (winCnt + loseCnt == n - 1) rankCnt++;
        }
        return rankCnt;
    }
    
    // 이미 순회를 마친 정점은 개수를 카운트?
    
    // 2 -> 1 -> 3 -> 4
    // 5 -> 2
    

    private int dfs(int start, List<Integer>[] graph, boolean[] visited) {
        // 재귀적으로 다음 정점을 순회
        visited[start] = true;
        int cnt = 0; // 자기 자신을 제외해야하므로 0
        // 해당 정점의 인접 정점으로 순회
        for (int adjV: graph[start]) {
            // 탐색한 인접 정점 카운트
            if (!visited[adjV]) {
                cnt++;
                cnt += dfs(adjV, graph, visited); // dfs가 호출될때마다 카운트
            }
        }
        return cnt;
    }
   
}
