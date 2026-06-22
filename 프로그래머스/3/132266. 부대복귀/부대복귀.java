import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 1. 인접 리스트 초기화
        List<Integer>[] adj = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        // 2. 양방향 간선 정보 채우기
        for (int[] r : roads) {
            adj[r[0]].add(r[1]);
            adj[r[1]].add(r[0]);
        }
        
        // 3. 각 source마다 따로 BFS를 돌려서 destination까지의 거리 계산
        int[] result = new int[sources.length];
        
        for (int i = 0; i < sources.length; i++) {
            int start = sources[i];
            result[i] = bfs(adj, n, start, destination);
        }
        
        return result;
    }
    
    private int bfs(List<Integer>[] adj, int n, int start, int destination) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[start] = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            // 목적지에 도달하면 바로 반환 (조기 종료)
            if (cur == destination) {
                return dist[cur];
            }
            
            for (int next : adj[cur]) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    queue.offer(next);
                }
            }
        }
        
        return -1; // 도달 불가능
    }
}