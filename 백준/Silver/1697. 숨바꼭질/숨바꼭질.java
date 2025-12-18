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

        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[100001];

        q.add(new int[] { N, 0 });
        visited[N] = true;

        while (!q.isEmpty()) {
            int[] cur = q.remove();
            int curPosition = cur[0];
            int curDist = cur[1];

            if (curPosition == K) {
                System.out.println(curDist);
                return;
            }

            int[] nextPositions = { curPosition - 1, curPosition + 1, curPosition * 2 };

            for (int nextPosition: nextPositions) {
                if (nextPosition >= 0 && nextPosition <= 100000 && !visited[nextPosition]) {
                    visited[nextPosition] = true;
                    q.add(new int[] {nextPosition, curDist + 1});
                }
            }
        }
    }
}