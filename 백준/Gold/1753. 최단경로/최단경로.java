import java.io.*;
import java.util.*;

// BOJ 1753 - 선형 탐색 다익스트라 (O(V^2 + E))
// 주의: V=20,000에서는 시간 초과 가능성이 큼. 학습/비교용 코드.
public class Main {
    static final int INF = 1_000_000_000;

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int nextInt() throws IOException {
            int c, sgn = 1, x = 0;
            do { c = read(); } while (c <= ' '); // skip space
            if (c == '-') { sgn = -1; c = read(); }
            while (c > ' ') { x = x * 10 + (c - '0'); c = read(); }
            return x * sgn;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int V = fs.nextInt();
        int E = fs.nextInt();
        int K = fs.nextInt(); // 시작 정점(1-based)

        // 인접 리스트
        List<int[]>[] adj = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            int u = fs.nextInt();
            int v = fs.nextInt();
            int w = fs.nextInt();
            adj[u].add(new int[]{v, w});
        }

        int[] dist = new int[V + 1];
        boolean[] visited = new boolean[V + 1];
        Arrays.fill(dist, INF);
        dist[K] = 0;

        // 선형 탐색 다익스트라
        for (int iter = 1; iter <= V; iter++) {
            int u = -1, best = INF;
            // 아직 방문 안 한 정점들 중 dist가 최소인 정점 선택 (O(V))
            for (int i = 1; i <= V; i++) {
                if (!visited[i] && dist[i] < best) {
                    best = dist[i];
                    u = i;
                }
            }
            if (u == -1) break; // 더 갱신 불가
            visited[u] = true;

            // u에서 나가는 간선 완화 (총합 O(E))
            for (int[] e : adj[u]) {
                int v = e[0], w = e[1];
                if (!visited[v] && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (dist[i] >= INF) out.append("INF\n");
            else out.append(dist[i]).append('\n');
        }
        System.out.print(out);
    }
}
