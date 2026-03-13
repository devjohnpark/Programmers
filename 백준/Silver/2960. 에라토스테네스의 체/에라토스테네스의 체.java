import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//

// int[] 배열 사용
// 인덱스는 수이고 저장 값은 삭제 번째
// 삭제된 수를 몇번재로 삭제되었는지 저장
// 삭제되지 않은 수는 0으로 초기화되어있음

// 1. 삭제되지 않은 가장 작은 소수 찾기 (요소값이 0이 아닌 수중에 약수의 개수가 1과 해당수를 제외하고 없는수)
// 2. 찾은 소수를 지우고, 아직 지우지 않은 P의 배수를 크기 순서대로 지운다.
// 3. 아직 모든 수를 지우지 않았다면, 다시 1번 단계로 간다.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] numsCnt = new int[N+1];
        int cnt = 0;
        while (cnt < N - 1) {
            for (int num = 2; num <= N; num++) {
                if (numsCnt[num] == 0 && isDecimal(num)) {
                    for (int j = 1; num * j <= N; j++) {
                        if (numsCnt[num * j] == 0) {
                            numsCnt[num * j] = ++cnt; // 몇번째 소수인지 저장
                        }
                    }

                }
            }
        }

        // K번째 소수라면 출력
        for (int num = 2; num <= N; num++) {
            if (numsCnt[num] == K) System.out.println(num);
        }
    }

    // 소수 찾기
    private static boolean isDecimal(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) return false; // 나누어 떨어지는 수가 있으면 소수가 아님
        }
        return true;
    }
}


