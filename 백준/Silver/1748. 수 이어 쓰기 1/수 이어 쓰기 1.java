import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 자리수 x 해당 자리수의 개수
//




// 1 ~ 9: 1자리 -> 9
// 10 ~ 99: 2자리 -> 90
// 100 ~ 999: 3자리 -> 900

// 998  
// 1 ~ 9: 1자리 -> 9
// 10 ~ 99: 2자리 -> 90
// 100 ~ 998: 3자리 -> 800 + 998 % 100 + 1
// 마지막 자리수: (998 / 100 - 1) * 100 + 998 % 100 + 1

// 100 으로 나누면 9 -> 나눈 몫이 0보다 클때까지만 수행 
// 10배씩 늘어날때마다 자리수 카운팅
// 100으로 나눈 나머지 98 -> +1 하면 
// 3(자리수 카운팅) * 9(100으로 나눈 몫) + 98(100으로 나눈 나머지) + 1

// 


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n < 10) {
            System.out.println(n);
            return;
        }

        // 998
        // 1 ~ 9: 1자리 -> 9
        // 10 ~ 99: 2자리 -> 90
        // 100 ~ 998: 3자리 -> 800 + 998 % 100 + 1
        // 마지막 자리수: (998 / 100 - 1) * 100 + 998 % 100 + 1

        // 8: (8 / 1 - 1) * 1 + 8 % 10 + 1 = 8
//        int k = 10;
//        int cnt = 1;
//        int sum = 0; // 9
//        // 10 11 12 13 14 15
//        while (n / k > 0) { // 10 /10
//            sum += 9 * k / 10 * cnt;
//            cnt++;
//            k *= 10;
//        }
//        k /= 10;
//        sum += ((n / k - 1) * k + n % k + 1) * cnt; // 개수
//        System.out.println(sum);


        int k = 10;
        int cnt = 1;
        int sum = 0; // 9
        // 10 11 12 13 14 15
        while (n / k > 0) { // 10 /10
            sum += 9 * k / 10 * cnt;
            cnt++;
            k *= 10;
        }
        k /= 10;
        sum += ((n / k - 1) * k + n % k + 1) * cnt; // 개수
        System.out.println(sum);

    }
}