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

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 현재 거리를 x번 정점으로 두면, 시작 정점 5부터 x - 1, x + 1, x * 2 정점으로 확장시키면 그래프 형태가 나온다.
        // 초당 특정 거리만큼 움직이므로 무가중치 그래프이다.
        // 따라서 BFS 탐색해서 가장 먼저 도착 정점 17에 도달했을때 깊이를 반환하면 시간이다.

        // int 거리를 저장할 큐
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[100001]; // 정점 N의 방문 마킹을 위한 변수

        q.add(N);
        visited[N] = true;

        int time = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curPosition = q.remove(); // 방문 정점

                if (curPosition == K) {
                    System.out.println(time);
                    return;
                }

                // x - 1, x + 1, x * 2 정점 생성
                int[] nextPositions = { curPosition - 1, curPosition + 1, curPosition * 2 };

                for (int nextPosition: nextPositions) {
                    if (nextPosition >= 0 && nextPosition <= 100000 && !visited[nextPosition]) {
                        visited[nextPosition] = true;
                        q.add(nextPosition);
                    }
                }
            }
            time++;
        }
    }
}
