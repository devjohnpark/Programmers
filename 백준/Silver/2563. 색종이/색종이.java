import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 색종이가 2개 겹치는 것이 아닌, N개를 겹쳐야하므로 계산하기가 까다로움
// 해당 문제의 좌표는 최대 가로 세로 100이므로 크기가 정해져있다.
// 따라서 색종이를 좌표에 모두 마킹하고나서 마킹한 칸의 개수를 세서 출력하면된다.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[][] p = new boolean[100][100];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for (int dx = x; dx < x + 10; dx++) {
                for (int dy = y; dy < y + 10; dy++) {
                    p[dx][dy] = true;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (p[i][j]) {
                    sum++;
                }
            }
        }
        System.out.println(sum);
    }
}