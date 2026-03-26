import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

// 1. 서로 다른 색의 인접칸 요소 바꿔치기 (롤백 필요: 매개 변수 복사 배열)
// 2. 모든 행/열 중에서 가장 긴 연속된 요소의 개수 찾는다.

public class Main {
    public static void main(String[] args) throws IOException {
        int n1, m1, n2, m2;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n1 = Integer.parseInt(st.nextToken());
        m1 = Integer.parseInt(st.nextToken());
        int[][] matrix1 = new int[n1][m1];
        for (int i = 0; i < n1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m1; j++) {
                matrix1[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        n2 = Integer.parseInt(st.nextToken());
        m2 = Integer.parseInt(st.nextToken());
        int[][] matrix2 = new int[n2][m2];
        for (int i = 0; i < n2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m2; j++) {
                matrix2[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < m2; j++) {
                int sum = 0;
                for (int k = 0; k < m1; k++) {
                    sum += matrix1[i][k] * matrix2[k][j];
                }
                System.out.print(sum + " ");
            }
            System.out.println();
        }
    }
}