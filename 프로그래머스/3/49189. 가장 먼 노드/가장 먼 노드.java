// 입력 크기에 따른 시간복잡도
// 인접 행렬(V^2): 20000 x 20000 -> 인접 리스트(V + E): 20000 + 50000
// 마지막 깊이의 정점을 모두 카운팅 필요: BFS 최단거리 탐색으로 깊이가 증가될때마다 카운터를 리셋

import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        // 주어진것은 인접행렬이 아니라 edge이다.
        // 첫번째 요소값에 두번째 요소값을 추가한다.
        // 두번쨰 요소값에 첫번째 요소값을 추가한다.
        
        // 모든 정점에 대한 인접 리스트 생성 및 초기화
        List<List<Integer>> adjList = new ArrayList<>();
        
        // 각 정점에 대한 인접리스트 생성 및 초기화
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        
        // 만일 첫번째 요소값이 없으면, 해당 요소값에 대한 리스트를 초기화 필요
        // 무방향 그래프이므로 두번째 요소값도 동일하게 진행
        for (int i = 0; i < edge.length; i++) {
            // 정점은 1번 부터 시작하므로 0번 인덱스부터 시작하기 위해 1 감소
            int v1 = edge[i][0] - 1;
            int v2 = edge[i][1] - 1;
            if (!adjList.get(v1).contains(v2)) {
                adjList.get(v1).add(v2);    
            }
            if (!adjList.get(v2).contains(v1)) {
                adjList.get(v2).add(v1);
            }
        }
        
        boolean visited[] = new boolean[n];
        
        Queue<Integer> q = new LinkedList<>();
        int cnt = 1;
        q.add(0);
        visited[0] = true; // 방문 마킹
        while(!q.isEmpty()) {
            int size = q.size();            
            cnt = 0;
            while (size-- > 0) {
                int cur = q.remove(); // 인접 정점 탐색을 위해 head 정점 가져오기
                cnt++; // 동일 깊이에 있는 인접 정점이므로 정점 큐에 제거될때마다 개수 카운팅 
                for (int v: adjList.get(cur)) {
                    if (!visited[v]) {
                        visited[v] = true; // 방문 마킹
                        q.add(v);
                    }
                }     
            }   
        }
        return cnt;
    }
}