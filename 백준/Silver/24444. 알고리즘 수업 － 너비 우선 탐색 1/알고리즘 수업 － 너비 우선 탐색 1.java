import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, R;
    static List<Integer>[] adj;
    static int[] visitedOrder;
    static int order = 1;  // 방문 순서를 기록할 번호

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄 입력: N, M, R
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 인접 리스트 초기화
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            adj[v].add(u);
        }

        // 인접 정점 오름차순 정렬 (문제 조건)
        for (int i = 1; i <= N; i++) {
            Collections.sort(adj[i]);
        }

        // 방문 순서 기록 배열
        visitedOrder = new int[N + 1];

        // BFS 시작
        bfs(R);

        // 출력 (1번 정점부터 N번까지)
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(visitedOrder[i]).append('\n');
        }
        System.out.print(sb);
    }

    // BFS 구현
    static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        visitedOrder[node] = order++;
        queue.add(node);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : adj[u]) {
                if (visitedOrder[v] == 0) {
                    visitedOrder[v] = order++;
                    queue.add(v);
                }
            }
        }
    }

    // DFS 구현
    static void dfs(int node) {
        visitedOrder[node] = order++;  // 방문 순서 기록

        for (int next : adj[node]) {
            if (visitedOrder[next] == 0) {  // 아직 방문하지 않은 경우
                dfs(next);
            }
        }
    }
}
