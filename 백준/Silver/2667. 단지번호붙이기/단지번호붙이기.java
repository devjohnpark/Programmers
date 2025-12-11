import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int grid[][];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄 입력: N, M, R
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        grid = new int[N][N];

        // grid 정보 입력
        for (int i = 0; i < N; i++) {
            String line = br.readLine();      // 예: "0110100"
            for (int j = 0; j < N; j++) {
                grid[i][j] = line.charAt(j) - '0';  // '0' 또는 '1' → 0 또는 1
            }
        }

        List<Integer> cntByPath = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    cntByPath.add(dfs(i, j)); // 단지에 해당하는 정점 개수 반환
                }
            }
        }

        Collections.sort(cntByPath);

        int pathSize = cntByPath.size();
        System.out.println(pathSize);
        for (int i = 0; i < pathSize; i++) {
            System.out.println(cntByPath.get(i));
        }
    }

    // DFS 구현 (탐색할 지점)
    static int dfs(int x, int y) {
        // 탐색 종료
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length || grid[x][y] == 0) {
            return 0; // 탐색 종료시에는 인접 정점을 방문한것이 아니라 이전 정점이 끝이므로 0을 반환
        }

        grid[x][y] = 0; // 방문 마킹
        int adjVertaxCnt = 1; // 현재 방문한 인접 정점 카운팅

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            adjVertaxCnt += dfs(nx, ny);
        }
        return adjVertaxCnt; // 인접 정점 카운트한 개수 반환
    }
}