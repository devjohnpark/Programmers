import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

//        int x = 12;
//        double d = (double) 13 / 5;

//        int x = 13 / 5 + 0.5;
//        System.out.println((double) 13 / 5 + 0.5);

        // 12 / 5 -> 2.4 -> 2
        //



        int[] levels = new int[N];
        for (int i = 0; i < N; i++) {
            levels[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(levels);
        // 절사평균: 위아래로 15%씩 제거 (반올림)
        int delete_15p= (int) (N * 0.15 + 0.5);
        int sum = 0;
        for (int i = delete_15p; i < N - delete_15p; i++) {
            sum += levels[i];
        }
        int people = N - 2 * delete_15p;
        int avg = (int) (sum * 1.0 / people + 0.5); // 나누는 숫자를 double로 치환 혹은 곱하기 1.0
        System.out.println(avg);
    }
}



