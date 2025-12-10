import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] adj;
    static boolean[] visitedOrder;
    static int cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N: 총 컴퓨터 수
        // M: 간선 정보 (정점 쌍)
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        // 인접 리스트 초기화
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
            adj[v].add(u);
        }

        // 방문 순서 기록 배열
        visitedOrder = new boolean[N + 1];

        // BFS 시작
        dfs(1);

        // 출력
        System.out.println(cnt);
    }

    // DFS 구현
    static void dfs(int node) {
        visitedOrder[node] = true;  // 방문 순서 기록

        for (int next : adj[node]) {
            if (!visitedOrder[next]) {  // 아직 방문하지 않은 경우
                cnt++;
                dfs(next);
            }
        }
    }
}
