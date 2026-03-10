import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[10];
        while (n > 0) { 
            int x = n % 10;
            // x가 6일때, 9가 더 작으면 9에 카운팅
            // x가 9일때, 6이 더 작으면 6에 카운팅
            if (x == 6 && nums[6] > nums[9]) {
                nums[9]++;
            } else if (x == 9 && nums[9] > nums[6]) {
                nums[6]++;
            } else {
                nums[x]++;
            }
            n /= 10;
        }
        int max = 1;
        for (int i = 0; i < 10; i++) {
            max = Math.max(max, nums[i]);
        }
        System.out.println(max);
    }
}