import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 사각형의 개쉬: (행 - 8 + 1) x (열 - 8 + 1)
// 사각형 내 모든 칸 탐색: 64
// 8 x 8 사각형 내에서 첫번째 칸을 W/B 로 시작한 체스판 중, 최소 변경 해야하는 값으로 갱신
// 부르트 포스 시간 복잡도: (행 - 8 + 1) x (열 - 8 + 1) x 64 x 2 < 10^8 이라서 통과
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer t = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(t.nextToken());
        int m = Integer.parseInt(t.nextToken());
        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        int min = 64;
        char c;
        
        // (n - 8 + 1) x (m - 8 + 1) x 2 x 8^2 = 43^2 x 2 x 8^2 
        // 가로/세로 8인 정사각형 내에서
        for (int rowSt = 0; rowSt <= n - 8; rowSt++) {
            for (int colSt = 0; colSt <= m - 8; colSt++) {
                // 시작점 백/흑 2번 수행
                for (int k = 0; k < 2; k++) {
                    if (k == 0) {
                        c = 'W';
                    } else {
                        c = 'B';
                    }
                    int count = 0;
                    // 8 x 8 정사각형 순회
                    for (int row = rowSt; row < rowSt + 8; row++) {
                        for (int col = colSt; col < colSt + 8; col++) {
                            if (board[row][col] != c) {
                                count++;
                            }
                            if (c == 'W') c = 'B';
                            else c = 'W';
                        }
                        if (c == 'W') c = 'B';
                        else c = 'W';
                    }
                    if (min > count) {
                        min = count;
                    }
                }
            }
        }
        System.out.println(min);
    }
}