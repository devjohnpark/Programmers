// 입력 크기에 따른 시간복잡도
// 인접 행렬(V^2): 20000 x 20000 -> 인접 리스트(V + E): 20000 + 50000
// 마지막 깊이의 정점을 모두 카운팅 필요: BFS 최단거리 탐색으로 깊이가 증가될때마다 카운터를 리셋

import java.util.*;


class Solution {
    public int solution(int n, int[][] edge) {
        // 주어진것은 인접행렬이 아니라 edge이다.
        // 첫번째 요소값에 두번째 요소값을 추가한다.
        
        // 인접 리스트 생성 및 초기화
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) adjList.add(new ArrayList<>());
        
        
        // 만일 첫번째 요소값이 없으면, 해당 요소값에 대한 리스트를 초기화 필요
        // 무방향 그래프이므로 두번째 요소값도 동일하게 진행
        for (int i = 0; i < edge.length; i++) {
            // if (!adjList.contains(edge[i][0])) {
            //     adjList.add(new ArrayList<>());
            // }
            // if (!adjList.contains(edge[i][1])) {
            //     adjList.add(new ArrayList<>());
            // }
            if (!adjList.get(edge[i][0]).contains(edge[i][1])) {
                adjList.get(edge[i][0]).add(edge[i][1]);    
            }
            if (!adjList.get(edge[i][1]).contains(edge[i][0])) {
                adjList.get(edge[i][1]).add(edge[i][0]);
            }
            // adjList.get(edge[i][0]).add(edge[i][1]);
            // adjList.get(edge[i][1]).add(edge[i][0]);
        }
        
        boolean visited[] = new boolean[n + 1];
        
        Queue<Integer> q = new LinkedList<>();
        int cnt = 1;
        q.offer(1);
        visited[1] = true; // 방문 마킹
        while(!q.isEmpty()) {
            int size = q.size();            
            cnt = 0;
            while (size-- > 0) {
                int cur = q.poll(); // 방문한 정점 가져오기
                cnt++; // 정점 큐에 제거될때마다 카운팅: 동일 깊이에 있는 정점
                
                // List<Integer> adjVertaxs = adjList.get(cur);
                for (int x: adjList.get(cur)) {
                    if (!visited[x]) {
                        visited[x] = true; // 방문 마킹
                        q.offer(x);
                    }
                }     
            }   
        }
        return cnt;
    }
}