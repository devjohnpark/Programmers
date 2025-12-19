import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> q = new LinkedList<>();

        // 1. 1로 시작하는 모든 지점을 동시 탐색하기 위해서 큐에 모두 적재
        // 2. 모든 토마토가 익어 있을시: 0인 요소가 없을때 0출력
        boolean canSearch = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    q.add(new int[]{i, j});
                } else if (map[i][j] == 0) {
                    canSearch = true;
                }
            }
        }

        if (!canSearch) {
            System.out.println(0);
            return;
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int[] cur = q.remove();
            int x = cur[0];
            int y = cur[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 방문 지점을 제외하고 탐색: 배열 요소가 0 일때만
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
                    map[nx][ny] = map[x][y] + 1; // 이동한 거리 저장
                    q.add(new int[]{nx, ny});
                }
            }
        }

        // 1. 토마토가 모두 익지 않은 상태: 0인 요소 발견시 -1 반환
        // 2. 토마토가 모두 익는 최단거리: 이동한 거리 중 최대값을 반환
        int maxDate = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                maxDate = Math.max(maxDate, map[i][j]);
            }
        }
        int result = maxDate - 1; // 처음 시작이 1인 요소이므로 -1 빼야한다. 
        System.out.println(result);
    }
}