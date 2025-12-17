import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        
        if (N == K) {
            System.out.println(0);
            return;
        }
        
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[100001];
        int[] time = new int[100001];
        
        q.offer(N);
        visited[N] = true;
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            int[] next = {cur - 1, cur + 1, cur * 2};
            
            for (int n : next) {
                if (n >= 0 && n <= 100000 && !visited[n]) {
                    visited[n] = true;
                    time[n] = time[cur] + 1;
                    q.offer(n);
                    
                    if (n == K) {
                        System.out.println(time[n]);
                        return;
                    }
                }
            }
        }
    }
}