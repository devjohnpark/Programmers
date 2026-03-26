import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 998
// 1 ~ 9: 1자리 -> 9
// 10 ~ 99: 2자리 -> 90
// 100 ~ 998: 3자리 -> 899 = 800 + 998 % 100 + 1
// 마지막 자리수: (998 / 100 - 1) * 100 + 998 % 100 + 1
// 899 + 90 + 9 
// 8: (8 / 1 - 1) * 1 + 8 % 10 + 1 = 8
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = 10;
        int cnt = 1;
        int sum = 0; // 9
        while (n / k > 0) { 
            sum += 9 * k / 10 * cnt;
            cnt++;
            k *= 10;
        }
        k /= 10;
        sum += ((n / k - 1) * k + n % k + 1) * cnt; // 개수
        System.out.println(sum);

    }
}