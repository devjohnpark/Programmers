import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1. 서로 다른 색의 인접칸 요소 바꿔치기 (롤백 필요: 매개 변수 복사 배열)
// 2. 모든 행/열 중에서 가장 긴 연속된 요소의 개수 찾는다.

public class Main {
    static int[] dx = { 1, -1, 0 , 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxHeight = Integer.parseInt(st.nextToken());
        int heightCnt = Integer.parseInt(st.nextToken());
        // 주어진 세로의 길이: 높이 최대값 초기화
        // 주어진 가로의 길이: 높이의 개수
        // 각 요소의 전체 왼쪽 최대 높이와 오른쪽 최대 높이를 저장해놓는다.
        // '각 요소의 전체 왼쪽 최대 높이와 오른쪽 최대 높이 중에서 작은값 - 현재 높이'를 모두 더한다.

        int[] heights = new int[heightCnt];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < heightCnt; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
        int[] leftHeightMax = new int[heightCnt];
        int[] rightHeightMax = new int[heightCnt];

        leftHeightMax[0] = heights[0];
        for (int i = 1; i < heightCnt; i++) {
            leftHeightMax[i] = Math.max(leftHeightMax[i - 1], heights[i]);
        }


        rightHeightMax[heightCnt - 1] = heights[heightCnt - 1];
        for (int i = heightCnt - 2; i >= 0; i--) {
            rightHeightMax[i] = Math.max(rightHeightMax[i + 1], heights[i]);
        }

        int sum = 0;
        for (int i = 0; i < heightCnt; i++) {
            sum += Math.min(leftHeightMax[i], rightHeightMax[i]) - heights[i];
        }
        System.out.println(sum);
    }
}






