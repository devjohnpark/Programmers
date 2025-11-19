// words가 정점
// words 크기의 방문 배열
// BFS를 위한 큐를 선언
// 방문하지 않은 word 중에서 변경된 문자열과 다른 문자의 개수가 1개인 경우이 큐에 적재
// 변경한 문자열이 target과 동일하면 return depth (bfs는 처음 도착한 지점이 최단거리임: 동일 깊이를 탐색하므로)
// 큐가 비었을때 bfs 끝나므로 return 0

// bfs를 할때 동일 깊이인지 알려면 정점을 적재할때 캡처를 해야한다. 

import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean[] visited = new boolean[words.length];
        int[] min = new int[words.length];
        Queue<String> q = new LinkedList<>();
        // String change = bigin;
        q.add(begin);
        int depth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                String cur = q.poll();
                if (target.equals(cur)) return depth;
                for (int i = 0; i < words.length; i++) {
                    if (!visited[i]) {
                        int cnt = 0;
                        for (int j = 0; j < words[i].length(); j++) {
                            if (cur.charAt(j) != words[i].charAt(j)) cnt++;
                        }
                        if (cnt == 1) {
                            visited[i] = true;
                            q.add(words[i]);
                        }
                    }
                }   
            }
            depth++;
        }
        
        return 0;
    }
}