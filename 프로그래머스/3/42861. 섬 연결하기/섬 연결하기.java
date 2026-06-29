import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] cost : costs) {
            int a = cost[0];
            int b = cost[1];
            int c = cost[2];

            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        boolean[] visited = new boolean[n];

        // 가장 비용이 작은순으로 저장
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        // int[]{섬 번호, 비용}
        pq.offer(new int[]{0, 0});

        int answer = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int land = cur[0];
            int cost = cur[1];

            if (visited[land]) {
                continue;
            }

            visited[land] = true;
            answer += cost; // 인접 리스트에서 가장 작은 순으로 탐색하므로 최단 거리 탐색 가능 
            count++;

            // 노드 n개 순회시 모든 통행로를 이동한거이므로 최종 값은 게산됨
            if (count == n) {
                break;
            }

            for (int[] edge : graph[land]) {
                int next = edge[0];
                int nextCost = edge[1];

                if (!visited[next]) {
                    pq.offer(new int[]{next, nextCost});
                }
            }
        }

        return answer;
    }
}