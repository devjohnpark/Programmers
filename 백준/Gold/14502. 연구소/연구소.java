import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 벽 좌표 저장
    static List<int[]> wall = new ArrayList<>();
    static List<int[]> virus = new ArrayList<>();
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

        // 바이러스 좌표 저장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    wall.add(new int[]{i, j});
                } else if (map[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }
            }
        }

        int maxSafyCnt = 0;

        // 벽을 3개 세우는 것의 경우의 수에 따른 바이러스 퍼짐 후 안전 영역의 최대 크기 저장
        int wallSize = wall.size();
        for (int i = 0; i < wallSize; i++) {
            for (int j = i + 1; j < wallSize; j++) {
                for (int k = j + 1; k < wallSize; k++) {
                    // 벽 세운 곳 && 바이러스 걸릭곳 원상 복귀 필요해서 map 복사
                    int[][] tmpMap = mapCopy(map, n, m);

                    // 3개의 벽 세우기
                    tmpMap[wall.get(i)[0]][wall.get(i)[1]] = 1;
                    tmpMap[wall.get(j)[0]][wall.get(j)[1]] = 1;
                    tmpMap[wall.get(k)[0]][wall.get(k)[1]] = 1;

                    // 바이러스 전염
                    activeVirus(tmpMap);

                    // 안전 영역 카운트
                    int cntSaftyZone = countSaftyZone(tmpMap);

                    // 안전 영역 최대값 갱신
                    maxSafyCnt = Math.max(cntSaftyZone, maxSafyCnt);
                }
            }
        }
        System.out.println(maxSafyCnt);
    }

    private static int[][] mapCopy(int[][] map, int n, int m) {
        int[][] newMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }

    private static void activeVirus(int[][] map) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        for (int i = 0; i < virus.size(); i++) {
            int[] startPosition = virus.get(i);
            int x = startPosition[0];
            int y = startPosition[1];

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

    private static int countSaftyZone(int[][] map) {
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

