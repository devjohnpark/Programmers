import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 어떤 자료구조를 쓸지도 헷갈렸음
    // 좌표 int[] { int, int }를 저장하고, 인덱스로 가져올수있어야했다. 따라서 ArrayList를 사용 
    static List<int[]> wall = new ArrayList<>();
    static List<int[]> virus = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) { // 벽 좌표 저장
                    wall.add(new int[]{i, j});
                } else if (map[i][j] == 2) {  // 바이러스 좌표 저장
                    virus.add(new int[]{i, j});
                }
            }
        }

        int maxSafeZoneSize = 0;

        // 벽을 3개 세우는 것의 경우의 수에 따른 바이러스 퍼짐 후 안전 영역의 최대 크기 저장
        int wallSize = wall.size();
        for (int i = 0; i < wallSize; i++) {
            for (int j = i + 1; j < wallSize; j++) {
                for (int k = j + 1; k < wallSize; k++) {
                    // 벽 세운 곳 && 바이러스 걸린 곳의 좌표 값 변경에 대한 원상 복귀 필요해서 map 복사
                    int[][] tmpMap = mapCopy(map);

                    int[] firstWall = wall.get(i);
                    int[] secondWall = wall.get(j);
                    int[] thirdWall = wall.get(k);

                    // 3개의 벽 세우기
                    tmpMap[firstWall[0]][firstWall[1]] = 1;
                    tmpMap[secondWall[0]][secondWall[1]] = 1;
                    tmpMap[thirdWall[0]][thirdWall[1]] = 1;

                    // 바이러스 전염
                    activeVirus(tmpMap);

                    // 안전 영역 카운트
                    int safeZoneSize = countSafeZone(tmpMap);

                    // 안전 영역 최대값 갱신
                    maxSafeZoneSize = Math.max(safeZoneSize, maxSafeZoneSize);
                }
            }
        }
        System.out.println(maxSafeZoneSize);
    }

    private static int[][] mapCopy(int[][] map) {
        int[][] newMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }

    private static void activeVirus(int[][] map) {
        for (int i = 0; i < virus.size(); i++) {
            int[] startPosition = virus.get(i);
            int x = startPosition[0];
            int y = startPosition[1];

//            dfs(map, x, y); // 재귀
            Stack<int[]> stack = new Stack<>();
            stack.push(new int[]{x, y});
            map[x][y] = 2;

            while(!stack.isEmpty()) {
                int[] cur = stack.pop();
                int curX = cur[0];
                int curY = cur[1];
                for (int j = 0; j < 4; j++) {
                    int nx = curX + dx[j];
                    int ny = curY + dy[j];

                    if (nx >= 0 && ny >= 0 && nx < n && ny < m && map[nx][ny] == 0) {
                        map[nx][ny] = 2;
                        stack.push(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    private static void dfs(int[][] map, int x, int y) {
        // 현재 위치를 바이러스로 표시
        map[x][y] = 2;

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 체크 및 빈 공간인 경우 재귀 호출
            if (nx >= 0 && ny >= 0 && nx < n && ny < m && map[nx][ny] == 0) {
                dfs(map, nx, ny);
            }
        }
    }

    private static int countSafeZone(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}

