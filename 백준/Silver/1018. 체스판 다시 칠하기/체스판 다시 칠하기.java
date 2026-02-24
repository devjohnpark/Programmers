import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// N: 50, M: 8
// 사각형의 개쉬: (N - M + 1)^2
// 사각형 내 모든 칸 탐색: M^2
// M x M 사각형 내에서 첫번째 칸을 W/B 로 시작한 체스판 중, 최소 변경 값으로 저장
// 부르트 포스 시간 복잡도: (N - M + 1)^2 x M^2 = 43^2 x 8^2 x 2 < 10^8 이라서 통과

// 시작점으로부터 8개 이후면 탐색 x
// 시작점으로 부터 상하좌우를 이동하면서 색이 이전과 다르지 않은 개수를 탐색
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