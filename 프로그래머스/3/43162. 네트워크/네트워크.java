class Solution {
    public int solution(int n, int[][] computers) {
        int cnt = 0;
        boolean[] visited = new boolean[computers.length];
        
        // 정점의 개수만큼 만큼 DFS 탐색 수행, 탐색을 마치면 경로가 생성되므로 개수 추가
        for (int i = 0; i < computers.length; i++) {
            if (!visited[i]) {
                dfs(computers, i, visited); // 시작 정점으로부터 DFS 탐색 수행
                cnt++;
            }
        } 
        return cnt;
    }
    
    private void dfs(int[][] computers, int node, boolean[] visited) {
        visited[node] = true; // 정점 방문 마킹
        for (int i = 0; i < computers.length; i++) {
            // 미방문 정점중에서 인접 정점 탐색
            if (computers[node][i] == 1 && !visited[i]) {
                dfs(computers, i, visited);
            }
        }
    }
}