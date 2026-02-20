import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int x = 1;
        int y = 1;
        if (n == 2) {
            x += 1;
        } else if (n == 3) {
            y += 1;
        } else if (n > 3) {
            y = 2;
            while (n > 3) {
                y += 1;
                n--;
                // 최대 x-1 만큼 -1, +1 반족
                int m = y - 1;
                while (n > 3 && m > 0) {
                    y -= 1;
                    x += 1;
                    n--;
                    m--;
                }
                if (n > 3) {
                    x += 1;
                    n--;
                }
                m = x - 1;
                while (n > 3 && m > 0) {
                    y += 1;
                    x -= 1;
                    n--;
                    m--;
                }
            }

        }
        System.out.println(y + "/" + x);
    }
}