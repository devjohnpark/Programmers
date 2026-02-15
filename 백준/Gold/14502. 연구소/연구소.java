import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 안전 영역을 어떻게 하면 최대 크키로 확보하는 로직은?
// 브루트 포스: m x n x nmC3 = 62 x 64 x 63 x 62 / 3!(64 -3)! = 64 x 63 x 62 / 6 = 36 x 6 x 10000 = 2160000 x 6 = 10^8 이내 이므로 통과 가능
// 모든 조합에서 DFS하고 0인 개수 최댓값 갱신
// 조합 어떻게 결정? 0인 좌표 리스트에 저장 -> 3중 for문으로 i, j, k 각 다음 인덱스로 벽 생성
// 원복 배열 -> 복사 후 조합 생성 -> 2부터 시작해서 0인 지점만 DFS -> 0칸 개수 세기 ->  최대 값 갱신
// DFS 시작 어떻게 바이러스 지점을 리스트에 저장 -> 각 요소별로 전파
public class Main {
    static int n;
    static int m;
    static List<int[]> safes = new ArrayList<>();
    static List<int[]> virus = new ArrayList<>();
    static int[] dx = { 1, -1, 0, 0};
    static int[] dy = { 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        int maxScope = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int x = Integer.parseInt(st.nextToken());
                map[i][j] = x;
                if (x == 0) {
                    safes.add(new int[] {i, j});
                } else if (x == 2) {
                    virus.add(new int[] {i, j});
                }
            }
        }


        // 벽에서 3개를 선택에서 조합
        int safeSize = safes.size();
        for (int i = 0; i < safeSize; i++) {
            for (int j = i + 1; j < safeSize; j++) {
                for (int k = j + 1; k < safeSize; k++) {
                    int[][] comMap = copy(map);
                    comMap[safes.get(i)[0]][safes.get(i)[1]] = 1;
                    comMap[safes.get(j)[0]][safes.get(j)[1]] = 1;
                    comMap[safes.get(k)[0]][safes.get(k)[1]] = 1;
                    activeVirus(comMap);
                    maxScope = Math.max(maxScope, countSaftyZone(comMap));
                }
            }
        }

        System.out.println(maxScope);
    }

    private static int[][] copy(int[][] map) {
        int[][] newMap = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                newMap[i][j] = map[i][j];
            }
        }
        return newMap;
    }

    private static void activeVirus(int[][] map) {
        for (int i = 0; i < virus.size(); i++) {
            int x = virus.get(i)[0];
            int y = virus.get(i)[1];
            for (int j = 0; j < dx.length; j++) {
                int nx = x + dx[j];
                int ny = y + dy[j];
                virusDfs(map, nx, ny);
            }
        }
    }

    private static void virusDfs(int[][] map, int x, int y) {
        // 벽이나 인덱스 범위 초과시 종료
        if (x < 0 || x >= map.length || y < 0 || y >= map[0].length || map[x][y] != 0) {
            return;
        }

        map[x][y] = 2;

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            virusDfs(map, nx, ny);
        }
    }

    private static int countSaftyZone(int[][] map) {
        int cnt = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

}