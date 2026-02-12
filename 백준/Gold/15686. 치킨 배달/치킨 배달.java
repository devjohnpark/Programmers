import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static List<int[]> home = new ArrayList<>();
    static List<int[]> chicken = new ArrayList<>();
    static boolean[] visited;

    static int minSum = Integer.MAX_VALUE;

    // BFS 최적화용
    static int[][] seenStamp;  // 방문 스탬프
    static int[][] dist;       // 거리 저장
    static int stamp = 0;

    // 집 빠른 체크용 (집인지 여부만 알면 됨)
    static boolean[][] isHome;

    // 오브젝트 없는 큐(1차원 인코딩)
    static int[] q;
    static int homeCount;

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        isHome = new boolean[n][n];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                int k = Integer.parseInt(st.nextToken());
                if (k == 1) {
                    home.add(new int[]{r, c});
                    isHome[r][c] = true;
                } else if (k == 2) {
                    chicken.add(new int[]{r, c});
                }
            }
        }

        visited = new boolean[chicken.size()];
        homeCount = home.size();

        // BFS용 배열 1회만 할당
        seenStamp = new int[n][n];
        dist = new int[n][n];
        q = new int[n * n];

        backtracking(0, 0);
        System.out.println(minSum);
    }

    private static void backtracking(int start, int count) {
        if (count == m) {
            minSum = Math.min(minSum, bfsOptimized());
            return;
        }

        // 조합(중복 없이)
        for (int i = start; i < chicken.size(); i++) {
            visited[i] = true;
            backtracking(i + 1, count + 1);
            visited[i] = false;
        }
    }

    private static int bfsOptimized() {
        stamp++;

        int head = 0, tail = 0;

        for (int i = 0; i < chicken.size(); i++) {
            if (!visited[i]) continue;
            int[] c = chicken.get(i);
            int r = c[0], col = c[1];

            seenStamp[r][col] = stamp;
            dist[r][col] = 0;
            q[tail++] = r * n + col;
        }

        int sum = 0;
        int found = 0;

        while (head < tail) {
            int pos = q[head++];
            int r = pos / n;
            int c = pos % n;

            int nd = dist[r][c] + 1;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (seenStamp[nr][nc] == stamp) continue;

                seenStamp[nr][nc] = stamp;
                dist[nr][nc] = nd;
                q[tail++] = nr * n + nc;

                // 집은 처음 도달한 거리가 최단거리
                if (isHome[nr][nc]) {
                    sum += nd;
                    found++;

                    // 가지치기 1: 이미 최적해보다 커짐
                    if (sum >= minSum) return sum;

                    // 가지치기 2: 모든 집 거리 확정됨
                    if (found == homeCount) return sum;
                }
            }
        }

        return sum;
    }
}