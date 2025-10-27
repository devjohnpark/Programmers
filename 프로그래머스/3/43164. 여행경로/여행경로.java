import java.util.*;

// 제한사항을 보았는가? 주어진 공항 개수 3개 이상 10000개 이하 -> 10^4이므로 10^8까지라서 시간복잡도 n^2을 이하로 풀어야한다.
// 문제를 보고 알고리즘을 떠올렸는가? 항공권 정보가 담긴 2차원 배열 방문하는 티켓으로 공항 경로를 배열에 담는것이므로 DFS를 써야한다.
// 2차원 배열에서 공항간에 탐색을 어떻게 진행할것인가? 
// 1. 배열의 첫번째 요소에서 두번째 요소로 탐색하도록 해야한다.
// 2. 모든 경로를 탐색해야하며, 방문 마킹을 해서 방문한곳은 제외시킨다.
// 3. 2차원 배열을 탐색할때 경로가 모두 성립하지않으므로, 성립하지 않을때 백트래킹을 수행해서 시간복잡도를 낮춘다.
class Solution {
    private final List<String> result = new ArrayList<>();
    
    // 이차원 배열에서 각 첫번째 요소를 출발점으로 순회시, 중복 탐색을 제거하기 위해 출발점 방문 마킹
    
    // 시작점(첫번째 요소)를 기준으로 방문한 항공을 마킹
    private boolean[] visited;
    
    public String[] solution(String[][] tickets) {        
        // 찾은 탐색 경로를 공항 이름과 공백을 붙여서 문자열로 만들어서 List<String>에 저장
        dfs(0, "ICN", "ICN", tickets, new boolean[tickets.length]);
        
        // 탐색 경로 문자열을 사전순 정렬
        Collections.sort(result);
        
        // 가장 첫번째 문자열을 반환
        String[] answer = result.get(0).split(" ");
        return answer;
    }
    
    // 티켓을 모두 사용할 경우, 탐색 경로 완성되므로 리스트에 추가
    // int depth == tickets.length
    
    // 항공권이 연결되기 위해서는 두번째 요소와 첫번째 요소가 같아야한다.
    // 따라서 재귀 메서드를 호출할때 두번째 요소를 매개변수로 전달한다. (매개변수명은 또 출발점이되므로 start로 지정)
    // if(tickets[i][0].equal(start)) 일때만 재귀메서드 호출
    
    // 모든 출발점을 시작으로 경로 탐색
    // 방문한 항공 방문 표시
    // dfs 탐색
    // 방문한 항공 방문 표시를 제거해서 부모 노드올라가 형제 노드로 탐색하게 한다. 
    
    // 방문 표시 제거를 빼면 DFS는 단 하나의 경로만 탐색하고 끝나버리게 된다.
    // 모든 항공권을 한 번씩 사용하여 가능한 모든 여행 경로를 찾아야한다.
    private void dfs(int depth, String start, String route, String[][] tickets, boolean[] visited) {
        if (tickets.length == depth) {
            result.add(route);
            return;
        }
        
        // 모든 경로를 탐색하며, 방문한 공황은 제외
        for (int i = 0; i < tickets.length; i++) {
            // 현재 공항(start)에서 출발하는 항공권을 찾는다.ㅁㅊ
            if (tickets[i][0].equals(start) && !visited[i]) {
                visited[i] = true; // 항공권 사용
                dfs(depth + 1, tickets[i][1], route + " " + tickets[i][1], tickets, visited);
                visited[i] = false; // 항공권 미사용
            }
        }
    }
}

