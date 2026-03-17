import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1. 서로 다른 색의 인접칸 요소 바꿔치기 (롤백 필요: 매개 변수 복사 배열)
// 2. 모든 행/열 중에서 가장 긴 연속된 요소의 개수 찾는다.

public class Main {
    static int[] dx = { 1, -1, 0 , 0};
    static int[] dy = { 0, 0, 1 , -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        int max = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (nx >= 0 && ny >= 0 && nx < N && ny < N && map[x][y] != map[nx][ny]) {
                        // 인접한 두 칸 바꿔치기
                        char color = map[x][y];
                        map[x][y] = map[nx][ny];
                        map[nx][ny] = color;

                        int cnt = 1;

                        // 행중에서 연속된 컬러의 가장 큰 긴값
                        for (int i = 0; i < N; i++) {
                             cnt = 1;
                             for (int j = 0; j < N - 1; j++) {
                                 if (map[i][j] == map[i][j+1]) {
                                     cnt++;
                                 } else {
                                     cnt = 1;
                                 }
                                 max = Math.max(max, cnt);
                             }
                        }

                        // 열 중에서 연속된 컬러의 가장 큰 긴값
                        for (int j = 0; j < N; j++) {
                            cnt = 1;
                            for (int i = 0; i < N - 1; i++) {
                                if (map[i][j] == map[i+1][j]) {
                                    cnt++;
                                } else {
                                    cnt = 1;
                                }
                                max = Math.max(max, cnt);
                            }
                        }

                        color = map[x][y];
                        map[x][y] = map[nx][ny];
                        map[nx][ny] = color;
                    }
                }
            }
        }
        System.out.println(max);
    }
}



