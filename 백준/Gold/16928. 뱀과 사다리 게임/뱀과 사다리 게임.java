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

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        int[][] ladder = new int[n][2];
        int[][] snake = new int[m][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            ladder[i][0] = Integer.parseInt(st.nextToken());
            ladder[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            snake[i][0] = Integer.parseInt(st.nextToken());
            snake[i][1] = Integer.parseInt(st.nextToken());
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[101]; // 같은 지점 방문을 막기위한 방문 배열

        q.add(new int[]{1, 0});
        visited[1] = true;


        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int dist = cur[0];
            int cnt = cur[1];

            if (dist == 100) {
                System.out.println(cnt);
                return;
            }

            // 주사위를 던져서 나온 눈만큼 이동하거나, 사다리를 만나면 올라가거나, 뱀을 만나면 내려가는 모든 경로에서 최단거리
            for (int i = 1; i <= 6; i++) {

                int newDist = dist + i;

                if (newDist > 100) continue;

                for (int j = 0; j < n; j++) {
                    if (newDist == ladder[j][0]) {
                        newDist = ladder[j][1];
                        break;
                    }
                }

                for (int j = 0; j < m; j++) {
                    if (newDist == snake[j][0]) {
                        newDist = snake[j][1];
                        break;
                    }
                }

                if (visited[newDist]) continue;

                visited[newDist] = true;

                // 큐에는 최종 칸만 넣는다 (기존의 newDist/ladder/snake 중복 삽입 제거)
                q.add(new int[]{newDist, cnt + 1});
            }

        }
    }
}

