//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {


    public static void main(String[] args)  {
        int[] nums = new int[10001];

        // 수열 생성 메서드를 돌린다.
        // 생성된 값이 저장되어 있을 경우 멈추고, 다음 수에 대한 수열 메서드를 호출한다.
        // 생성된 값이 저장되어 있지 안을 경우, 값을 저장하고 수열 메서드를 연속적으로 호출한다.

        for (int x = 1; x < nums.length; x++) {
            int n = d(x); // 생성자 유무 확인 수 n
            while(n < nums.length && nums[n] == 0) {
                nums[n] = 1;
                n = d(n);
            }
        }
        for (int x = 1; x < nums.length; x++) {
            if (nums[x] == 0) {
                System.out.println(x);
            }
        }
    }

    private static int d(int x) {
        int num = x;
        // 33
        while (x > 0) {
            num += x % 10; // 3
            x /= 10; // 3
        }
        return num;
    }
}

