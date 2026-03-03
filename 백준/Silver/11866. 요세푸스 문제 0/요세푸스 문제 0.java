import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// N: 1000
// K: 1000
// 부루트포스 시간복잡도: 1000 x 1000


// 7 3
// 1 2 3 4 5 6 7
// 1 2 4 5 6 7
// 1 4 5 7
// 1 4 5
// 4
// []

// 7 3
// 1 2 3 4 5 6 7
// 1 2 X 4 5 6 7: 2
// 1 2 X 4 5 X 7: 5
// 1 X X 4 5 X 7: 1
// 1 X X 4 5 X X: 6
// X X X 4 X X X
// X X X X X X X

// 1. false이면 i++
// 2. i == 3이면, true로 변경
// 3. i = 0으로 초기화
// 4. true된 값을 카운팅
// 5.



// 제거된 다음 수부터 K번째 수를 제거
// 3 -> 6 -> 2 -> 7 -> 5 -> 1 -> 4

// an = a0 + 3
// an+1 = an + 3
// an+2 = a1 + 3
// 0 + 3
// 3 + 6
// 2

// 순열
// 순열이면 점화식을 찾아낸다.

// boolean[]으로 초기화

// 삭제된 다음수부터 3번째 수를 true로 설정반복
// 삭제된 수의 초기값은 0 (0 다음 수부터 3번째 수 true로 설정)

// true가 아닌 수중에서 3번째 수를 찾아내서 false로 설정

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        boolean[] num = new boolean[n];

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int cnt = 0;
        int i = 0;
        int j = 0;
        while (cnt < n) {
            if (!num[i]) { // 0, 1, 2
                j++; // 1, 2, 3
            }
            if (j == k) {
                num[i] = true;
                sb.append(i + 1);
                cnt++; // 1
                if (cnt < n) {
                    sb.append(", ");
                }
                j = 0;
            }
            i++;
            if (i == n) {
                i = 0;
            }
        }
        sb.append(">");
        System.out.println(sb.toString());
    }
}