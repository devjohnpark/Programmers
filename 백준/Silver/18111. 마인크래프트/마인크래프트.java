import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 문제1: 문제를 모두 읽고나서 입출력 예시를 확인한다 -> 큰 맥락에서 읽고 바로 입출력을 확인한다.
// 문제1: 로직을 명확히 안세우고 작성했다.
// 문제2: 정렬 메서드를 사용하지 못한다.

// 우리의 목적은 이 집터 내의 땅의 높이를 일정하게 바꾸는 것이다.
// 땅고르기 두 종류의 작업
// 맵 요소를 인벤터리에 삽입: 2초
// 맵 요소로 인벤터리에서 가져오기: 1초
// ‘땅 고르기’ 작업에 걸리는 최소 시간과 그 경우 땅의 높이를 출력하시오.
// 인벤토리에는 B개의 블록

// 완전 탐색 필요
// 기준 높이: 맵의 최소 높이 ~ 맵의 최대 높이
// 순회: 기준 높이 지정 -> 순회: 맵 요소 지정
// 기준 높이 지정 -> 맵 요소 지정
// 1. 맵의 높이 > 기준 높이: 인벤터리 입력 -> 시간: (맵의 높이 - 기준 높이) x 2초
// 2. 맵의 높이 < 기준 높이: 인벤터리 출력 -> 시간: (기준 높이 - 맵의 높이) x 1초
// 3. 시간, 높이를 리스트에 저장 (단, 인벤토리가 0미만일 경우 지정한 높이는 성립 안됨)
// 4. 리스트를 시간 오름차순 후 시간이 동일하다면, 높이 내림차순 정렬

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
        int minHeight = Integer.MAX_VALUE;
        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                minHeight = Math.min(minHeight, map[i][j]);
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }
        List<int[]> list = new ArrayList<>();
        for (int height = minHeight; height <= maxHeight; height++) {
            int inven = B;
            int time = 0;
            int diff = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > height) {
                        diff = map[i][j] - height;
                        time += diff * 2;
                        inven += diff;
                    } else if (map[i][j] < height) {
                        diff = height - map[i][j];
                        time += diff;
                        inven -= diff;
                    }
                }
            }
            if (inven >= 0) list.add(new int[]{time, height});
        }
        list.sort((a, b) -> {
            if (a[0] == b[0]) { // 1번째 요소가 동일하다면
                return b[1] - a[1]; // 2번째 요소 내림차순
            }
            return a[0] - b[0]; // 1번째 요소 오름차순
        });

        System.out.println(list.get(0)[0] + " " + list.get(0)[1]);
    }
}
