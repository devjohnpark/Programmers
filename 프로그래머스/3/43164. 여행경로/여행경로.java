import java.util.*;

class Solution {
    // 가능한 모든 여행 경로(문자열 형태)를 저장하는 리스트
    private ArrayList<String> list = new ArrayList<>();
    
    // 각 티켓이 사용되었는지 여부를 체크하는 boolean 배열
    // 그래프에서 인접한 노드를 이미 방문했는지 확인 필요
    private boolean visited[];

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];

        // 항상 "ICN" 공항에서 시작
        dfs(0, "ICN", "ICN", tickets);

        // 가능한 모든 경로 중 사전순으로 가장 앞선 경로 선택
        Collections.sort(list);

        return list.get(0).split(" ");
    }

    // current: 현재 위치한 공항
    // path: 지금까지의 경로 문자열 (공항들을 공백으로 이어 붙임)
    // depth: 현재 사용한 티켓의 개수
    private void dfs(int depth, String current, String path, String[][] tickets) {
            // 모든 티켓을 사용했을 경우 -> 경로 완성
        if (depth == tickets.length) {
            list.add(path);
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            // 아직 사용하지 않은 티켓 중에서, current에 현재 공항을 전달하여 다른 티켓중 출발지와 동일한지 확인
            if (!visited[i] && current.equals(tickets[i][0])) {
                // 티켓 사용 처리
                visited[i] = true; 

                // 다음 공항으로 이동하면서 경로 확장
                dfs(depth + 1, tickets[i][1], path + " " + tickets[i][1], tickets); // JFK

                // 티켓 미사용으로 되돌리기
                visited[i] = false; 
            }
        }
    }
}
