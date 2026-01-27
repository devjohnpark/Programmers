import java.util.*;

class Solution { 
    
    public int solution(int n, int[][] wires) {
        List<Integer>[] graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < wires.length; i++) {
            int u = wires[i][0];
            int v = wires[i][1];
            graph[u].add(v);
            graph[v].add(u);
        }
        
        int minDiff = n;

        for (int i = 0; i < wires.length; i++) {
            int u = wires[i][0];
            int v = wires[i][1];

            int cnt = dfs(graph, u, new boolean[n+1], u, v); // 시작점은 u (또는 v)
            int diff = Math.abs(cnt - (n - cnt)); // | (한쪽 개수) - (다른쪽 개수) |

            minDiff = Math.min(minDiff, diff);
        }
        return minDiff;
    }

    
    private int dfs(List<Integer>[] graph, int start, boolean[] visited, int banU, int banV) {
        visited[start] = true;
        int cnt = 1; // 자기 자신 포함

        for (int next : graph[start]) {
            // 끊은 간선이면 스킵
            if ((start == banU && next == banV) || (start == banV && next == banU)) continue;

            if (!visited[next]) {
                cnt += dfs(graph, next, visited, banU, banV);
            }
        }
        return cnt;
    }
}