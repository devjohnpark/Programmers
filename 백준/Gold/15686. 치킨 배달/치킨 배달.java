import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// 치킨집 중에서 M개 조합(치킨집 리스트에 저장) -> 집과 치킨 집거리 구하기 -> 최소 거리 갱신
// 조합 -> 백트래킹 (방문 마킹으로 M개의 치킨집 조합 -> 탐색한 치킨집 개수가 M개이면, 방문한 M개의 치킨집과 집의 모든 거리의 합 구하기 -> 최소 거리 갱신)
// 집도 리스트에 저장해서, 치킨집 리스트 중에서 방문한 지점과의 거리만 구한다. (조합만 정해지면 각 집과의 거리만 구하면된다. BFS를 쓰는것보다 간단)
public class Main {
    static List<int[]> home;
    static List<int[]> chicken;
    static boolean[] visited;
    static int n;
    static int m;
    static int minSum = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        home = new ArrayList<>();
        chicken  = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int k = Integer.parseInt(st.nextToken());
                if (k == 1) {
                    home.add(new int[]{i, j});
                } else if (k == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }

        visited = new boolean[chicken.size()];

        backtracking(0, 0);
        System.out.println(minSum);
    }

    // 다음 탐색할 지점 설정(start): 현재 치킨 보다 다음 치킨으로만 설정
    // 탐색한 지점 개수(count): 탐색한 개수가 m개와 동일하면, 집과 조합된 치킨집 거리중에서 가장 작은 거리로 합을 구한다.
    private static void backtracking(int start, int count) {
        // 방문 개수가 동일할때
        if (count == m) {
            int sum = 0;
            for (int i = 0; i < home.size(); i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (visited[j]) {
                        int x = Math.abs(chicken.get(j)[0] - home.get(i)[0]);
                        int y = Math.abs(chicken.get(j)[1] - home.get(i)[1]);
                        min = Math.min(min, x + y);
                    }
                }
                sum += min;
            }
            minSum = Math.min(minSum, sum);
            return;
        }
        
        // 현재 탐색하는 지점의 다음 지점만 탐색 
        for (int i = start; i < chicken.size(); i++) {
            visited[i] = true;
            backtracking(i + 1, count + 1);
            visited[i] = false;
        }
    }
}

