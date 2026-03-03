import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.*;


// -1 -2 -3 -1 -2
// -3 -2 -2 -1 -1
// -8 / 5

// 8 / 5 = 2
// 8 / 5 + 8 % 5 > 5 * 0.5
// 8 % 5 = 3
// 3 / 5 > 0.5
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums);

        int result = 0;

        // 산술 평균
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        result = sum / n;
        if (Math.abs(sum % n) > n * 0.5) {
            if (result < 0) {
                result -= 1;
            } else {
                result += 1;
            }
        }
        System.out.println(result);

        // 중앙값
        result = nums[n/2];
        System.out.println(result);

        // 최빈값
        // 1 2 2 2 3 4 4 4
        // -2 1 2 3 8
        // 최빈값이 여러개 있을때는 최빈값 중 두 번째로 작은 값을 출력한다.
        // -3 -2 -2 -1 -1
        int maxCnt = 0;
        int cnt = 1;
//        result = nums[0];
        boolean second = false;

        // 2
        // 1 2 2 3 3
        // -2 1 2 3 8
        for (int i = 1; i < n; i++) {
            if (nums[i-1] == nums[i]) cnt++;
            else cnt = 1;

            if (maxCnt < cnt) {
                maxCnt = cnt;
                result = nums[i - 1]; // result = -2
                second = false;
            } else if (maxCnt == cnt && !second) { // 2 == 2 && !false
                second = true;
                result = nums[i - 1]; // 1
            }
        }
        System.out.println(result);

        // 범위
        result = nums[n-1] - nums[0];
        System.out.println(result);
    }
}