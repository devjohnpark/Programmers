// DFS 상하좌우 방향으로 탐색, for 문으로 각 배열 요소부터 탐색시작 -> 탐색 완료시 경로 카운트
// 1이면 탐색 시작하므로 무조건 경로 하나 생기므로 카운팅
// 각 경로마다 정점의 개수를 카운팅해야한다. 따라서 dfs 재귀 메서드 반환값으로 탐색한 정점의 개수를 1을 반환하도록 구현
// 반환받은 1을 모두 더해서 N x N 배열에 저장

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

        int pathCnt = 0;
        List<Integer> cnt = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == 1) {
                    cnt.add(dfs(i, j)); // 단지에 해당하는 정점 개수 반환
                    pathCnt++;
                }
            }
        }

        Collections.sort(cnt);

        System.out.println(pathCnt);
        for (int i = 0; i < pathCnt; i++) {
            System.out.println(cnt.get(i));
        }
    }

    // DFS 구현
    static int dfs(int x, int y) {
        // 탐색 종료 지점
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[x].length || grid[x][y] == 0) {
            return 0;
        }

        grid[x][y] = 0; // 방문 마킹
        int depth = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            depth += dfs(nx, ny);
        }
        return depth;
    }
}

