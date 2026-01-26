// 탐험 시작 하기 위한, 최소 필요 피로도
// 탐험 마쳤을때, 소모 피로도


// 로직
// 남은 피로도 - 최소 필요 피로도 >= 0 이면, 탐색가능
// 남은 피로도 -= 소모 필요도
// 탐색 순서는 
class Solution {
    int maxCnt = 0;
    public int solution(int k, int[][] dungeons) {
        backtracking(new boolean[dungeons.length], k, dungeons, 0);
        return maxCnt;
    }
    
    private void backtracking(boolean[] visited, int resource, int[][] dungeons, int cnt) {
        maxCnt = Math.max(cnt, maxCnt);
        
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && resource - dungeons[i][0] >= 0) {
                visited[i] = true;
                backtracking(visited, resource - dungeons[i][1], dungeons, cnt + 1);
                visited[i] = false;
            }
        }
    }
}