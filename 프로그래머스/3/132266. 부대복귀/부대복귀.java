import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int m = roads.length;
        
        // 1. 각 노드의 차수 계산 (양방향이므로 두 배)
        int[] degree = new int[n + 1];
        for (int[] r : roads) {
            degree[r[0]]++;
            degree[r[1]]++;
        }
        
        // 2. 시작 인덱스(head) 배열 생성 (누적합)
        int[] head = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            head[i + 1] = head[i] + degree[i];
        }
        
        // 3. 인접 리스트 본체 배열 채우기
        int[] adj = new int[2 * m];
        int[] pos = Arrays.copyOf(head, head.length); // 현재 삽입 위치 추적용
        for (int[] r : roads) {
            int a = r[0], b = r[1];
            adj[pos[a]++] = b;
            adj[pos[b]++] = a;
        }
        
        // 4. destination에서 BFS
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[destination] = 0;
        
        int[] queue = new int[n + 1];
        int qHead = 0, qTail = 0;
        queue[qTail++] = destination;
        
        while (qHead < qTail) {
            int cur = queue[qHead++];
            // cur의 인접 노드들은 adj[head[cur] ... head[cur+1]-1] 구간
            for (int idx = head[cur]; idx < head[cur + 1]; idx++) {
                int next = adj[idx];
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    queue[qTail++] = next;
                }
            }
        }
        
        // 5. sources 순서대로 결과 채우기
        int[] result = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            result[i] = dist[sources[i]];
        }
        
        return result;
    }
}