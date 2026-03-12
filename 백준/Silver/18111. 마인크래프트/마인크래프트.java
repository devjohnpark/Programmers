import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// 벽의 높이를 몇으로 맞추면 좋을까?
// 모든 좌표의 값의 평균값
// 땅 고르는데 최소 걸리는 시간중 제일 큰 높이

// 모든 경우의 수를 구해야한다.
// 블록이 될수있는 최소/최대 높이를 구한다.
// 최소 ~ 최대 높이를 기준으로 블록을 평평하게 하는 시간을 계산
// 이중에서 시간이 가장 적고, 제일 큰 높이 벽을 구한다.

// 단, B개수로 맵의 높이를 채울수 있어야 가능
// 맵의 높이가 기준 높이보다 클 경우, B개수에 추가
// 맵의 높이가 기준 높이보다 작을 경우, B개수에서 마이너스
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int minHeight = 100000000;
        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                minHeight = Math.min(map[i][j], minHeight);
                maxHeight = Math.max(map[i][j], maxHeight);
            }
        }

        // 걸리는 시간, 땅의 높이 저장
//        int[][] result = new int[maxHeight - minHeight + 1][2]; // 첫번째 요소로 오름 차순 -> 두번째 요소 내림 차순
        List<int[]> list = new ArrayList<>();
//        int cnt = 0;
        for (int height = minHeight; height <= maxHeight; height++) {
            int invenCnt = B;
            int time = 0;
            boolean result = true;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > height) { // 지정된 블록이 기준 블록이 더 높은 경우, 블록 인벤 추가

                        int diff = map[i][j] - height;
                        time += diff * 2;
                        invenCnt += diff;
                    } else if (map[i][j] < height) { // 지정된 블록이 기준 블록이 더 낮은 경우, 블록 인벤 감가
                        int diff = height - map[i][j];
                        time += diff;
                        invenCnt -= diff;
                    }
                }
            }
            if (invenCnt >= 0) {
                list.add(new int[]{time, height});
            }
        }

        list.sort((a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; // 두번째 요소 내림차순
            }
            return a[0] - b[0];     // 첫번째 요소 오름차순
        });
//        Arrays.sort(result, (a, b) -> {
//            if (a[0] == b[0]) {
//                return b[1] - a[1]; // 두번째 요소 내림차순
//            }
//            return a[0] - b[0]; // 첫번째 요소 오름차순
//        });
//
        System.out.println(list.get(0)[0] + " " + list.get(0)[1]);
    }
}
